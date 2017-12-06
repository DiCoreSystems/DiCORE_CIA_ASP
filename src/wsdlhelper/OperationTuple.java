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
        this.name = name;
        this.input = input;
        this.output = output;
        this.fault = fault;
    }


}
