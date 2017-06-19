/**
 * Created by CSZ on 30.05.2017.
 */
public class Fluent {
    private String name;
    private boolean value;

    // Fluents need to be declared by their positive values.
    // The negative version of a fluent is defined straight afterwards
    // by the constructor. (to be done)

    public Fluent(String name){
        this.name = name;
        this.value = true;
    }

    // Depending on the value of our fluent, its name has to contain
    // the natural negation or not.
    public Fluent(String name, boolean value){
        this.value = value;
        if(!value){
            this.name = "-" + name;
        } else {
            this.name = name;
        }
    }

    public boolean getValue() {
        return value;
    }

    public String getName(){
        return this.name;
    }
}
