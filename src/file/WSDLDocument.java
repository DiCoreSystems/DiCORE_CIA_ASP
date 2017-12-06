package file;

import wsdlhelper.*;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by CSZ on 07.11.2017.
 */
public class WSDLDocument {
    private File path;
    private ArrayList<MessageTuple> messages;
    private ArrayList<TypeTriple> types;

    public WSDLDocument(String path){
        this.path = new File(path);
    }

    public File getPath() {
        return path;
    }

    public void addMessage(MessageTuple message){
        messages.add(message);
    }

    public void addType(TypeTriple type){
        types.add(type);
    }

    public ArrayList<MessageTuple> getMessages() {
        return messages;
    }

    public ArrayList<TypeTriple> getTypes() {
        return types;
    }
}
