package wsdlhelper;

public class MessageTuple {
    private String message;
    private String part;

    public MessageTuple(String message, String part){
        this.message = firstLetterLowerCase(message);
        this.part =  firstLetterLowerCase(part.replace(":", "*"));
    }


    public String getMessage() {
        return message;
    }

    public String getPart() {
        return part;
    }

    private String firstLetterLowerCase(String string){
        char c[] = string.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        String result = new String(c);
        return result;
    }
}
