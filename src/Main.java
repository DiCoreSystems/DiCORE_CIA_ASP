import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CSZ on 30.05.2017.
 */
public class Main {

    public static void main(String[] args){

/*        List<Fluent> fluents = new ArrayList<>();
        List<Action> actions = new ArrayList<>();
        List<State> states = new ArrayList<>();

        //Step 1: Determine all our fluents. Save their positive versions in a list.
        Fluent type = new Fluent("type");
        fluents.add(type);
        Fluent geo = new Fluent("geo");
        fluents.add(geo);
        Fluent pol = new Fluent("pol");
        fluents.add(pol);

        State start = new State(type, geo.getNegation(), pol.getNegation());
        states.add(start);
        State geoMap = new State(type, geo, pol.getNegation());
        states.add(geoMap);
        State polMap = new State(type, geo.getNegation(), pol);
        states.add(polMap);

        Action getGeoMap = new Action(start, geoMap, "getGeoMap");
        actions.add(getGeoMap);
        Action getPollutionMap = new Action(start, polMap, "getPollutionMap");
        actions.add(getPollutionMap);

        TransitionDiagram t = new TransitionDiagram(fluents, actions, states);

        t.createASPCode(); */
    }
}
