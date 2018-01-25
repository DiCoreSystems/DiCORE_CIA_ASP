package wsdlhelper;

public class OperationTuple {
    private String name;
    private String input;
    private String output;
    private String fault;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
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

    public OperationTuple(String name, String input, String output){
        this.name =  name.replace(":", "_");
        this.input = input.replace(":", "_");
        this.output = output.replace(":", "_");
        this.fault = null;
    }

    public OperationTuple(String name, String input, String output, String fault){
        this.name =  name.replace(":", "_");
        this.input = input.replace(":", "_");
        this.output = output.replace(":", "_");
        this.fault =  fault.replace(":", "_");
    }
}
