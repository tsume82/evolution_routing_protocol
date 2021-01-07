import java.io.*;

public class DynamicClassGeneration {

    private String updateCode = "";

    public DynamicClassGeneration(){}

    public DynamicClassGeneration(String code){
        this.updateCode = code;
    }


    public double executeTheOne(String scenarioName, int numberHost, String mapName, String baseProtocol) {
        Float result = (float)0;
        String scenarioSettings = scenarioName + "settings";
        String scenarioRouter = scenarioName + "Router";
        try {
            File settingsFile = null;
            settingsFile = new File("./the-one/example_settings/"+scenarioSettings + ".txt");

            String settingsCode = "Scenario.name = " + scenarioName +
                    "\nGroup.router = " + scenarioRouter + "\nGroup.nrofHosts = " + String.valueOf(numberHost);

            if (mapName.equals("helsinki")){
                settingsCode += "\nMapBasedMovement.nrofMapFiles = 1" + "\nMovementModel.worldSize = 100000, 100000" +
                        "\nMapBasedMovement.mapFile1 = data/HelsinkiMedium/roads.wkt" +
                        "\nGroup4.routeFile = data/HelsinkiMedium/A_bus.wkt" +
                        "\nGroup5.routeFile = data/HelsinkiMedium/B_bus.wkt" +
                        "\nGroup6.routeFile = data/HelsinkiMedium/C_bus.wkt";
            } else if (mapName.equals("manhattan")) {
                settingsCode += "\nMapBasedMovement.nrofMapFiles = 1" + "\nMovementModel.worldSize = 100000, 100000" +
                        "\nMapBasedMovement.mapFile1 = data/Manhattan/roads.wkt" +
                        "\nGroup4.routeFile = data/Manhattan/bus.wkt" +
                        "\nGroup5.routeFile = data/Manhattan/bus.wkt" +
                        "\nGroup6.routeFile = data/Manhattan/bus.wkt";
            }

            if (numberHost == 100) {
                settingsCode += "\nEvents1.hosts = 0,306";
            } else if (numberHost == 40) {
                settingsCode += "\nEvents1.hosts = 0,126";
            }

            if (baseProtocol.equals("prophet")) {
                settingsCode += "\nProphetRouter.secondsInTimeUnit = 30";
            }

            FileWriter sett_writer = new FileWriter(settingsFile);
            sett_writer.write(settingsCode);
            sett_writer.close();

            File sourceFile = new File("./the-one/src/routing/" + scenarioRouter + ".java");

            String sourceCode = "";
            if (baseProtocol.equals("epidemic")){
                sourceCode = TemplateClassGenerator.GenerateTemplateEpidemic(scenarioRouter, this.updateCode);
            }
            else if (baseProtocol.equals("prophet")) {
                sourceCode = TemplateClassGenerator.GenerateTemplateProphet(scenarioRouter, this.updateCode);
            }

            // write the source code into the source file
            FileWriter writer = new FileWriter(sourceFile);
            writer.write(sourceCode);
            writer.close();

            File tempScript = createTempScript(scenarioRouter,scenarioSettings);

            ProcessBuilder pb = new ProcessBuilder("bash", tempScript.toString());
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
            //process.getOutputStream();
            System.out.println("Simulation terminated");

            // InputStream error = process.getErrorStream();
            // String errLine = "" + error.read();
            // System.out.println(errLine);
            File resultReport = new File("./the-one/reports/"+scenarioName+"_MessageStatsReport.txt");

            if (resultReport.exists()){
                BufferedReader br = new BufferedReader(new FileReader(resultReport));
                String st;
                boolean cont = true;
                while ((st = br.readLine()) != null && cont) {
                    String[] fields = st.split(":");
                    if (fields[0].equals("delivery_prob")){
                        result = Float.valueOf(fields[1]);
                        cont = false;
                    }
                    // System.out.println(fields[0]);
                }
                resultReport.delete();
            }
            if (sourceFile.exists()){
                sourceFile.delete();
            }
            File classFile = new File("./the-one/target/routing/" + scenarioRouter + ".class");

            if (classFile.exists()){
                classFile.delete();
            }
            File reportFile = new File("./the-one/reports/"+scenarioName+"_ContactTimesReport.txt");

            if (reportFile.exists()){
                reportFile.delete();
            }
            if (settingsFile.exists()){
                settingsFile.delete();
            }
            tempScript.delete();
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private File createTempScript(String scenarioRouter, String scenarioSettings) throws IOException {
        File tempScript = File.createTempFile("script", null);

        Writer streamWriter = new OutputStreamWriter(new FileOutputStream(
                tempScript));
        PrintWriter printWriter = new PrintWriter(streamWriter);

        printWriter.println("#!/bin/bash");
        printWriter.println("cd ./the-one/src");

        printWriter.println("javac -d ../target ./routing/"+scenarioRouter+".java");
        printWriter.println("cd ..");
        printWriter.println("./one.sh -b 1 ./example_settings/"+scenarioSettings+".txt");

        printWriter.close();

        return tempScript;
    }
}


