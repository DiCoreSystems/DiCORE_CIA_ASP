package parser;

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

    private final File domainsFile = new File("\"C:/Users/Jan/Documents/Arbeit/ASP/logic programs/domains-test.lp\"");
    private final File diffFile = new File("\"C:/Users/Jan/Documents/Arbeit/ASP/logic programs/diff.lp\"");
    private final File currentFile = new File("\"C:/Users/Jan/Documents/Arbeit/ASP/logic programs/current.lp\"");

    public boolean checkIfSatisfiable(File targetFile){
        Runtime rt = Runtime.getRuntime();

        try {
            Process exec = rt.exec("clingo " + targetFile +  " 0");

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
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public String getUniverse(File targetFile){
        Runtime rt = Runtime.getRuntime();

        try {
            Process exec = rt.exec("clingo-python " + targetFile + " " + domainsFile + " 0");
            boolean ready = false;

            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream(), "UTF-8"));
            while(true){
                String str = br.readLine();

                if(str != null){

                    if(str.contains("universe")){
                        ready = true;
                    }

                    if(str.contains("UNKNOWN")){
                        ready = false;
                    }

                    if(ready){
                        stringBuilder.append(str + "\n");
                    }

                } else {
                    break;
                }
            }
            br.close();

            String message = stringBuilder.toString();

            System.out.println(message);

            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDifferences(File targetFile){
        Runtime rt = Runtime.getRuntime();

        try {
            Process exec = rt.exec("clingo-python " + targetFile + " " + currentFile + " " + diffFile +" 0");

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

            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
