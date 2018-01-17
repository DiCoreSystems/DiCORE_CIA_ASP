package parser;

import java.io.*;
import java.nio.file.Paths;

/**
 * Created by CSZ on 26.06.2017.
 * The ClingoRunner runs the new logic program and depending on the result, we either discard all changes
 * or we overwrite our current logic program with this.
 */
public class ClingoRunner {
    // This class runs a given ASP program via command and checks the result output for errors.
    // If Clingo detects an error or the resulting logical program is not satisfiable,
    // all changes will be discarded and all changes are undone.

    private final String configPath = System.getProperty("user.dir") + "\\logic_programs";
    private File domainsFile = new File(configPath + "/domains.lp");
    private File workflowDiffFile = new File(configPath + "/Workflow/diff.lp");
    private File currentFile = new File(configPath + "/current.lp");
    private File interfaceDefaultFile = new File(configPath + "/Interface/default.lp");
    private File interfaceDiffFile = new File(configPath + "/Interface/diff.lp");

    public boolean checkIfSatisfiable(File targetFile, boolean workflow){
        Runtime rt = Runtime.getRuntime();

        try {
            Process exec;

            if(System.getProperty("os.name").startsWith("Windows")){
                targetFile = new File("\"" + targetFile.getAbsolutePath() + "\"");
                interfaceDefaultFile = new File("\"" + configPath + "/Interface/default.lp\"");
            }

            if(workflow){
                String command = "clingo " + targetFile + " 0";
                System.out.println("The command is: " + command);
                exec = rt.exec(command);
            } else {
                String command = "clingo " + targetFile + " " + interfaceDefaultFile + " 0";
                System.out.println("The command is: " + command);
                exec = rt.exec(command);
            }

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

        if(System.getProperty("os.name").startsWith("Windows")){
            domainsFile = new File("\"" + configPath + "/domains.lp\"");
        }

        try {
            Process exec = rt.exec("clingo-python " + targetFile + " " + domainsFile + " 0");
            boolean ready = false;

            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream(), "UTF-8"));
            while(true){
                String str = br.readLine();

                if(str != null){
                    // TODO: Use regex if possible
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

    public String getDifferences(File targetFile, boolean workflow){
        Runtime rt = Runtime.getRuntime();

        if(System.getProperty("os.name").startsWith("Windows")){
            if(workflow) {
                workflowDiffFile = new File("\"" + configPath + "/Workflow/diff.lp\"");
            } else {
                interfaceDiffFile = new File("\"" + configPath + "/Interface/diff.lp\"");
            }


        }

        try {
            Process exec;

            if(workflow){
                exec = rt.exec("clingo-python " + targetFile + " " + currentFile + " " + workflowDiffFile +" 0");
            } else {
                exec = rt.exec("clingo-python " + targetFile + " " + interfaceDiffFile +
                        " " + interfaceDefaultFile + " 0");
            }


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
