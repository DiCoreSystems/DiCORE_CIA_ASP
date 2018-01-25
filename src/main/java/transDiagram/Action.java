package transDiagram;

import java.util.UUID;

/**
 * Created by CSZ on 30.05.2017.
 */
public class Action{
    private UUID id;
    private State startState;
    private State endState;
    private String name;


    public Action(UUID id, State startState, State endState, String name){
        this.id = id;
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

    public State getEndState() {
        return endState;
    }
}
