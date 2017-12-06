package wsdlhelper;

public class MessageTuple {
    private String message;
    private String part;

    public MessageTuple(String message, String part){
        this.message = message;
        this.part = part;
    }


    public String getMessage() {
        return message;
    }

    public String getPart() {
        return part;
    }
}
