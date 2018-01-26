package parser;


import org.lorislab.clingo4j.api.*;

import java.io.*;

/**
 * Created by CSZ on 26.06.2017.
 * The ClingoRunner runs the new logic program and depending on the result, we either discard all changes
 * or we overwrite our current logic program with this.
 */
public class ClingoRunner {
    // This class runs a given ASP program via command and checks the result output for errors.
    // If Clingo detects an error or the resulting logical program is not satisfiable,
    // all changes will be discarded and all changes are undone.

    private final String configPath = System.getProperty("user.dir") + "/logic_programs";
    private File workflowDiffFile = new File(configPath + "/Workflow/diff.lp");
    private File currentFile = new File(configPath + "/current.lp");
    private File interfaceDefaultFile = new File(configPath + "/Interface/default.lp");
    private File interfaceDiffFile = new File(configPath + "/Interface/diff.lp");

    public void checkIfSatisfiable(File targetFile, boolean workflow){
        Clingo.init("../clingo4j/src/main/clingo");

        try (Clingo control = Clingo.create()) {
            System.out.println("Clingo Ver. " + control.getVersion());
            control.add("base", extractLogicProgram(targetFile));

            if(!workflow){
                control.add("base", extractLogicProgram(interfaceDefaultFile));
            }

            control.ground("base");

            try (SolveHandle handle = control.solve()) {
                for (Model model : handle)  {
                    System.out.println("Model type: " + model.getType());
                    for (Symbol atom : model.getSymbols()) {
                        System.out.println(atom);
                    }
                }
            }
        } catch (ClingoException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void getDifferences(File targetFile, boolean workflow){
        getDifferences(targetFile, currentFile, workflow);
    }

    private String extractLogicProgram(File file){
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while(true){
                String str = br.readLine();
                if(str != null){
                    stringBuilder.append(str + "\n");
                } else {
                    break;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * In order for this to work properly, one of these files should be created with a prefix and one without.
     * @param file1
     * @param file2
     * @param workflow True if you're running a workflow file, false otherwise.
     */

    public void getDifferences(File file1, File file2, boolean workflow) {
        Clingo.init("../clingo4j/src/main/clingo");

        try (Clingo control = Clingo.create()) {
            System.out.println("Clingo Ver. " + control.getVersion());
            control.add("base", extractLogicProgram(file1));
            control.add("base", extractLogicProgram(file2));

            if(!workflow){
                control.add("base", extractLogicProgram(interfaceDefaultFile));
                control.add("base", extractLogicProgram(interfaceDiffFile));
            } else {
                control.add("base", extractLogicProgram(workflowDiffFile));
            }

            control.ground("base");

            try (SolveHandle handle = control.solve()) {
                for (Model model : handle)  {
                    System.out.println("Model type: " + model.getType());
                    for (Symbol atom : model.getSymbols()) {
                        System.out.println(atom);
                    }
                }
            }
        } catch (ClingoException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
