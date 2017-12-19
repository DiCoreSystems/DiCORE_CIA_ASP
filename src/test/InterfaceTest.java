package test;

import file.WSDLTranslator;
import parser.WSDLParser;
import file.WSDLDocument;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Paths;

import static junit.framework.Assert.assertEquals;

/**
 * Created by CSZ on 07.11.2017.
 */
public class InterfaceTest {

    @Test
    public void ParserTest(){
        String configPath = Paths.get("text").normalize().toString();
        WSDLDocument document = new WSDLDocument(configPath + "/sample2.wsdl");
        WSDLParser parser = new WSDLParser();
        try {
            parser.parse(document);

            assertEquals(document.getMessages().size(), 2);
            assertEquals(document.getOperations().size(), 1);
            assertEquals(document.getTypes().size(),2);

            new WSDLTranslator().translate(document);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
