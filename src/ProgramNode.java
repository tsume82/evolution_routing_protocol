public class ProgramNode {
    private String name;
    private Integer children;
    private String type;
    private String[] childrenType;

    public ProgramNode(String name, Integer children, String type){
        this.setName(name);
        this.setChildren(children);
        this.setType(type);
    }

    public ProgramNode(String name, Integer children, String type, String[] childrenType){
        this.setName(name);
        this.setChildren(children);
        this.setType(type);
        this.setChildrenType(childrenType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getChildrenType() {
        return childrenType;
    }

    public void setChildrenType(String[] childrenType) {
        this.childrenType = childrenType;
    }
}
