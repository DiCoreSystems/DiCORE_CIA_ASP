package transDiagram;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jan Brümmer on 10.06.2017.
 * This class represents the final step in our conversion.  All actions and states in our WorkflowGraph are
 * now directly translated to ASP code.
 */
public class TransitionDiagram {
    private final List<Fluent> fluents;
    private final List<Action> actions;
    private final List<State> states;
    private final List<State> startingStates;
    private final File configFile = new File("config.txt");
    private File f;

    public TransitionDiagram(List<Fluent> fluents, List<Action> actions, List<State> states,
                                List<State> startingStates) {
        this.fluents = fluents;
        this.actions = actions;
        this.states = states;
        this.startingStates = startingStates;
    }

    public File createASPCode(){

        FileReader fr;
        try {
            fr = new FileReader(configFile);
            BufferedReader r = new BufferedReader(fr);
            String configPath = r.readLine();
            f = new File(configPath + "/new.lp");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        if(!(f.exists() && !f.isDirectory())){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }



        try {
            FileWriter w = new FileWriter(f);
            // Step 1: DEFINITION OF FLUENTS
            // For this example graph I'm treating all fluents as inertial
            // But it is necessary to check if a fluent is really inertial or not.

            int n = this.getStates().size();
            w.write("#const n = " + n + ".\n");
            w.write("step(0..n).\n");

            w.write("\n");

            for(Fluent fluent: fluents){
                String name;
                if(fluent.getValue()){
                    name = fluent.getName();
                } else {
                    name = fluent.getNegation().getName();
                }

                if(fluent.isInertial()){
                    w.write("fluent(inertial," + name + ").\n");

                    // INERTIA AXIOM FOR FLUENTS
                    w.write("holds(" + name + ",I+1) :- \n" +
                            "           fluent(inertial," + name + "), \n" +
                            "           holds(" + name + ",I),\n" +
                            "           not -holds(" + name + ",I+1), step(I).\n");

                    w.write("-holds(" + name + ",I+1) :- \n" +
                            "           fluent(inertial," + name + "), \n" +
                            "           -holds(" + name + ",I),\n" +
                            "           not holds(" + name + ",I+1), step(I).\n");
                } else {
                    w.write("fluent(defined," + name + ").\n");

                    // CWA FOR FLUENTS
                    w.write("-holds(" + name + ",I+1) :- \n" +
                            "           fluent(defined," + name + "), \n" +
                            "           not holds(" + name + ",I).\n");
                }

                w.write("\n");
            }



            // DEFINITION OF ACTIONS.
            for(Action a: actions){
                w.write("action(" + a.getName() +").\n");

                // CWA FOR ACTIONS
                w.write("-occurs(" + a.getName() +",I) :-" +
                        " not occurs(" + a.getName() + ",I), step(I).\n");

                w.write("\n");
            }

            // Step 3: Get all Fluents from all starting states
            // and define their holds-Attribute for timestamp 0.
            for(State start: startingStates){
                for(Fluent fluent: start.getFluents()) {
                    if(fluent.getValue()){
                        w.write("holds(" + fluent.getName() + ",0).\n");
                    } else {
                        w.write("-holds(" + fluent.getName() + ",0).\n");
                    }
                }
            }

            w.write("\n");

            int i = 0;
            List<State> statesToVisit = new ArrayList<>();
            List<State> nextVisit = new ArrayList<>();
            List<State> visitedStates = new ArrayList<>();

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
                        w.write("holds(" + state.getName() + ",T+1) :- \n");
                        w.write("           occurs(do" + state.getName() + ", T).\n\n");
                    } else {
                        for(Action a: state.getIngoingActions()){
                            w.write("holds(" + state.getName() + ",T+1) :- \n");
                            w.write("           holds(" + a.getStartState().getName() + ",T),\n");
                            w.write("          -holds(" + state.getName() + ",T),\n");
                            w.write("           occurs(do" + state.getName() + ",T).\n\n");
                        }
                    }

                    for(Action a: state.getOutgoingActions()){
                        nextVisit.add(a.getEndState());
                    }

                    visitedStates.add(state);

                    w.write("occurs(do" + state.getName() + "," + i + ").\n\n");
                }

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
}
