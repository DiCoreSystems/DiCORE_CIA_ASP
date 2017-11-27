package test;

import parser.WsdlParser;
import file.WSDLDocument;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by CSZ on 07.11.2017.
 */
public class InterfaceTest {

    @Test
    public void ParserTest(){
        String configPath = Paths.get("text").normalize().toString();
        WSDLDocument document = new WSDLDocument(configPath + "/WSDLtest.wsdl");
        WsdlParser parser = new WsdlParser();
        try {
            String[] operations = parser.listOperationsUnique(document.getPath().getAbsolutePath());
            for(String op_name: operations){
                System.out.println(op_name);
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
