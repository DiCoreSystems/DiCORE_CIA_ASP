package file;

import wsdlhelper.MessageTuple;
import wsdlhelper.OperationTuple;
import wsdlhelper.Service;
import wsdlhelper.TypeTriple;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by CSZ on 14.12.2017.
 * This class translates every information stored in our WSDLDocument directly into ASP.
 */
public class WSDLTranslator {
    private final String configPath = System.getProperty("user.dir") + "\\logic_programs";

    public File translate(WSDLDocument document){
        return translate(document, configPath + "\\Interface\\newFile.lp");
    }

    public File translate(WSDLDocument document, String fileName){
        File f = new File(fileName);

        try {
            FileWriter w = new FileWriter(f);

            Service service = document.getService();

            if(service != null){
                if(service.getServiceBinding() != null){
                    w.write("service(" + firstLetterLowerCase(service.getServiceName()) +
                            ", " + firstLetterLowerCase(service.getServiceBinding()) + ").\n");
                } else {
                    w.write("service(" + firstLetterLowerCase(service.getServiceName()) + ").\n");
                }
            }

            for(String binding: document.getBindings()){
                w.write("binding(" + firstLetterLowerCase(binding) + ").\n");
            }

            for(OperationTuple o: document.getOperations()){
                w.write("operation(" + o.getName() + ", " + o.getInput() + ", " + o.getOutput() + ").\n");
            }

            for (TypeTriple t: document.getTypes()){
                switch(t.getAttribute()){
                    case NORMAL:
                        w.write("type(" + firstLetterLowerCase(t.getName()) +
                                ", " + firstLetterLowerCase(t.getType()) + ").\n");
                        break;
                    case INPUT:
                        w.write("input(" + firstLetterLowerCase(t.getParent().getName())
                                + ", " + firstLetterLowerCase(t.getName()) +
                                ", " + firstLetterLowerCase(t.getType()) + ").\n");
                        break;
                    case OUTPUT:
                        w.write("output(" + firstLetterLowerCase(t.getParent().getName())
                                + ", " + firstLetterLowerCase(t.getName())
                                + ", " + firstLetterLowerCase(t.getType()) + ").\n");
                        break;
                    case FAULT:
                        w.write("fault(" + firstLetterLowerCase(t.getParent().getName()) +
                                ", " + firstLetterLowerCase(t.getName()) +
                                ", " + firstLetterLowerCase(t.getType()) + ").\n");
                        break;
                    default:
                        System.out.println("Your type is not defined.");
                }
            }

            for (MessageTuple m: document.getMessages()){
                w.write("messagePart(" + firstLetterLowerCase(m.getMessageName()) + ", " +
                        firstLetterLowerCase(m.getPart()) + ").\n");
            }

            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return f;
    }

    private String firstLetterLowerCase(String string){
        char c[] = string.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        String result = new String(c);
        return result;
    }
}
