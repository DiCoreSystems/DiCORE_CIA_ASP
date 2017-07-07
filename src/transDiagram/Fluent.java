package transDiagram;

/**
 * Created by CSZ on 30.05.2017.
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
        this.negation = new Fluent("-" + name, false, this);
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
        //TODO: Check if a given fluent is inertial.
        return inertial;
    }
}
