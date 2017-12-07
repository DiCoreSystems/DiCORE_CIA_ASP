package file;

import wsdlhelper.*;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by CSZ on 07.11.2017.
 */
public class WSDLDocument {
    private File path;
    private ArrayList<MessageTuple> messages = new ArrayList<>();
    private ArrayList<TypeTriple> types = new ArrayList<>();
    private ArrayList<OperationTuple> operations = new ArrayList<>();
    private String binding;
    private String service;

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

    public void addOperation(OperationTuple operation){
        operations.add(operation);
    }

    public ArrayList<MessageTuple> getMessages() {
        return messages;
    }

    public ArrayList<TypeTriple> getTypes() {
        return types;
    }

    public ArrayList<OperationTuple> getOperations() {
        return operations;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }
}
