package wsdlhelper;

public class TypeTriple {
    private OperationTuple parent;
    private String name;
    private String type;
    private TypeAttribute attribute;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public TypeAttribute getAttribute() {
        return attribute;
    }

    public TypeTriple(){
        this.parent = null;
        this.name = null;
        this.type = null;
        this.attribute = TypeAttribute.FAULT;
    }

    public TypeTriple(String name, String type, TypeAttribute attribute){
        this.name = name.replace(":", "-");
        this.type = type.replace(":", "-");
        this.attribute = attribute;
    }


    public OperationTuple getParent() {
        return parent;
    }

    public void setParent(OperationTuple parent) {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }
}
