package parser;

import file.WSDLDocument;

/**
 * Created by CSZ on 07.11.2017.
 * This class takes care of reading WSDL files and translating them to logic programs.
 */
public class WSDLParser
{
    public boolean parse(WSDLDocument document){
        return true;
    }

    // TODO: Better do this with RegExes
    public String getSubstring(String s, String start, String end){
        s = s.substring(s.indexOf(start) + 1);
        s = s.substring(0, s.indexOf(end));

        return s;
    }
}
