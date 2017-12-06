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
        WSDLDocument document = new WSDLDocument(configPath + "/sample2.wsdl");
        WsdlParser parser = new WsdlParser();
        try {
            parser.parse(document);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
