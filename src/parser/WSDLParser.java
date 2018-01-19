package parser;

import file.WSDLDocument;
import javafx.util.Pair;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import wsdlhelper.MessageTuple;
import wsdlhelper.OperationTuple;
import wsdlhelper.TypeAttribute;
import wsdlhelper.TypeTriple;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WSDLParser {
    Document d;
    HashMap<String, String> documentMap;

    public void parse(WSDLDocument document) throws SAXException, IOException,
            ParserConfigurationException{


        d = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new FileInputStream(document.getPath().getAbsolutePath()));
        Element docElement = d.getDocumentElement();
        NodeList docElementChildNodes = docElement.getChildNodes();

        //Get all defined namespaces
        NamedNodeMap definitions = docElement.getAttributes();
        for(int i = 0; i < definitions.getLength(); i++){
            String nodeName = definitions.item(i).getNodeName();
            if(nodeName.contains(":")){
                nodeName = nodeName.substring(nodeName.indexOf(":") + 1);
            }

            String nodeValue = definitions.item(i).getNodeValue();
            nodeValue = replaceSpecialCharacters(nodeValue);

            document.getNamespaceMap().put(nodeName, nodeValue);
        }

        documentMap = document.getNamespaceMap();

        for (int i = 0; i < docElementChildNodes.getLength(); i++){
            Node item = docElementChildNodes.item(i);

            switch(item.getNodeName()){
                case "#text": break;



                case "message":
                    String messageName = item.getAttributes().getNamedItem("name").getNodeValue();

                    for (int j = 0; j < item.getChildNodes().getLength(); j++){
                        Node item2 = item.getChildNodes().item(j);
                        if(item2 == null || item2.getNodeName().equals("#text")){
                            continue;
                        }

                        if(item2.getNodeName().equals("part")){
                            if(item2.hasAttributes()){
                                String partType = null;
                                String partElement = null;

                                if(item2.getAttributes().getNamedItem("type") != null){
                                    partType = item2.getAttributes().getNamedItem("type").getNodeValue();
                                    partType = determineNamespace(partType);
                                }

                                if(item2.getAttributes().getNamedItem("element") != null){
                                    partElement = item2.getAttributes().getNamedItem("element").getNodeValue();
                                    partElement = determineNamespace(partElement);
                                }

                                MessageTuple message;
                                // TODO: Check for partName = null
                                if(partType == null){
                                    message = new MessageTuple(messageName, partElement);
                                } else {
                                    message = new MessageTuple(messageName, partType);
                                }
                                document.addMessage(message);
                            }
                        }
                    }
                    break;



                case "types":
                    Node schema = item.getChildNodes().item(1);
                    String namespace = schema.getAttributes().getNamedItem("targetNamespace").getNodeValue();
                    namespace = replaceSpecialCharacters(namespace);

                    for(int j = 0; j < docElement.getElementsByTagName("complexType").getLength(); j++){
                        Node item2 = docElement.getElementsByTagName("complexType").item(j);

                        if(item2 == null || item2.getNodeName().equals("#text")){
                            continue;
                        }

                        String parentName = item2.getParentNode().getAttributes()
                                .getNamedItem("name").getNodeValue();

                        String elementName = null;
                        String elementType = null;

                        // Get the child node "element" of the complex type by going through node "all"

                        // TODO: Complex Types need a more detailed approach than this.
                        Node complexTypeElement = item2.getChildNodes().item(1).getChildNodes().item(1);

                        if(complexTypeElement.getAttributes().getNamedItem("name") != null){
                            elementName = complexTypeElement.getAttributes().getNamedItem("name").getNodeValue();
                        }

                        if(complexTypeElement.getAttributes().getNamedItem("type") != null){
                            elementType = complexTypeElement.getAttributes().getNamedItem("type").getNodeValue();
                        }

                        TypeTriple parent = new TypeTriple(namespace + "*" + parentName,
                                namespace + "*" + elementName, TypeAttribute.NORMAL);
                        TypeTriple child = new TypeTriple(namespace + "*" + elementName,
                                namespace + "*" + elementType, TypeAttribute.NORMAL);

                        document.addType(parent);
                        document.addType(child);
                    }



                case "portType":
                    for (int j = 0; j < item.getChildNodes().getLength(); j++){
                        Node item2 = item.getChildNodes().item(j);
                        if(item2 == null || item2.getNodeName().equals("#text")){
                            continue;
                        }

                        // Get the Operation Node
                        if(item2.getNodeName().equals("operation")){
                            String op_name = item2.getAttributes().getNamedItem("name").getNodeValue();
                            TypeTriple input = new TypeTriple();
                            TypeTriple output = new TypeTriple();
                            TypeTriple fault = new TypeTriple();
                            for (int k = 0; k < item2.getChildNodes().getLength(); k++){
                                Node item3 = item2.getChildNodes().item(k);
                                if(item3 == null || item3.getNodeName().equals("#text")){
                                    continue;
                                }

                                // Find the input and output of the operation
                                if(item3.getNodeName().equals("input")){
                                    if(item3.hasAttributes()) {
                                        String inputName = item3.getAttributes().getNamedItem("message").getNodeValue();
                                        inputName = determineNamespace(inputName);
                                        input = new TypeTriple(inputName, inputName, TypeAttribute.INPUT);
                                        input.setParent(op_name);
                                        document.addType(input);
                                    }
                                }

                                if(item3.getNodeName().equals("output")){
                                    if(item3.hasAttributes()) {
                                        String outputName = item3.getAttributes().getNamedItem("message").getNodeValue();
                                        outputName = determineNamespace(outputName);
                                        output = new TypeTriple(outputName, outputName, TypeAttribute.OUTPUT);
                                        output.setParent(op_name);
                                        document.addType(output);
                                    }
                                }
                            }

                            OperationTuple operation = new OperationTuple(op_name, input.getName(),
                                    output.getName(), fault.getName());
                            document.addOperation(operation);
                        }
                    }
                    break;



                case "binding":
                    String binding = item.getAttributes().getNamedItem("name").getNodeValue();
                    document.setBinding(binding);
                    break;



                case "service":
                    String service = item.getAttributes().getNamedItem("name").getNodeValue();
                    document.setService(service);
                    break;
            }
        }
    }

    // There are certain characters in a URl that cause errors in ASP, such as ':' or '/'
    // We need to find these special characters and replace them with their ASCII-code.
    public String replaceSpecialCharacters(String namespace){
        String result = namespace;
        Pattern p = Pattern.compile("[:/.]");
        Matcher m = p.matcher(namespace);

        while (m.find()) {
            short asciiIndex = (short) m.group().charAt(0);
            result = result.replace(m.group(), "*" + Short.toString(asciiIndex) + "*");
        }

        return result;
    }

    // This method replaces the unique identifier of a namespace (e.g. xsd1) with the namespace URL.
    private String determineNamespace(String variable){
        if(variable.contains(":")){
            String namespace = variable.substring(0, variable.indexOf(":"));
            variable = variable.substring(variable.indexOf(":") + 1);
            namespace = findNameSpaceID(namespace);
            variable = namespace + "*" + variable;
        }

        return variable;
    }

    public String findNameSpaceID(String identifier){
        String namespace = documentMap.get(identifier);
        return namespace;
    }
}
