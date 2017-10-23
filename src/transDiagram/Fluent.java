package transDiagram;

/**
 * Created by CSZ on 30.05.2017.
 * A fluent stand for a status of each action. A negated fluent means that the action has not
 * been completed yet. The positive value states the opposite (obviously)
 */
public class Fluent {
    private final String name;
    private final boolean value;
    private Fluent negation;
    private final boolean inertial = true;

    // Fluents need to be declared by their positive values.
    // The negative version of a fluent is defined straight afterwards
    // by the constructor.
    public Fluent(String name){
        this.name = name;
        this.value = true;
        this.negation = new Fluent(name, false, this);
    }

    public Fluent(String name, boolean value, Fluent negation){
        this.name = name;
        this.value = value;
        this.negation = negation;
    }

    public boolean getValue() {
        return value;
    }

    public String getName(){
        return this.name;
    }

    public Fluent getNegation() {
        return negation;
    }

    public boolean isInertial() {
        //Note to me: Inertial fluents are triggered by actions. They change their value then.
        //If an action does not occur, it keeps its value from the previous timestep.
        // A defined fluent is much like a constant. No action can change the value. It's defined by unique bodies insted.
        return inertial;
    }
}
