import java.util.UUID;

/**
 * Created by CSZ on 30.05.2017.
 */
public class Action extends Edge{
    private State startState;
    private State endState;
    private String name;

    public Action(UUID id, State startState, State endState, String name){
        super(id, startState, endState);
        this.startState = startState;
        this.endState = endState;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public State getStartState() {
        return startState;
    }

    public void setStartState(State startState) {
        this.startState = startState;
    }

    public State getEndState() {
        return endState;
    }

    public void setEndState(State endState) {
        this.endState = endState;
    }
}
