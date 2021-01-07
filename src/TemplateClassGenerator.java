public class TemplateClassGenerator {
    private static String imports = "package routing;\nimport core.Settings;\n";
    private static String endClass = "}";

    public static String GenerateTemplateEpidemic(String className) {
        String startClass = buildStartClass(className);
        String constructors = buildConstructorsEpidemic(className);
        String replicate = buildReplicateEpidemic(className);
        String update = buildUpdateEpidemic("");
        return imports + startClass + constructors + update + replicate + endClass;
    }

    public static String GenerateTemplateEpidemic(String className, String update) {
        String startClass = buildStartClass(className);
        String constructors = buildConstructorsEpidemic(className);
        String replicate = buildReplicateEpidemic(className);
        update = buildUpdateEpidemic(update);
        return imports + startClass + constructors + update + replicate + endClass;
    }

    public static String GenerateTemplateProphet(String className, String update) {
        String imports = "package routing;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.Collection;\n" +
                "import java.util.Collections;\n" +
                "import java.util.Comparator;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.List;\n" +
                "import java.util.Map;\n" +
                "import routing.util.RoutingInfo;\n" +
                "import util.Tuple;\n" +
                "import core.Connection;\n" +
                "import core.DTNHost;\n" +
                "import core.Message;\n" +
                "import core.Settings;\n" +
                "import core.SimClock;\n";
        String startClass = buildStartClass(className);
        String parameters = "public static final double P_INIT = 0.75;\n" +
                "\tpublic static final double DEFAULT_BETA = 0.25;\n" +
                "\tpublic static final double DEFAULT_GAMMA = 0.98;\n" +
                "\tpublic static final String PROPHET_NS = \"ProphetRouter\";\n" +
                "\tpublic static final String SECONDS_IN_UNIT_S =\"secondsInTimeUnit\";\n" +
                "\tpublic static final String BETA_S = \"beta\";\n" +
                "\tpublic static final String GAMMA_S = \"gamma\";\n" +
                "\tprivate int secondsInTimeUnit;\n" +
                "\tprivate double beta;\n" +
                "\tprivate double gamma;\n" +
                "\tprivate Map<DTNHost, Double> preds;\n" +
                "\tprivate double lastAgeUpdate;";

        String constructors = buildConstructorsProphet(className);
        String methods = buildMethodsProphet(className);
        update = buildUpdateProphet(update);

        return imports + startClass + parameters + constructors + methods + update + endClass;
    }

    private static String buildStartClass(String className) {
        return "public class " + className + " extends ActiveRouter{ \n";
    }

    private static String buildConstructorsEpidemic(String className){
        return "\tpublic " + className + "(Settings s) {\n" +
                "\t\tsuper(s);\n" +
                "\t}\n" +
                "\tprotected " + className + "(" + className + " r) {\n" +
                "\t\tsuper(r);\n" +
                "\t}\n";
    }

    private static String buildConstructorsProphet(String className){
        String res = "\tpublic " + className + "(Settings s) {\n" +
                "\t\tsuper(s);\n" +
                "\t\tSettings prophetSettings = new Settings(PROPHET_NS);\n" +
                "\t\tsecondsInTimeUnit = prophetSettings.getInt(SECONDS_IN_UNIT_S);\n" +
                "\t\tif (prophetSettings.contains(BETA_S)) {\n" +
                "\t\t\tbeta = prophetSettings.getDouble(BETA_S);\n" +
                "\t\t}\n" +
                "\t\telse {\n" +
                "\t\t\tbeta = DEFAULT_BETA;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tif (prophetSettings.contains(GAMMA_S)) {\n" +
                "\t\t\tgamma = prophetSettings.getDouble(GAMMA_S);\n" +
                "\t\t}\n" +
                "\t\telse {\n" +
                "\t\t\tgamma = DEFAULT_GAMMA;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tinitPreds();\n" +
                "\t}\n" +
                "\tprotected " + className + "(" + className + " r) {\n" +
                "\t\tsuper(r);\n" +
                "\t\tthis.secondsInTimeUnit = r.secondsInTimeUnit;\n" +
                "\t\tthis.beta = r.beta;\n" +
                "\t\tthis.gamma = r.gamma;\n" +
                "\t\tinitPreds();\n" +
                "\t}\n";
        return res;
    }

    private static String buildMethodsProphet(String className){
        String res = "\tprivate void initPreds() {\n" +
                "\t\tthis.preds = new HashMap<DTNHost, Double>();\n" +
                "\t}\n" +
                "\n" +
                "\t@Override\n" +
                "\tpublic void changedConnection(Connection con) {\n" +
                "\t\tsuper.changedConnection(con);\n" +
                "\n" +
                "\t\tif (con.isUp()) {\n" +
                "\t\t\tDTNHost otherHost = con.getOtherNode(getHost());\n" +
                "\t\t\tupdateDeliveryPredFor(otherHost);\n" +
                "\t\t\tupdateTransitivePreds(otherHost);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\tprivate void updateDeliveryPredFor(DTNHost host) {\n" +
                "\t\tdouble oldValue = getPredFor(host);\n" +
                "\t\tdouble newValue = oldValue + (1 - oldValue) * P_INIT;\n" +
                "\t\tpreds.put(host, newValue);\n" +
                "\t}\n" +
                "public double getPredFor(DTNHost host) {\n" +
                "\t\tageDeliveryPreds();\n" +
                "\t\tif (preds.containsKey(host)) {\n" +
                "\t\t\treturn preds.get(host);\n" +
                "\t\t}\n" +
                "\t\telse {\n" +
                "\t\t\treturn 0;\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\tprivate void updateTransitivePreds(DTNHost host) {\n" +
                "\t\tMessageRouter otherRouter = host.getRouter();\n" +
                "\t\tassert otherRouter instanceof " + className + " : \"PRoPHET only works \" +\n" +
                "\t\t\t\" with other routers of same type\";\n" +
                "\n" +
                "\t\tdouble pForHost = getPredFor(host); \n" +
                "\t\tMap<DTNHost, Double> othersPreds =\n" +
                "\t\t\t((" + className + ")otherRouter).getDeliveryPreds();\n" +
                "\n" +
                "\t\tfor (Map.Entry<DTNHost, Double> e : othersPreds.entrySet()) {\n" +
                "\t\t\tif (e.getKey() == getHost()) {\n" +
                "\t\t\t\tcontinue; \n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\tdouble pOld = getPredFor(e.getKey()); \n" +
                "\t\t\tdouble pNew = pOld + ( 1 - pOld) * pForHost * e.getValue() * beta;\n" +
                "\t\t\tpreds.put(e.getKey(), pNew);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\tprivate void ageDeliveryPreds() {\n" +
                "\t\tdouble timeDiff = (SimClock.getTime() - this.lastAgeUpdate) /\n" +
                "\t\t\tsecondsInTimeUnit;\n" +
                "\t\tif (timeDiff == 0) {\n" +
                "\t\t\treturn;\n" +
                "\t\t}\n" +
                "\t\tdouble mult = Math.pow(gamma, timeDiff);\n" +
                "\t\tfor (Map.Entry<DTNHost, Double> e : preds.entrySet()) {\n" +
                "\t\t\te.setValue(e.getValue()*mult);\n" +
                "\t\t}\n" +
                "\t\tthis.lastAgeUpdate = SimClock.getTime();\n" +
                "\t}\n" +
                "\tprivate Map<DTNHost, Double> getDeliveryPreds() {\n" +
                "\t\tageDeliveryPreds();\n" +
                "\t\treturn this.preds;\n" +
                "\t}\n" +
                "\tprivate Tuple<Message, Connection> tryOtherMessages() {\n" +
                "\t\tList<Tuple<Message, Connection>> messages =\n" +
                "\t\t\tnew ArrayList<Tuple<Message, Connection>>();\n" +
                "\t\tCollection<Message> msgCollection = getMessageCollection();\n" +
                "\t\tfor (Connection con : getConnections()) {\n" +
                "\t\t\tDTNHost other = con.getOtherNode(getHost());\n" +
                "\t\t\t" + className + " othRouter = (" + className + ")other.getRouter();\n" +
                "\n" +
                "\t\t\tif (othRouter.isTransferring()) {\n" +
                "\t\t\t\tcontinue; \n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\tfor (Message m : msgCollection) {\n" +
                "\t\t\t\tif (othRouter.hasMessage(m.getId())) {\n" +
                "\t\t\t\t\tcontinue;\n" +
                "\t\t\t\t}\n" +
                "\t\t\t\tif (othRouter.getPredFor(m.getTo()) > getPredFor(m.getTo())) {\n" +
                "\t\t\t\t\tmessages.add(new Tuple<Message, Connection>(m,con));\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tif (messages.size() == 0) {\n" +
                "\t\t\treturn null;\n" +
                "\t\t}\n" +
                "\t\tCollections.sort(messages, new TupleComparator());\n" +
                "\t\treturn tryMessagesForConnected(messages);\n" +
                "\t}\n" +
                "\tprivate class TupleComparator implements Comparator\n" +
                "\t\t<Tuple<Message, Connection>> {\n" +
                "\t\tpublic int compare(Tuple<Message, Connection> tuple1,\n" +
                "\t\t\t\tTuple<Message, Connection> tuple2) {\n" +
                "\t\t\tdouble p1 = ((" + className + ")tuple1.getValue().\n" +
                "\t\t\t\t\tgetOtherNode(getHost()).getRouter()).getPredFor(\n" +
                "\t\t\t\t\ttuple1.getKey().getTo());\n" +
                "\t\t\tdouble p2 = ((" + className + ")tuple2.getValue().\n" +
                "\t\t\t\t\tgetOtherNode(getHost()).getRouter()).getPredFor(\n" +
                "\t\t\t\t\ttuple2.getKey().getTo());\n" +
                "\t\t\tif (p2-p1 == 0) {\n" +
                "\t\t\t\treturn compareByQueueMode(tuple1.getKey(), tuple2.getKey());\n" +
                "\t\t\t}\n" +
                "\t\t\telse if (p2-p1 < 0) {\n" +
                "\t\t\t\treturn -1;\n" +
                "\t\t\t}\n" +
                "\t\t\telse {\n" +
                "\t\t\t\treturn 1;\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\n" +
                "\t@Override\n" +
                "\tpublic RoutingInfo getRoutingInfo() {\n" +
                "\t\tageDeliveryPreds();\n" +
                "\t\tRoutingInfo top = super.getRoutingInfo();\n" +
                "\t\tRoutingInfo ri = new RoutingInfo(preds.size() +\n" +
                "\t\t\t\t\" delivery prediction(s)\");\n" +
                "\t\tfor (Map.Entry<DTNHost, Double> e : preds.entrySet()) {\n" +
                "\t\t\tDTNHost host = e.getKey();\n" +
                "\t\t\tDouble value = e.getValue();\n" +
                "\t\t\tri.addMoreInfo(new RoutingInfo(String.format(\"%s : %.6f\",\n" +
                "\t\t\t\t\thost, value)));\n" +
                "\t\t}\n" +
                "\t\ttop.addMoreInfo(ri);\n" +
                "\t\treturn top;\n" +
                "\t}\n" +
                "\n" +
                "\t@Override\n" +
                "\tpublic MessageRouter replicate() {\n" +
                "\t\t" + className + " r = new " + className + "(this);\n" +
                "\t\treturn r;\n" +
                "\t}";
        return res;
    }

    private static String buildUpdateProphet(String update) {
        String res = "\t@Override\n" +
                "\tpublic void update() {\n"
                + "\t\tsuper.update();\n";
        if (!update.equals("")){
            res += update;
        } else {
            res = res + "\t\tsuper.update();\n" +
                    "\t\tif (!canStartTransfer() ||isTransferring()) {\n" +
                    "\t\t\treturn;\n" +
                    "\t\t}\n" +
                    "\t\tif (exchangeDeliverableMessages() != null) {\n" +
                    "\t\t\treturn;\n" +
                    "\t\t}\n" +
                    "\t\ttryOtherMessages();";
        }
        res += "\t}\n";
        return res;
    }

    private static String buildUpdateEpidemic(String update) {
        String res = "\t@Override\n" +
                "\tpublic void update() {\n"
                + "\t\tsuper.update();\n";
        if (!update.equals("")){
            res += update;
        } else {
            res = res + "\t\tsuper.update();\n" +
                    "\t\tif (isTransferring() || !canStartTransfer()) {\n" +
                    "\t\t\treturn; // transferring, don't try other connections yet\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\tif (exchangeDeliverableMessages() != null) {\n" +
                    "\t\t\treturn; // started a transfer, don't try others (yet)\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\tthis.tryAllMessagesToAllConnections();\n";
        }
        res += "\t}\n" +
                "\n" +
                "\n";
        return res;
    }

    private static String buildReplicateEpidemic(String className) {
        return "\t@Override\n" +
                "\tpublic " + className + " replicate() {\n" +
                "\t\treturn new " + className + "(this);\n" +
                "\t}\n";
    }
}
