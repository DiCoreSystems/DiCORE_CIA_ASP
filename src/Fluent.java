/**
 * Created by CSZ on 30.05.2017.
 */
public class Fluent {
    private String name;
    private boolean value;
    private Fluent negation;

    // Fluents need to be declared by their positive values.
    // The negative version of a fluent is defined straight afterwards
    // by the constructor.

    public Fluent(String name){
        this.name = name;
        this.value = true;
        //TODO: Make this negation accessible.
        negation = new Fluent("-" + name, false);
    }

    public Fluent(String name, boolean value){
        this.value = value;
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
}
