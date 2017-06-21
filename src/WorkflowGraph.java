import java.util.ArrayList;
import java.util.List;

/**
 * Created by CSZ on 14.06.2017.
 */
public class WorkflowGraph extends Graph {

    // This class takes care of translating a WorkflowGraph to a corresponding TransitionDiagram.

    public WorkflowGraph(List<Vertex> vertices, List<Edge> edges){
        super(vertices, edges);
    }

    public void translate() {
        List<Fluent> fluents = new ArrayList<>();
        int choiceNR = 1;

        for(Vertex v: this.getVertices()){
            // If a vertex has more than one outgoing edge, then we can assume that we have
            // some sort of choice in our process. We then create a type fluent which stands for our
            // chosen path.
            if(v.getOutgoingEdges().size() > 1){
                Fluent type = new Fluent("type" + choiceNR);
                fluents.add(type);
                choiceNR++;
            }

            // TODO: Assign a return type for each vertex.
            // For this example, it's enough defining a fluent for each remaining vertex, that has only
            // 1 outgoing edge.
            if(v.getOutgoingEdges().size() == 1){
                Fluent vFluent = new Fluent("geoMap");
                fluents.add(vFluent);
            }
        }
    }
}
