package file;

import java.io.File;

/**
 * Created by CSZ on 07.11.2017.
 */
public class WSDLDocument {
    private final File path;

    public WSDLDocument(String path){
        this.path = new File(path);
    }

    public File getPath() {
        return path;
    }
}
