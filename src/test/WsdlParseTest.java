package test;

import junit.framework.TestCase;
import parser.WsdlParser;

import java.nio.file.Paths;
import java.util.Arrays;

public class WsdlParseTest extends TestCase {
    public void testListOperations() throws Exception {
        String configPath = Paths.get("text").normalize().toString();
        String[] expected = { "GetLastTradePrice", "GetLastTradePrice" };
        String[] actual = new WsdlParser().listOperations(configPath + "/sample1.wsdl");
        assertEquals(expected, actual);
    }

    public void testListOperationsUnique() throws Exception {
        String configPath = Paths.get("text").normalize().toString();
        String[] expected = { "GetLastTradePrice" };
        assertEquals(expected, new WsdlParser().listOperationsUnique(configPath + "/sample1.wsdl"));
    }

    public static <E> void assertEquals(E[] a1, E[] a2) {
        assertEquals(Arrays.toString(a1), Arrays.toString(a2));
    }
}
