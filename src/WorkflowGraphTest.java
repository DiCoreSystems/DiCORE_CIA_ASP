import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by CSZ on 19.06.2017.
 */
public class WorkflowGraphTest {

    private Graph graph;

    @Before
    public void GraphSetUp(){
        List<Vertex> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        Vertex v1 = new Vertex(UUID.randomUUID(), "start");
        Vertex v2 = new Vertex(UUID.randomUUID(), "geoMap");
        Vertex v3 = new Vertex(UUID.randomUUID(), "polMap");
        Vertex v4 = new Vertex(UUID.randomUUID(), "extractRelevantData");

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);

        Edge e1 = new Edge(UUID.randomUUID(), v1, v2);
        Edge e2 = new Edge(UUID.randomUUID(), v1, v3);
        Edge e3 = new Edge(UUID.randomUUID(), v2, v4);
        Edge e4 = new Edge(UUID.randomUUID(), v3, v4);

        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);

        for(Edge e: edges){
            e.getStart().addOutgoingEdge(e);
            e.getEnd().addIncomingEdge(e);
        }

        graph = new Graph(vertices, edges);
    }


    @Test
    public void WorkflowGraphTest(){
        WorkflowGraph workflowGraph = new WorkflowGraph(graph.getVertices(), graph.getEdges());

        workflowGraph.translate();
    }
}