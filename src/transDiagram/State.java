package transDiagram;

import graph.Vertex;

import java.util.*;

/**
 * Created by CSZ on 30.05.2017.
 * Important: A state and its corresponding vertex must have the same name.
 */
public class State {
    private UUID id;
    private String name;
    private List<Fluent> fluents = new ArrayList<>();
    private List<Action> actions = new ArrayList<>();

    public State(UUID id, List<Fluent> fluents){
        this.id = id;
        this.name = name;
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

    public List<Action> getActions() {
        return this.actions;
    }

    public void addAction(Action a){
        this.actions.add(a);
    }

    public String getName() { return name; }
}
