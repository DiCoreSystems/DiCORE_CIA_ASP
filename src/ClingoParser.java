import java.io.*;

/**
 * Created by CSZ on 26.06.2017.
 */
public class ClingoParser {
    // This class runs a given ASP program via command and checks the result output for errors.
    // If Clingo detects an error or the resulting logical program is not satisfiable,
    // all changes will be discarded and all changes are undone.

    public boolean run(File targetFile){
        String filePath = targetFile.getAbsolutePath();

        Runtime rt = Runtime.getRuntime();
        try {
            Process exec = rt.exec("clingo " + filePath + " 0");

            StringBuilder s = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream(), "UTF-8"));
            while(true){
                String str = br.readLine();
                if(str != null){
                    s.append(str + "\n");
                } else {
                    break;
                }
            }
            br.close();

            String message = s.toString();

            System.out.println(message);

            // UNSATISFIABLE: Our graph is completely invalid and has to be discarded.
            // Parsing Failed: Syntax error in our listing.

            if(message.contains("UNSATISFIABLE") || message.contains("parsing failed")) {
                // TODO: Discard the change.
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
