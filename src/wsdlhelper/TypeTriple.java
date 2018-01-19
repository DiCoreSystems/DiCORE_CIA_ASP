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
        this.name = firstLetterLowerCase(name.replace(":", "-"));
        this.type = firstLetterLowerCase(type.replace(":", "-"));
        this.attribute = attribute;
    }


    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = firstLetterLowerCase(parent);
    }

    private String firstLetterLowerCase(String string){
        char c[] = string.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        String result = new String(c);
        return result;
    }
}
