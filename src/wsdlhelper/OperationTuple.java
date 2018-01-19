package wsdlhelper;

public class OperationTuple {
    private String name;
    private String input;
    private String output;
    private String fault;

    public String getName() {
        return name;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public String getFault() {
        return fault;
    }

    public OperationTuple(String name, String input, String output, String fault){
        this.name =  firstLetterLowerCase(name);
        this.input =  firstLetterLowerCase(input);
        this.output =  firstLetterLowerCase(output);
        this.fault =  firstLetterLowerCase(fault);
    }

    private String firstLetterLowerCase(String string){
        if(string == null){
            return string;
        }
        char c[] = string.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        String result = new String(c);
        return result;
    }
}
