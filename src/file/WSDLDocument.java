package file;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by CSZ on 07.11.2017.
 */
public class WSDLDocument {
    private File path;
    private ArrayList<String> types;

    public WSDLDocument(String path){
        this.path = new File(path);
    }

    public File getPath() {
        return path;
    }

    public void setTypes(ArrayList<String> types){
        this.types = types;
    }

    public ArrayList<String> getTypes() {
        return types;
    }
}
