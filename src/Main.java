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
        Fluent type = new Fluent("type", true);
        fluents.add(type);
        Fluent geo = new Fluent("geo", true);
        fluents.add(geo);
        Fluent pol = new Fluent("pol", true);
        fluents.add(pol);

        State start = new State(type, negGeo, negPol);
        states.add(start);
        State geoMap = new State(type, geo, negPol);
        states.add(geoMap);
        State polMap = new State(type, negGeo, pol);
        states.add(polMap);

        Action getGeoMap = new Action(start, geoMap, "getGeoMap");
        actions.add(getGeoMap);
        Action getPollutionMap = new Action(start, polMap, "getPollutionMap");
        actions.add(getPollutionMap);

        TransitionDiagram t = new TransitionDiagram(fluents, actions, states);

        t.createASPCode(); */
    }
}
