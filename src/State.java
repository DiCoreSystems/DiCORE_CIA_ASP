import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by CSZ on 30.05.2017.
 */
public class State extends Vertex {
    private List<Fluent> fluents = new ArrayList<>();
    private Set<Action> actions = new HashSet<>();

    public State(Fluent... fluents){
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
}
