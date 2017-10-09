package transDiagram;

import java.util.*;

/**
 * Created by CSZ on 30.05.2017.
 * Each state contains a list of all actions inside the WorkflowGraph. That means, that each state
 * knows which actions must've been completed before this one and which are yet to come.
 */
public class State {
    private UUID id;
    private String name;
    private List<Fluent> fluents = new ArrayList<>();
    private List<Action> ingoingActions = new ArrayList<>();
    private List<Action> outgoingActions = new ArrayList<>();

    public State(UUID id, List<Fluent> fluents){
        this.id = id;
        this.fluents = fluents;
    }

    public State(UUID id, String name, List<Fluent> fluents){
        this.id = id;
        this.name = name;
        this.fluents = fluents;
    }

    public List<Fluent> getFluents(){
        return this.fluents;
    }

    public List<Action> getOutgoingActions() {
        return this.outgoingActions;
    }

    public void addOutgoingAction(Action a){
        this.outgoingActions.add(a);
    }

    public List<Action> getIngoingActions() {
        return this.ingoingActions;
    }

    public void addIngoingAction(Action a){
        this.ingoingActions.add(a);
    }

    public String getName() { return name; }
}
