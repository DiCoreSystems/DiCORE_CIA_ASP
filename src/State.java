import java.util.*;

/**
 * Created by CSZ on 30.05.2017.
 */
public class State extends Vertex {
    private List<Fluent> fluents = new ArrayList<>();
    private Set<Action> actions = new HashSet<>();

    public State(UUID id, Fluent... fluents){
        super(id);
        for(Fluent f: fluents){
            this.fluents.add(f);
        }
    }

    public List<Fluent> getFluents(){
        return this.fluents;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void addAction(Action a){
        actions.add(a);
    }
}
