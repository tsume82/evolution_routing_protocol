import java.util.ArrayList;

public class GPelements {
    ArrayList<ProgramNode> components;
    String protocol;

    public GPelements(String protocol){
        this.protocol = protocol;
        this.components = new ArrayList<>();
        components.add(new ProgramNode("if", 2, "corpus", new String[]{"condition", "corpus"}));
        components.add(new ProgramNode("or", 2, "condition", new String[]{"condition", "condition"}));
        components.add(new ProgramNode("not", 1, "condition", new String[]{"condition"}));
        components.add(new ProgramNode("notEqual", 2, "condition", new String[]{"condition", "condition"}));
        components.add(new ProgramNode("add", 2, "corpus", new String[]{"corpus", "corpus"}));
        components.add(new ProgramNode("update", 0, "corpus"));
        components.add(new ProgramNode("isTransferring", 0, "condition"));
        components.add(new ProgramNode("canStartTransfer", 0, "condition"));
        components.add(new ProgramNode("exchangeDeliverableMessages", 0, "corpus")); // it was set to condition but it returns a non-boolean value
        components.add(new ProgramNode("tryAllMessagesToAllConnections", 0, "corpus"));
        // components.add(new ProgramNode("null", 0, "condition"));
        components.add(new ProgramNode("return", 0, "corpus"));

        if (this.protocol.equals("prophet")){
            components.add(new ProgramNode("tryOtherMessages", 0, "corpus"));
        }
    }

    public ArrayList<String> getElementsByChildrenType(Integer figli, String type){
        ArrayList<String> list = new ArrayList<>();
        for (ProgramNode component : this.components) {
            if (component.getChildren().equals(figli) && component.getType().equals(type)) {
                list.add(component.getName());
            }
        }
        return list;
    }

    public ProgramNode getNodeByName(String name){
        for (ProgramNode current : this.components) {
            if (current.getName().equals(name)) {
                return current;
            }
        }
        return null;
    }

    public String getTypeByNodeName(String name){
        for (ProgramNode current : this.components) {
            if (current.getName().equals(name)) {
                return current.getType();
            }
        }
        return null;
    }

    public String getOperationFunction(Object[] v, String name){
        switch (name){
            case "or": return "(" + v[0] + " || " + v[1] + ")";
            case "not": return "!" + v[0];
            case "if": return "if(" + v[0] + "){" + v[1] + "}";
            case "notEqual": return "(" + v[0] + " != " + v[1] + ")";
            case "add": return v[0] + "\n" + v[1];
            case "update": return "super.update();";
            case "isTransferring": return "isTransferring()";
            case "canStartTransfer": return "canStartTransfer()";
            case "exchangeDeliverableMessages": return "exchangeDeliverableMessages();";
            case "tryAllMessagesToAllConnections": return "this.tryAllMessagesToAllConnections();";
            case "null": return "null";
            case "return": return "return;";
            case "tryOtherMessages": return "tryOtherMessages();";
        }
        return "";
    }

}
