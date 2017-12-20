package file;

import java.io.*;
import java.nio.file.Paths;

/**
 * Created by Jan Brümmer on 10.10.2017
 * This class takes care of the version management by saving the last 3 workflows that have been added by the
 * WorkflowGraph class. It is triggered after the ClingoParser has parsed the new file and compared it to the most
 * recent workflow. If the new workflow is either unsatisfiable or indifferent to the most recent workflow, then
 * nothing will be saved and all changes (if there are any) will be discarded.
 */
public class VersionManager {
    private final String configPath = Paths.get(".").normalize().toString() + "/logic_programs";
    private final String file1 = configPath + "/Workflow/current.lp";
    private final String file2 = configPath + "/Workflow/previous1.lp";
    private final String file3 = configPath + "/Workflow/previous2.lp";
    private final String file4 = configPath + "/Workflow/previous3.lp";

    public void saveNewFile(File newFile) throws IOException {
        overwriteFiles(file3, file4);
        overwriteFiles(file2, file3);
        overwriteFiles(file1, file2);
        overwriteFiles(newFile.getPath().replace("\"", ""), file1);
    }

    private void overwriteFiles(String newFile, String oldFile) throws IOException {
        if(!new File(newFile).exists() && !new File(oldFile).exists()) {
            System.out.println("Beide Dateien existieren nicht.");
            return;
        }
        FileWriter fw;
        BufferedReader bufferedReader;

        fw = new FileWriter(oldFile, false);
        StringBuilder builder = new StringBuilder();
        bufferedReader = new BufferedReader(new FileReader(newFile));

        while(true){
            String str = bufferedReader.readLine();
            if(str != null){
                // The _n_ prefix is used for comparison. It serves no purpose in a saved file.
                str.replace("_n_", "");
                builder.append(str + "\n");
            } else {
                break;
            }
        }

        fw.write(builder.toString());

        fw.close();
        bufferedReader.close();
    }

}
