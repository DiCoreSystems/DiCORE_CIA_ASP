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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class WsdlParser {
    Document d;

    public void parse(WSDLDocument document) throws SAXException, IOException,
            ParserConfigurationException{
        d = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new FileInputStream(document.getPath().getAbsolutePath()));
        Element docElement = d.getDocumentElement();
        NodeList docElementChildNodes = docElement.getChildNodes();
        for (int i = 0; i < docElementChildNodes.getLength(); i++){
            Node item = docElementChildNodes.item(i);
            System.out.println("Debug: " + item.getNodeName());

            switch(item.getNodeName()){
                case "#text": break;

                case "message":
                    String messageName = item.getAttributes().getNamedItem("name").getNodeValue();
                    for (int j = 0; j < item.getChildNodes().getLength(); j++){
                        Node item2 = item.getChildNodes().item(j);
                        if(item2 == null || item2.getNodeName().equals("#text")){
                            System.out.println("null");
                            continue;
                        }

                        System.out.println(item2.getNodeName());

                        if(item2.getNodeName().equals("part")){
                            // TODO: Catch those pesky nulls.
                            String partName = item2.getAttributes().getNamedItem("name").getNodeValue();
                            String partType = item2.getAttributes().getNamedItem("type").getNodeValue();
                            MessageTuple message = new MessageTuple(messageName, partName);
                            TypeTriple part = new TypeTriple(partName, partType, TypeAttribute.NORMAL);
                            document.addMessage(message);
                            document.addType(part);
                        }
                    }
                    break;

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
                                    String inputName = item3.getAttributes().getNamedItem("message").getNodeValue();
                                    input = new TypeTriple(inputName, inputName, TypeAttribute.INPUT);
                                    input.setParent(op_name);
                                }

                                if(item3.getNodeName().equals("output")){
                                    String outputName = item3.getAttributes().getNamedItem("message").getNodeValue();
                                    output = new TypeTriple(outputName, outputName, TypeAttribute.OUTPUT);
                                    output.setParent(op_name);
                                }
                            }

                            OperationTuple operation = new OperationTuple(op_name, input.getName(), output.getName(), fault.getName());
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
