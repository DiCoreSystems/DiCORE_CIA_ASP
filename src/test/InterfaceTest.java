package test;

import file.WSDLTranslator;
import parser.ClingoParser;
import parser.WSDLParser;
import file.WSDLDocument;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by CSZ on 07.11.2017.
 */
public class InterfaceTest {

    String configPath = Paths.get("text").normalize().toString();

    @Test
    public void Sample1Test(){
        WSDLDocument document = new WSDLDocument(configPath + "/sample1.wsdl");
        WSDLParser parser = new WSDLParser();
        File f = null;
        try {
            parser.parse(document);

            assertEquals(document.getMessages().size(), 2);
            assertEquals(document.getOperations().size(), 1);
            assertEquals(document.getTypes().size(),6);

            f = new WSDLTranslator().translate(document);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        assertNotNull(f);

        ClingoParser clingo = new ClingoParser();
        assertTrue(clingo.checkIfSatisfiable(f, false));
    }

    @Test
    public void Sample2Test(){
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
