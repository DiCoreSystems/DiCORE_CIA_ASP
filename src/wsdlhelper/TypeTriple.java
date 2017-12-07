package wsdlhelper;

public class TypeTriple {
    private String parent;
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
        this.name = name;
        this.type = type;
        this.attribute = attribute;
    }


    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
