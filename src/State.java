import java.util.*;

/**
 * Created by CSZ on 30.05.2017.
 */
public class State extends Vertex {
    private List<Fluent> fluents = new ArrayList<>();
    private List<Action> actions = new ArrayList<>();

    public State(UUID id, List<Fluent> fluents){
        super(id);
        this.fluents = fluents;
    }

    public State(UUID id, String name, List<Fluent> fluents){
        super(id, name);
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
}
