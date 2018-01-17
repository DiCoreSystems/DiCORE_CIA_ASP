package parser;

import file.WSDLDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import wsdlhelper.MessageTuple;
import wsdlhelper.OperationTuple;
import wsdlhelper.TypeAttribute;
import wsdlhelper.TypeTriple;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;

public class WSDLParser {
    Document d;

    public void parse(WSDLDocument document) throws SAXException, IOException,
            ParserConfigurationException{
        d = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new FileInputStream(document.getPath().getAbsolutePath()));
        d.
        Element docElement = d.getDocumentElement();
        NodeList docElementChildNodes = docElement.getChildNodes();
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
                                String partName = null;
                                String partType = null;
                                String partElement = null;
                                TypeTriple part;

                                if(item2.getAttributes().getNamedItem("name") != null){
                                    partName = item2.getAttributes().getNamedItem("name").getNodeValue();
                                }

                                if(item2.getAttributes().getNamedItem("type") != null){
                                    partType = item2.getAttributes().getNamedItem("type").getNodeValue();
                                }

                                if(item2.getAttributes().getNamedItem("element") != null){
                                    partElement = item2.getAttributes().getNamedItem("element").getNodeValue();
                                }

                                MessageTuple message = new MessageTuple(messageName, partName);
                                // TODO: Check for partName = null
                                if(partType == null){
                                    part = new TypeTriple(partName, partElement, TypeAttribute.NORMAL);
                                } else {
                                    part = new TypeTriple(partName, partType, TypeAttribute.NORMAL);
                                }
                                document.addMessage(message);
                                if(part != null){
                                    document.addType(part);
                                }
                            }
                        }
                    }
                    break;

                case "types":
                    // TODO: Parse Schema.
                    for(int j = 0; j < docElement.getElementsByTagName("complexType").getLength(); j++){
                        Node item2 = docElement.getElementsByTagName("complexType").item(j);

                        if(item2 == null || item2.getNodeName().equals("#text")){
                            System.out.println("null");
                            continue;
                        }

                        String parentName = item2.getParentNode().getAttributes()
                                .getNamedItem("name").getNodeValue();
                        String elementName = null;
                        String elementType = null;

                        // Get the child node "element" of the complex type by going through node "all"
                        Node complexTypeElement = item2.getChildNodes().item(1).getChildNodes().item(1);

                        if(complexTypeElement.getAttributes().getNamedItem("name") != null){
                            elementName = complexTypeElement.getAttributes().getNamedItem("name").getNodeValue();
                        }

                        if(complexTypeElement.getAttributes().getNamedItem("type") != null){
                            elementType = complexTypeElement.getAttributes().getNamedItem("type").getNodeValue();
                        }

                        TypeTriple parent = new TypeTriple(parentName, elementName, TypeAttribute.NORMAL);
                        TypeTriple child = new TypeTriple(elementName, elementType, TypeAttribute.NORMAL);

                        document.addType(parent);
                        document.addType(child);
                    }

                case "portType":
                    for (int j = 0; j < item.getChildNodes().getLength(); j++){
                        Node item2 = item.getChildNodes().item(j);
                        if(item2 == null || item2.getNodeName().equals("#text")){
                            System.out.println("null");
                            continue;
                        }

                        System.out.println(item2.getNodeName());

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

                                if(item3.getNodeName().equals("input")){
                                    if(item3.hasAttributes()) {
                                        String inputName = item3.getAttributes().getNamedItem("message").getNodeValue();
                                        input = new TypeTriple(inputName, inputName, TypeAttribute.INPUT);
                                        input.setParent(op_name);
                                    }
                                }

                                if(item3.getNodeName().equals("output")){
                                    if(item3.hasAttributes()) {
                                        String outputName = item3.getAttributes().getNamedItem("message").getNodeValue();
                                        output = new TypeTriple(outputName, outputName, TypeAttribute.OUTPUT);
                                        output.setParent(op_name);
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
}
