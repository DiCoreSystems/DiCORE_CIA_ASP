package test;

import file.VersionManager;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.WorkflowGraph;
import org.junit.Test;
import parser.ClingoRunner;
import transDiagram.State;
import transDiagram.TransitionDiagram;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by CSZ on 19.06.2017.
 */
public class WorkflowGraphTest {

    private Graph graph;
    private final String configPath = System.getProperty("user.dir") + "\\logic_programs";
    private final String testFilePath = configPath + "/test/testNew.lp";


    public void Graph1SetUp(){
        graph = new Graph();

        Vertex v1 = new Vertex(UUID.randomUUID(), "start");
        Vertex v2 = new Vertex(UUID.randomUUID(), "poi");
        Vertex v3 = new Vertex(UUID.randomUUID(), "gpsCoord");
        Vertex v4 = new Vertex(UUID.randomUUID(), "chooseMapType");
        Vertex v5 = new Vertex(UUID.randomUUID(), "geoMap");
        Vertex v6 = new Vertex(UUID.randomUUID(), "polMap");
        Vertex v7 = new Vertex(UUID.randomUUID(), "extractRelevantData");
        Vertex v8 = new Vertex(UUID.randomUUID(), "showData");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);

        graph.addEdge(new Edge(UUID.randomUUID(), v1, v2));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, v3));
        graph.addEdge(new Edge(UUID.randomUUID(), v3, v4));
        graph.addEdge(new Edge(UUID.randomUUID(), v4, v5));
        graph.addEdge(new Edge(UUID.randomUUID(), v4, v6));
        graph.addEdge(new Edge(UUID.randomUUID(), v5, v7));
        graph.addEdge(new Edge(UUID.randomUUID(), v6, v7));
        graph.addEdge(new Edge(UUID.randomUUID(), v7, v8));

        for(Edge e: graph.getEdges()){
            e.getStart().addOutgoingEdge(e);
            e.getEnd().addIncomingEdge(e);
        }
    }

    public void Graph2SetUp(){
        graph = new Graph();

        Vertex v1 = new Vertex(UUID.randomUUID(), "start");
        Vertex v2 = new Vertex(UUID.randomUUID(), "poi");
        Vertex v3 = new Vertex(UUID.randomUUID(), "absolCoord");
        Vertex v4 = new Vertex(UUID.randomUUID(), "chooseMapType");
        Vertex v5 = new Vertex(UUID.randomUUID(), "geoMap");
        Vertex v6 = new Vertex(UUID.randomUUID(), "polMap");
        Vertex v7 = new Vertex(UUID.randomUUID(), "extractRelevantData");
        Vertex v8 = new Vertex(UUID.randomUUID(), "showData");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);

        graph.addEdge(new Edge(UUID.randomUUID(), v1, v2));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, v3));
        graph.addEdge(new Edge(UUID.randomUUID(), v3, v4));
        graph.addEdge(new Edge(UUID.randomUUID(), v4, v5));
        graph.addEdge(new Edge(UUID.randomUUID(), v4, v6));
        graph.addEdge(new Edge(UUID.randomUUID(), v5, v7));
        graph.addEdge(new Edge(UUID.randomUUID(), v6, v7));
        graph.addEdge(new Edge(UUID.randomUUID(), v7, v8));

        for(Edge e: graph.getEdges()){
            e.getStart().addOutgoingEdge(e);
            e.getEnd().addIncomingEdge(e);
        }
    }

    public void Graph3SetUp(){
        graph = new Graph();

        Vertex v1 = new Vertex(UUID.randomUUID(), "start");
        Vertex v2 = new Vertex(UUID.randomUUID(), "step1");
        Vertex v3 = new Vertex(UUID.randomUUID(), "step2A");
        Vertex v4 = new Vertex(UUID.randomUUID(), "step2B");
        Vertex v5 = new Vertex(UUID.randomUUID(), "step3A");
        Vertex v6 = new Vertex(UUID.randomUUID(), "step3B");
        Vertex v7 = new Vertex(UUID.randomUUID(), "step4A");
        Vertex v8 = new Vertex(UUID.randomUUID(), "step4B");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);

        graph.addEdge(new Edge(UUID.randomUUID(), v1, v2));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, v3));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, v4));
        graph.addEdge(new Edge(UUID.randomUUID(), v3, v5));
        graph.addEdge(new Edge(UUID.randomUUID(), v4, v6));
        graph.addEdge(new Edge(UUID.randomUUID(), v5, v7));
        graph.addEdge(new Edge(UUID.randomUUID(), v6, v8));

        for(Edge e: graph.getEdges()){
            e.getStart().addOutgoingEdge(e);
            e.getEnd().addIncomingEdge(e);
        }
    }

    @Test
    public void TranslationTest1(){
        Graph1SetUp();

        WorkflowGraph workflowGraph = new WorkflowGraph(graph.getVertices(), graph.getEdges());

        TransitionDiagram t = workflowGraph.translate();

        File aspCode = t.createASPCode();

        assertEquals(7, t.getStates().size());
        assertEquals(8, t.getActions().size());
        assertEquals(7, t.getFluents().size());

        for (State s: t.getStates()){
            assertEquals(7, s.getFluents().size());
        }
    }

    @Test
    public void TranslationTest2(){
        Graph2SetUp();

        WorkflowGraph workflowGraph = new WorkflowGraph(graph.getVertices(), graph.getEdges());

        TransitionDiagram t = workflowGraph.translate();

        t.createASPCode();

        assertEquals(7, t.getStates().size());
        assertEquals(8, t.getActions().size());
        assertEquals(7, t.getFluents().size());

        for (State s: t.getStates()){
            assertEquals(7, s.getFluents().size());
        }
    }

    @Test
    public void ParserTest(){
        File f;
        f = new File(testFilePath);

        ClingoRunner parser = new ClingoRunner();
        assertTrue(parser.checkIfSatisfiable(f, true));

        parser.getDifferences(f, true);
    }

    @Test
    public void SaveTest(){
        VersionManager manager = new VersionManager();
        try {
            manager.saveNewFile(new File(testFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}