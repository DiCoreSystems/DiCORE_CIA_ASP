package test;

import file.WSDLDocument;
import org.junit.Assert;
import org.junit.Test;
import parser.WSDLParser;

import java.nio.file.Paths;

/**
 * Created by CSZ on 07.11.2017.
 */
public class InterfaceTest {

    private final String configPath = Paths.get(".").normalize().toString() + "/text";

    @Test
    public void ParserTest(){
        WSDLDocument document = new WSDLDocument(configPath + "/WSDLtest.txt");
        WSDLParser parser = new WSDLParser();
        Assert.assertTrue(parser.parse(document));
    }
}
