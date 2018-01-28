package transDiagram;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jan Brümmer on 10.06.2017.
 * This class represents the final step in our conversion.  All actions and states in our WorkflowGraph are
 * now directly translated to ASP code.
 */
public class TransitionDiagram {
    private final String DEFAULT_FILE_PATH = "../ASP/logic_programs/new.lp";
    private final List<Fluent> fluents;
    private final List<Action> actions;
    private final List<State> states;
    private final List<State> startingStates;
    private File f;
    private String prefix = "_n_";
    private String constant = randomChar();

    private String randomChar() {
        // TODO: BAD!! VERY BAD!!!
        int rand = 97 + (int)(Math.random() * ((122 - 97) + 1));
        char result = (char) rand;
        return Character.toString(result);
    }

    public TransitionDiagram(List<Fluent> fluents, List<Action> actions, List<State> states,
                                List<State> startingStates) {
        this.fluents = fluents;
        this.actions = actions;
        this.states = states;
        this.startingStates = startingStates;
    }

    public void setFilePath(String filePath){
        f = new File(filePath);

        if(!(f.exists() && !f.isDirectory())){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File createASPCode(){
        System.out.println(constant);

        if(f == null){
            setFilePath(DEFAULT_FILE_PATH);
        }

        try {
            FileWriter w = new FileWriter(f);
            // Step 1: DEFINITION OF FLUENTS
            // For this example graph I'm treating all fluents as inertial
            // But it is necessary to check if a fluent is really inertial or not.

            int n = this.getStates().size();
            w.write("#const " + constant + " = " + n + ".\n");
            w.write("step(0.." + constant + ").\n");

            w.write("\n");

            for(Fluent fluent: fluents){
                String name = fluent.getName();

                if(fluent.isInertial()){
                    w.write(prefix + "fluent(inertial, " + name + ").\n");

                    // INERTIA AXIOM FOR FLUENTS
                    w.write(prefix + "holds(" + name + ",I+1) :- \n" +
                            "           " + prefix + "fluent(inertial, " + name + "), \n" +
                            "           " + prefix + "holds(" + name + ",I),\n" +
                            "           not -" + prefix + "holds(" + name + ",I+1), step(I).\n");

                    w.write("-" + prefix + "holds(" + name + ",I+1) :- \n" +
                            "           " + prefix + "fluent(inertial, " + name + "), \n" +
                            "           -" + prefix + "holds(" + name + ",I),\n" +
                            "           not " + prefix + "holds(" + name + ",I+1), step(I).\n");
                } else {
                    w.write("" + prefix + "fluent(defined" + name + ").\n");

                    // CWA FOR FLUENTS
                    w.write("-" + prefix + "holds(" + name + ",I+1) :- \n" +
                            "           " + prefix + "fluent(defined" + name + "), \n" +
                            "           not holds(" + name + ",I), step(I).\n");
                }

                w.write("\n");
            }



            // DEFINITION OF ACTIONS.
            for(Action a: actions){
                w.write("" + prefix + "action(" + a.getName() +").\n");

                // CWA FOR ACTIONS
                w.write("-" + prefix + "occurs(" + a.getName() +",I) :-" +
                        " not " + prefix + "occurs(" + a.getName() + ",I), step(I).\n");

                w.write("\n");
            }

            // Step 3: Get all Fluents from all starting states
            // and define their holds-Attribute for timestamp 0.
            for(State start: startingStates){
                for(Fluent fluent: start.getFluents()) {
                    if(fluent.getValue()){
                        w.write("" + prefix + "holds(" + fluent.getName() + ",0).\n");
                    } else {
                        w.write("-" + prefix + "holds(" + fluent.getName() + ",0).\n");
                    }
                }
            }

            w.write("\n");

            // Step 4: Search through the graph via broad search.
            // Write ASP Codeblocks for each state and its prede- and successors.

            int i = 0;
            List<State> statesToVisit = new ArrayList<>();
            List<State> nextVisit = new ArrayList<>();
            List<State> visitedStates = new ArrayList<>();

            // Get all successors of our artificial start state.
            for(State startState: startingStates){
                for(Action startAction: startState.getOutgoingActions()){
                    statesToVisit.add(startAction.getEndState());
                }
                states.remove(startState);
            }

            while(true){
                for(State state: statesToVisit){
                    if(visitedStates.contains(state)){
                        continue;
                    }

                    // Standard ASP Codeblock for each predecessor (causal law).
                    if(state.getIngoingActions().isEmpty()){
                        w.write("" + prefix + "holds(" + state.getName() + ",T+1) :- \n");
                        w.write("           " + prefix + "occurs(do" + state.getName() + ", T).\n\n");
                    } else {
                        for(Action predecessorAction: state.getIngoingActions()){
                            w.write("" + prefix + "holds(" + state.getName() + ",T+1) :- \n");

                            // The state "start" is merely used as an orientation where the workflow starts.
                            // It has no further impact on the workflow.
                            if(!predecessorAction.getStartState().getName().equals("start")){
                                w.write("           " + prefix + "holds(" + predecessorAction.getStartState().getName() + ",T),\n");
                            }

                            w.write("          -" + prefix + "holds(" + state.getName() + ",T),\n");
                            w.write("           " + prefix + "occurs(do" + state.getName() + ",T).\n\n");
                        }
                    }

                    for(Action a: state.getOutgoingActions()){
                        nextVisit.add(a.getEndState());
                    }

                    visitedStates.add(state);

                    w.write("" + prefix + "occurs(do" + state.getName() + "," + i + ").\n\n");
                }

                // If nextVisit is empty
                if(nextVisit.isEmpty()){
                    break;
                }

                for(State newState: nextVisit){
                    statesToVisit.add(newState);
                }

                nextVisit.clear();
                i++;
            }

            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    public List<State> getStates() {
        return states;
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Fluent> getFluents() {
        return fluents;
    }

    public File createASPCodeWithoutPrefix() {
        this.prefix = "";
        return createASPCode();
    }
}
