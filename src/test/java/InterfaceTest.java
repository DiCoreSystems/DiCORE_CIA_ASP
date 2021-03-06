import file.WSDLTranslator;
import parser.ClingoRunner;
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

    private final String configPath = System.getProperty("user.dir") + "\\text";

    @Test
    public void Sample1Test(){
        WSDLDocument document = new WSDLDocument(configPath + "/sample1.wsdl");
        WSDLParser parser = new WSDLParser();
        File f = null;
        try {
            parser.parse(document, true);

            assertEquals(2, document.getMessages().size());
            assertEquals(1, document.getOperations().size());
            assertEquals(6, document.getTypes().size());

            f = new WSDLTranslator().translate(document);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        assertNotNull(f);

        ClingoRunner clingo = new ClingoRunner();
        clingo.checkIfSatisfiable(f, false);
    }

    @Test
    public void Sample2Test(){
        WSDLDocument document = new WSDLDocument(configPath + "/sample2.wsdl");
        WSDLParser parser = new WSDLParser();
        try {
            parser.parse(document, true);

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

    @Test
    public void Sample3Test(){
        WSDLDocument document = new WSDLDocument(configPath + "/sample3.wsdl");
        WSDLParser parser = new WSDLParser();
        try {
            parser.parse(document, true);

            new WSDLTranslator().translate(document);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCharacterReplacement(){
        String namespace = "http://example.com/stockquote.wsdl";
        String expectedResult = "http*58**47**47*example*46*com*47*stockquote*46*wsdl";

        WSDLParser parser = new WSDLParser();
        String result = parser.replaceSpecialCharacters(namespace);

        assertEquals("", expectedResult, result);
    }
}
