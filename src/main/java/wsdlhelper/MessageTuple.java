package wsdlhelper;

public class MessageTuple {
    private String messageName;
    private String part;

    //----------------------------------

    public MessageTuple(String messageName, String part){
        this.messageName = messageName;
        this.part =  part.replace(":", "_");
    }

    //----------------------------------

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    //----------------------------------

    public String getPart() {
        return part;
    }

    //----------------------------------

}
