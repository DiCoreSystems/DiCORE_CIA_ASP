package file;

import wsdlhelper.MessageTuple;
import wsdlhelper.OperationTuple;
import wsdlhelper.TypeTriple;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by CSZ on 14.12.2017.
 */
public class WSDLTranslator {

    public void translate(WSDLDocument document){
        translate(document, "../ASP/logic_programs/interface/newFile.txt");
    }

    public void translate(WSDLDocument document, String fileName){
        File f = new File(fileName);

        try {
            FileWriter w = new FileWriter(f);

            String service = document.getService();

            w.write("service(" + service + ").\n");

            String binding = document.getBinding();

            w.write("binding(" + binding + ").\n");

            //operation(O) :- operation(O, I, Out, F), input(O, I, T1), output(O, Out, T2), fault(O, F, T3).
            //operation(O) :- operation(O, I, Out), input(O, I, T1), output(O, Out, T2).
            for(OperationTuple o: document.getOperations()){
                w.write("operation(" + o.getName() + ", " + o.getInput() + ", " + o.getOutput() + ").\n");
            }

            for (TypeTriple t: document.getTypes()){
                switch(t.getAttribute()){
                    case NORMAL:
                        w.write("type(" + t.getName() + ", " + t.getType() + ").\n");
                        break;
                    case INPUT:
                        w.write("input(" + t.getParent() + ", " + t.getName() + ", " + t.getType() + ").\n");
                        break;
                    case OUTPUT:
                        w.write("output(" + t.getParent() + ", " + t.getName() + ", " + t.getType() + ").\n");
                        break;
                    case FAULT:
                        w.write("fault(" + t.getParent() + ", " + t.getName() + ", " + t.getType() + ").\n");
                        break;
                    default:
                        System.out.println("Your type is not defined.");
                }
            }

            // message(M) :- messagePart(M, P), type(P, T).
            for (MessageTuple m: document.getMessages()){
                w.write("messagePart(" + m.getMessage() + ", " + m.getPart() + ").\n");
            }

            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
