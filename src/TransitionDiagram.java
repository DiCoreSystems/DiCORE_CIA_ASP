import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CSZ on 10.06.2017.
 */
public class TransitionDiagram {
    private final List<Fluent> fluents;
    private final List<Action> actions;
    private final List<State> states;
    //private final List<State> startingStates;
    private File f;

    public TransitionDiagram(List<Fluent> fluents, List<Action> actions, List<State> states) {
        this.fluents = fluents;
        this.actions = actions;
        this.states = states;
        //this.startingStates = getStartingStates(states);
    }

    /*public void createASPCode(){

        f = new File("../ASP/output.lp");

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
            // It may be necessary to check if a fluent is really inertial or not.
            for(Fluent fluent: fluents){
                if(fluent.isInertial()){
                    w.write("fluent(inertial," + fluent.getName() + ").\n");
                } else {
                    w.write("fluent(defined," + fluent.getName() + ").\n");
                }
            }

            w.write("\n");

            // DEFINITION OF ACTIONS.
            for(Action a: actions){
                a.getStartState().getActions().add(a);
                w.write("action(" + a.getName() +").\n");
            }

            w.write("\n");

            // CWA AND INERTIA AXIOM FOR FLUENTS
            for(Fluent fluent: fluents){
                if(fluent.isInertial()){
                    w.write("holds(" + fluent.getName() + ",i+1) :- " +
                            "fluent(inertial," + fluent.getName() + "), " +
                            "holds(" + fluent.getName() + ",i)," +
                            "not -holds(" + fluent.getName() + ",i+1), i < n.");

                    w.write("-holds(" + fluent.getName() + ",i+1) :- " +
                            "fluent(inertial," + fluent.getName() + "), " +
                            "-holds(" + fluent.getName() + ",i)," +
                            "not holds(" + fluent.getName() + ",i+1), i < n.");
                } else {
                    w.write("-holds(" + fluent.getName() + ",i+1) :- " +
                            "fluent(defined," + fluent.getName() + "), " +
                            "not holds(" + fluent.getName() + ",i).");
                }
            }

            // CWA FOR ACTIONS
            for(Action a: actions){
                w.write("-occurs(" + a.getName() +",I) :-" +
                        " not occurs(" + a.getName() + ",I).");
            }

            // Step 3: Get all Fluents from all starting states
            // and define their holds-Attribute for timestamp 0.
            for(State start: startingStates){
                for(Fluent fluent: start.getFluents()) {
                    w.write("holds(" + fluent.getName() + ",0).\n");
                }
            }

            w.write("\n");

            // CAUSAL LAW DEFINITION
            for(Action a: actions){
                State s1 = a.getStartState();
                State s2 = a.getEndState();
                //TODO: I made it... with too many for loops, is horrible.
                //Get Fluents before action
                for (Fluent s_fluent: s1.getFluents()){
                    String f_name = s_fluent.getName().replaceAll("-", "");
                    // Get Fluents after action
                    for(Fluent e_fluent: s2.getFluents()){
                        String e_name = e_fluent.getName().replaceAll("-", "");
                        if(e_name.equals(f_name)){
                            if(s_fluent.getValue() != e_fluent.getValue()){
                                //We've detected a change triggered by this action.
                                //Write the specified code for it.
                                w.write("holds(" + s_fluent.getName() + ",t+1) :- ");
                                for(Fluent s_Fluent2: s1.getFluents()) {
                                    w.write("holds(" + s_Fluent2.getName() + ",t),\n");
                                }
                                w.write("occurs(" + a.getName() + ",t).\n\n");
                            }
                        }
                    }
                }
            }

            // Step 5 (Final): Go through our graph and define an occurs-attribute
            // for each action to a given timestep.

            int i = 0;
            List<State> statesToVisit = startingStates;
            while(true){
                List<State> nextVisit = new ArrayList<>();
                for(State s: statesToVisit){
                    for(Action a: s.getActions()){
                        w.write("occurs(" + a.getName() + "," + i + ").\n");
                        nextVisit.add(a.getEndState());
                    }
                }
                if(nextVisit.isEmpty()){
                    break;
                }
                statesToVisit = nextVisit;
                nextVisit.removeAll(nextVisit);
                i++;
            }

            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    } */

    public List<State> getStates() {
        return states;
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Fluent> getFluents() {
        return fluents;
    }

   /* private List<State> getStartingStates(List<State> states) {
        List<State> result = states;
        for(Action a: actions){
            //Assuming that our graph has no loops, if a action points to a state,
            //that state cannot be a starting state.

            //If our graph contains loops, then a fourth parameter will be needed
            //in our constructor in order to find out our starting states.
            states.remove(a.getEndState());
        }
        return result;
    } */

    // False means remove
    // True means add
  /*  public void changeActions(boolean mode, List<Action> changes) {
        File f = new File("../ASP/output.lp");

        if(!(f.exists() && !f.isDirectory())){
            System.out.println("Error: File has not been found");
            return;
        }

        try{
            if(mode){
                FileWriter w = new FileWriter(f);
                // Adding is easy, we simply need to do the same, as we did in
                // our initialization.
                for (Action a: changes){
                    a.getStartState().getActions().add(a);
                    w.write("action(" + a.getName() +").\n");

                    w.write("\n");

                    State s1 = a.getStartState();
                    State s2 = a.getEndState();
                    //Get Fluents before action
                    for (Fluent s_fluent: s1.getFluents()){
                        String f_name = s_fluent.getName().replaceAll("-", "");
                        // Get Fluents after action
                        for(Fluent e_fluent: s2.getFluents()){
                            String e_name = e_fluent.getName().replaceAll("-", "");
                            if(e_name.equals(f_name)){
                                if(s_fluent.getValue() != e_fluent.getValue()){
                                    //We've detected a change triggered by this action.
                                    //Write the specified code for it.
                                    w.write("holds(" + s_fluent.getName() + ",t+1) :- ");
                                    for(Fluent s_Fluent2: s1.getFluents()) {
                                        w.write("holds(" + s_Fluent2.getName() + ",t),\n");
                                    }
                                    w.write("occurs(" + a.getName() + ",t).\n\n");
                                }
                            }
                        }
                    }
                }
            } else {
                String code = readFile(f.getAbsolutePath());
                for (Action a: changes){
                    // TODO: Get the entire code as text and eliminate relevant statements.
                }
            }
        } catch (IOException e){
            e.printStackTrace();
            return;
        }
    }

    private String readFile(String filename)
    {
        String content = null;
        File file = new File(filename); //for ex foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader !=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    } */
}
