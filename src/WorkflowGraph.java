import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by CSZ on 14.06.2017.
 */
public class WorkflowGraph extends Graph {

    // This class takes care of translating a WorkflowGraph to a corresponding TransitionDiagram.

    public WorkflowGraph(List<Vertex> vertices, List<Edge> edges){
        super(vertices, edges);
    }

    public void translate() {
        List<Vertex> verticesToCheck = this.getVertices();
        List<Vertex> stateVertices = new ArrayList<>();
        List<Vertex> actionVertices = new ArrayList<>();
        List<Fluent> fluents = new ArrayList<>();
        Vertex startingVertex = null;
        int choiceNR = 1;
        boolean vertexIsAction = false;

        verticesToCheck.add(this.getVertices().get(0));

        // For this example I'm going to assume that our original vertices stand
        // first for States, then an Action, then a State again, and so on...

        //This while loop categorizes every vertex in our original graph.
        while(!verticesToCheck.isEmpty()){
            Vertex v = verticesToCheck.get(0);
            verticesToCheck.remove(v);

            if(vertexIsAction){
                // We have an Action Vertex
                actionVertices.add(v);
                // All action change something. So we need to create a fluent that is changed by this
                // action.
                Fluent aFluent = new Fluent(v.getName());
                // Why the negation? At the start of the workflow, we haven't retrieved that data yet.
                fluents.add(aFluent.getNegation());
            } else {
                // We have a State Vertex.
                stateVertices.add(v);

                if(v.getOutgoingEdges().size() > 1){
                    // A State Vertex with two or more outgoing edges (read: two or more possible actions)
                    // contains a choice that has to be made. So we create a new fluent representing our
                    // choice at this point.
                    Fluent type = new Fluent("type" + choiceNR);
                    fluents.add(type);
                    choiceNR++;
                }

                if(v.getName() == "start"){
                    startingVertex = v;
                }
            }

            vertexIsAction = !vertexIsAction;
        }

        // Transform all StateVertices to actual States.
        // First attach all fluents to our starting vertex.
        State startingState = new State(UUID.randomUUID(), fluents);

        // Now we follow the graph vertex by veterx, until we reach the end.
        // Each passed vertex will yield an Action or a new state.

        for(Edge e: startingVertex.getOutgoingEdges()){
            Vertex v = e.getEnd();
            // v should always be an Action vertex
            // TODO: Can actions result in mutiple states?
            Vertex nextVertex = v.getOutgoingEdges().get(0).getEnd();

            //Find out which fluent is changed by our action.
            for(Fluent f: fluents){
                if(f.getName().contains(v.getName())){
                    fluents.remove(f);
                    fluents.add(f.getNegation());
                }
            }

            //Create the corresponding state.
            State newState = new State(UUID.randomUUID(), fluents);
        }
    }
}
