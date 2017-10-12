package parser;

import file.VersionManager;

import java.io.*;

/**
 * Created by CSZ on 26.06.2017.
 * The ClingoParser runs the new logic program and depending on the result, we either discard all changes
 * or we overwrite our current logic program with this.
 */
public class ClingoParser {
    // This class runs a given ASP program via command and checks the result output for errors.
    // If Clingo detects an error or the resulting logical program is not satisfiable,
    // all changes will be discarded and all changes are undone.
    private final File currentFile = new File("\"C:/Users/Jan/Documents/Arbeit/ASP/logic programs/current.lp\"");
    private final File domainsFile = new File("\"C:/Users/Jan/Documents/Arbeit/ASP/logic programs/domains-test.lp\"");

    public String run(File targetFile){
        Runtime rt = Runtime.getRuntime();

        try {
            Process exec = rt.exec("clingo-python " + targetFile + " " + domainsFile + " 0");

            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream(), "UTF-8"));
            while(true){
                String str = br.readLine();
                if(str != null){
                    stringBuilder.append(str + "\n");
                } else {
                    break;
                }
            }
            br.close();

            String message = stringBuilder.toString();

            System.out.println(message);

            // UNSATISFIABLE: Our graph is completely invalid and has to be discarded.
            // Parsing Failed: Syntax error in our listing.
            // UNKNOWN

            if(message.contains("UNSATISFIABLE") || message.contains("parsing failed") ||
                    message.contains("UNKNOWN")) {
                System.out.println("Warning: Something with your file is wrong. Please check it.");
            }

            return message;
            //TODO: Find differences
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
