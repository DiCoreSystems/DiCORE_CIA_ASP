package wsdlhelper;

public class TypeTriple {
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


    public TypeTriple(String name, String type, TypeAttribute attribute){
        this.name = name;
        this.type = type;
        this.attribute = attribute;
    }
}
