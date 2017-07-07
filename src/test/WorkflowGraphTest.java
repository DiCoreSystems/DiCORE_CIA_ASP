package test;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.WorkflowGraph;
import org.junit.Before;
import org.junit.Test;
import parser.ClingoParser;
import transDiagram.State;
import transDiagram.TransitionDiagram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by CSZ on 19.06.2017.
 */
public class WorkflowGraphTest {

    private Graph graph;
    private final File configFile = new File("config.txt");

    @Before
    public void GraphSetUp(){
        graph = new Graph();

        Vertex v1 = new Vertex(UUID.randomUUID(), "start");
        Vertex v2 = new Vertex(UUID.randomUUID(), "geoMap");
        v2.vertexIsAction  = true;
        Vertex v3 = new Vertex(UUID.randomUUID(), "polMap");
        v3.vertexIsAction = true;
        Vertex v4 = new Vertex(UUID.randomUUID(), "extractRelevantData");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(new Edge(UUID.randomUUID(), v1, v2));
        graph.addEdge(new Edge(UUID.randomUUID(), v1, v3));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, v4));
        graph.addEdge(new Edge(UUID.randomUUID(), v3, v4));

        for(Edge e: graph.getEdges()){
            e.getStart().addOutgoingEdge(e);
            e.getEnd().addIncomingEdge(e);
        }
    }


    @Test
    public void WorkflowGraphTest(){
        WorkflowGraph workflowGraph = new WorkflowGraph(graph.getVertices(), graph.getEdges());

        TransitionDiagram t = workflowGraph.translate();

        assertEquals(3, t.getStates().size());
        assertEquals(2, t.getActions().size());
        assertEquals(3, t.getFluents().size());

        assertEquals(2, t.getStates().get(0).getActions().size());
        assertEquals(0, t.getStates().get(1).getActions().size());
        assertEquals(0, t.getStates().get(2).getActions().size());

        for (State s: t.getStates()){
            assertEquals(3, s.getFluents().size());
        }
    }

    @Test
    public void ParserTest(){
        File f = null;
        FileReader fr;
        try {
            fr = new FileReader(configFile);
            BufferedReader r = new BufferedReader(fr);
            String configPath = r.readLine();
            f = new File(configPath + "/test.lp");
        } catch (Exception e) {
            e.printStackTrace();
        }


        ClingoParser parser = new ClingoParser();
        assertNotNull(f);
        assertTrue(parser.run(f));
    }
}