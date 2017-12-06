package parser;

import file.WSDLDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
            if(item == null || item.getNodeName().equals("#text")){
                continue;
            }

            if(item.getNodeName().equals("message")){
                for (int j = 0; j < item.getChildNodes().getLength(); j++){
                    Node item2 = item.getChildNodes().item(i);
                    if(item2 == null || item2.getNodeName().equals("#text")){
                        continue;
                    }

                    if(item2.getNodeName().equals("part")){
                        item2.getAttributes().getNamedItem("name").getNodeValue();
                        item2.getAttributes().getNamedItem("type").getNodeValue();
                    }
                }
            }

            if(item.getNodeName().equals("portType")){
                for (int j = 0; j < item.getChildNodes().getLength(); j++){
                    Node item2 = item.getChildNodes().item(i);
                    if(item2 == null || item2.getNodeName().equals("#text")){
                        continue;
                    }

                    if(item2.getNodeName().equals("operation")){
                        for (int k = 0; k < item2.getChildNodes().getLength(); k++){
                            Node item3 = item2.getChildNodes().item(i);
                            if(item3 == null || item3.getNodeName().equals("#text")){
                                continue;
                            }

                            if(item3.getNodeName().equals("input")){
                                item3.getAttributes().getNamedItem("message").getNodeValue();
                            }

                            if(item3.getNodeName().equals("output")){
                                item3.getAttributes().getNamedItem("message").getNodeValue();
                            }
                        }
                    }
                }
            }

            if(item.getNodeName().equals("binding")){
                item.getAttributes().getNamedItem("name").getNodeValue();
            }

            if(item.getNodeName().equals("service")){
                item.getAttributes().getNamedItem("name").getNodeValue();
            }
        }
    }
}
