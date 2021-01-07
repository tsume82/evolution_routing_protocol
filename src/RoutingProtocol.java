import io.jenetics.*;
import io.jenetics.engine.*;
import io.jenetics.ext.SingleNodeCrossover;
import io.jenetics.ext.util.FlatTree;
import io.jenetics.ext.util.FlatTreeNode;
import io.jenetics.ext.util.Tree;
import io.jenetics.ext.util.TreeNode;
import io.jenetics.prog.ProgramChromosome;
import io.jenetics.prog.ProgramGene;
import io.jenetics.prog.op.Op;
import io.jenetics.stat.DoubleMomentStatistics;
import io.jenetics.util.ISeq;
import io.jenetics.util.RandomRegistry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class RoutingProtocol {
    // EVOLUTION PARAMETER
    static double MUTATION_RATE = 0.1;
    static double CROSSOVER_RATE = 0.1;
    static int POPULATION_SIZE = 150;
    static int MAX_GENERATIONS = 100;
    static int STEADY_FITNESS = 50;
    static int MAX_DEPTH = 5; // maximum depth in the first generation
    static int MAX_NUMBER_NODES = 50;

    // SIMULATION PARAMETERS
    static int NUMBER_HOST = 100;
    static String MAP_NAME = "manhattan"; // default, helsinki or manhattan
    static String BASE_PROTOCOL = "epidemic"; // epidemic or prophet

    // SET SEED PARAMETER
    static boolean USE_SEED = false;
    static int SEED = 123;
    static Random RANDOM_OBJ;

    // PARAMETERS FOR THE RESULTS LOCATIONS
    static String CURRENT_RUN;
    static String DIRECTORY_RUN;

    // ELEMENTS
    static GPelements gPelements = new GPelements(BASE_PROTOCOL);


    static final ISeq<Op<String>> OPERATIONS = ISeq.of(
            Op.of("or", 2,  v -> "(" + v[0] + " || " + v[1] + ")"),
            Op.of("not",1, v -> "!" + v[0]),
            Op.of("if",2, v -> "if(" + v[0] + "){" + v[1] + "}"),
            Op.of("notEqual",2, v -> "(" + v[0] + " != " + v[1] + ")"),
            Op.of("add",2,v-> v[0] + "\n" + v[1])
    );

    static final ISeq<Op<String>> TERMINALS = ISeq.of(
            Op.of("update",0, f -> "super.update();"),
            Op.of("isTransferring",0, f -> "isTransferring()"),
            Op.of("canStartTransfer",0, f -> "canStartTransfer()"),
            Op.of("exchangeDeliverableMessages",0, f -> "exchangeDeliverableMessages();"),
            Op.of("tryAllMessagesToAllConnections",0, f -> "this.tryAllMessagesToAllConnections();"),
            // Op.of("null",0, f -> "null"),
            Op.of("return",0, f -> "return;")
    );

    static final Codec<ProgramGene<String>, ProgramGene<String>> CODEC =
            Codec.of(
                    Genotype.of(ProgramChromosome.of(
                            // Program tree depth
                            MAX_DEPTH,
                            // Chromosome validator
                            ch -> ch.root().size() <= MAX_NUMBER_NODES,
                            OPERATIONS,
                            TERMINALS
                    )),
                    Genotype::gene
            );

    static double fitness(final ProgramGene<String> program) {
        String id = String.valueOf(UUID.randomUUID());
        String scenarioName = "ind_" + id.replace('-','_');

        DynamicClassGeneration ex = new DynamicClassGeneration(program.eval());

        double res = ex.executeTheOne(scenarioName, NUMBER_HOST, MAP_NAME, BASE_PROTOCOL);
        return res;
    }

    private static boolean CreateDirectory(String folderPath){
        File theDir = new File(folderPath);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;

            try {
                theDir.mkdirs();
                result = true;
            } catch (SecurityException se) {
                System.out.println(se.getMessage());
                return false;
            }
            if (result) {
                System.out.println("Folder created");
            }
        } else if (theDir.exists()) {
            System.out.println("Folder exist");
        }
        return true;
    }

    final static Constraint<ProgramGene<String>, Double> CONSTRAINT = Constraint.of(
            RoutingProtocol::isValid,
            RoutingProtocol::repair
    );

    private static boolean isValid(final Phenotype<ProgramGene<String>, Double> pt) {
        final Tree<Op<String>, ?> tree = pt.genotype().gene();
        // System.out.println("tree nel check validità " + tree.toString());
        // Do your validity checks with the given operation tree.
        // boolean res = isValidRec(tree, 0, "corpus");
        boolean res = checkRootValid(tree, "corpus");
        return res;
    }

    private static boolean checkRootValid(final Tree<Op<String>, ?> tree, String typeExpected){
        String name = tree.value().name();
        ProgramNode currentNode = gPelements.getNodeByName(name);
        if (!currentNode.getType().equals(typeExpected)){
            return false;
        } else {
            Tree<Op<String>, ?> child = tree.firstChild().get();
            int childIndex = tree.indexOf(child);
            for (int i = 0; i < tree.childCount(); i++){
                boolean res = isValidRec(tree, childIndex+i, currentNode.getChildrenType()[i]);
                if (!res){
                    return false;
                }
            }
            return true;
        }
    }


    private static boolean isValidRec(final Tree<Op<String>, ?> tree, int start, String typeExpected){
        Tree<Op<String>, ?> current = tree.childAt(start);
        ProgramNode currentNode = gPelements.getNodeByName(current.value().name());
        if (!currentNode.getType().equals(typeExpected)){
            return false;
        } else {
            if (current.firstChild().isPresent()) {
                Tree<Op<String>, ?> child = current.firstChild().get();
                int childIndex = current.indexOf(child);
                for (int i = 0; i < current.childCount(); i++){
                    boolean res = isValidRec(current, childIndex+i, currentNode.getChildrenType()[i]);
                    if (!res){
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private static Phenotype<ProgramGene<String>, Double>
    repair(final Phenotype<ProgramGene<String>, Double> pt, final long generation) {
        final TreeNode<Op<String>> tree = TreeNode
                .ofTree(pt.genotype().chromosome().gene());

        repairRoot(tree, "corpus");

        final FlatTreeNode<Op<String>> flat = FlatTreeNode.of(tree);
        final ISeq<ProgramGene<String>> genes = flat.map(t -> gene(pt.genotype().gene(), t));
        final Chromosome<ProgramGene<String>> chromosome = pt.genotype().chromosome().newInstance(genes);
        return Phenotype.of(Genotype.of(chromosome), generation);
    }

    private static void repairRoot(final TreeNode<Op<String>> tree, String typeExpected) {
        // Repair your tree.
        String name = tree.value().name();
        ProgramNode currentNode = gPelements.getNodeByName(name);
        if (!currentNode.getType().equals(typeExpected)){
            ArrayList<String> elementSet = gPelements.getElementsByChildrenType(tree.childCount(), typeExpected);
            // Random rand = new Random(SEED);
            if (elementSet.size() > 0){
                int rand_int = RANDOM_OBJ.nextInt(elementSet.size());
                tree.value(Op.of(elementSet.get(rand_int), tree.childCount(), v -> gPelements.getOperationFunction(v, elementSet.get(rand_int))));
                currentNode = gPelements.getNodeByName(elementSet.get(rand_int));
            }
        }
        Tree<Op<String>, ?> child = tree.firstChild().get();
        int childIndex = tree.indexOf(child);
        for (int i = 0; i < tree.childCount(); i++){
            repairRecursive(tree, childIndex+i, currentNode.getChildrenType()[i]);
        }
    }

    private static void repairRecursive(final TreeNode<Op<String>> tree, int start, String typeExpected){
        TreeNode<Op<String>> current = tree.childAt(start);
        ProgramNode currentNode = gPelements.getNodeByName(current.value().name());
        if (!currentNode.getType().equals(typeExpected)){
            ArrayList<String> elementSet = gPelements.getElementsByChildrenType(current.childCount(), typeExpected);
            // Random rand = new Random(SEED);
            if (elementSet.size() > 0){
                int rand_int = RANDOM_OBJ.nextInt(elementSet.size());
                current.value(Op.of(elementSet.get(rand_int), current.childCount(), v -> gPelements.getOperationFunction(v, elementSet.get(rand_int))));
                currentNode = gPelements.getNodeByName(elementSet.get(rand_int));
            }
        }
        if (current.firstChild().isPresent()) {
            Tree<Op<String>, ?> child = current.firstChild().get();
            int childIndex = current.indexOf(child);
            for (int i = 0; i < current.childCount(); i++){
                repairRecursive(current, childIndex+i, currentNode.getChildrenType()[i]);
            }
        }
    }

    private static ProgramGene<String> gene(
            final ProgramGene<String> template,
            final FlatTree<Op<String>, ?> tree
    ) {
        return template.newInstance(
                tree.value(),
                tree.childOffset(),
                tree.childCount()
        );
    }

    private static void WriteBestIndividual(String code, long generation, String tree){
        String className = "BestIndividual_" + generation;
        File sourceFile = new File(DIRECTORY_RUN + "/" + className + ".java");
        String sourceCode = "";
        if (BASE_PROTOCOL.equals("epidemic")){
            sourceCode = TemplateClassGenerator.GenerateTemplateEpidemic(className, code);
        }
        else if (BASE_PROTOCOL.equals("prophet")) {
            sourceCode = TemplateClassGenerator.GenerateTemplateProphet(className, code);
        }
        // write the source code into the source file
        try {
            FileWriter writer = new FileWriter(sourceFile);
            writer.write(sourceCode);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String treeName = "Tree_" + generation;
        File treeFile = new File(DIRECTORY_RUN + "/" + treeName + ".txt");
        // write the source code into the source file
        try {
            FileWriter writer = new FileWriter(treeFile);
            writer.write(tree);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void WriteFitnessStatistics(double bestFitness, long generation, ISeq<Phenotype<ProgramGene<String>,Double>> population){
        File statisticsFitnessFile = new File("./results/" + CURRENT_RUN + "_best_fitness.csv");

        try {
            FileWriter writer = new FileWriter(statisticsFitnessFile, true);
            writer.append(generation + ";" + bestFitness + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File statisticsAllFitnessFile = new File("./results/" + CURRENT_RUN + "_all_fitness.csv");

        try {
            FileWriter writer = new FileWriter(statisticsAllFitnessFile, true);
            writer.append(String.valueOf(generation));
            for (int i = 0; i < population.size(); i++){
                Phenotype<ProgramGene<String>,Double> current = population.get(i);
                writer.append(";" + current.fitness());
            }
            writer.append("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // maximize the delivery_prob
    public static void main(final String[] args) {

        if (BASE_PROTOCOL.equals("prophet")){
            TERMINALS.append(Op.of("tryOtherMessages",0, f -> "tryOtherMessages();"));
        }

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH.mm.ss.SSS");
        String currentDateTime = format.format(date);
        if (USE_SEED) {
            CURRENT_RUN = "RunOn_" + currentDateTime + "_seed" + SEED + "_map" + MAP_NAME + "_host" + NUMBER_HOST + "_prot" + BASE_PROTOCOL;
        } else {
            CURRENT_RUN = "RunOn_" + currentDateTime + "_map" + MAP_NAME + "_host" + NUMBER_HOST + "_prot" + BASE_PROTOCOL;
        }

        DIRECTORY_RUN = "./the-one/src/routing/" + CURRENT_RUN;

        boolean checkDir = CreateDirectory(DIRECTORY_RUN);

        if (checkDir){
            final EvolutionStatistics<Double, DoubleMomentStatistics> statistics =
                    EvolutionStatistics.ofNumber();

            final Engine<ProgramGene<String>, Double> engine;

            // RandomRegistry.random(new LCG64ShiftRandom.ThreadSafe(1234));
            final ProgramGene<String> result;
            if (USE_SEED){
                RANDOM_OBJ = new Random(SEED);
                engine = Engine
                        .builder(RoutingProtocol::fitness, CODEC)
                        .maximizing()
                        .populationSize(POPULATION_SIZE)
                        .alterers(
                                new SingleNodeCrossover<>(CROSSOVER_RATE),
                                new Mutator<>(MUTATION_RATE))
                        .constraint(CONSTRAINT)
                        .executor(Runnable::run)
                        .build();
                result =
                        RandomRegistry.with(RANDOM_OBJ, r ->
                                engine.stream()
                                        .limit(Limits.bySteadyFitness(STEADY_FITNESS))
                                        .limit(MAX_GENERATIONS)
                                        .peek(statistics)
                                        .peek(er -> WriteBestIndividual(er.bestPhenotype().genotype().gene().eval(), er.generation(),Tree.toString(er.bestPhenotype().genotype().gene())))
                                        .peek(er -> WriteFitnessStatistics(er.bestFitness(), er.generation(), er.population()))
                                        .collect(EvolutionResult.toBestGenotype())
                                        .gene()
                        );
            } else {
                RANDOM_OBJ = new Random();
                engine = Engine
                        .builder(RoutingProtocol::fitness, CODEC)
                        .maximizing()
                        .populationSize(POPULATION_SIZE)
                        .alterers(
                                new SingleNodeCrossover<>(CROSSOVER_RATE),
                                new Mutator<>(MUTATION_RATE))
                        .constraint(CONSTRAINT)
                        .build();
                //System.out.println(engine.offspringSelector());
                //System.out.println(engine.survivorsSelector());
                result = engine.stream()
                        .limit(Limits.bySteadyFitness(STEADY_FITNESS))
                        .limit(MAX_GENERATIONS)
                        .peek(statistics)
                        .peek(er -> WriteBestIndividual(er.bestPhenotype().genotype().gene().eval(), er.generation(),Tree.toString(er.bestPhenotype().genotype().gene())))
                        .peek(er -> WriteFitnessStatistics(er.bestFitness(), er.generation(), er.population()))
                        .collect(EvolutionResult.toBestGenotype())
                        .gene();
            }

            File statisticsFile = new File("./results/" + CURRENT_RUN + ".txt");

            try {
                FileWriter writer = new FileWriter(statisticsFile);
                writer.write(statistics.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
