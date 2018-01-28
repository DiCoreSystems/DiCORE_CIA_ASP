import file.VersionManager;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.WorkflowGraph;
import org.junit.Before;
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
    private final String configPath = System.getProperty("user.dir") + "/logic_programs";
    private final String testFilePath = configPath + "/testGraph.lp";

    public void DefaultGraphSetUp(){
        graph = new Graph();

        Vertex v1 = new Vertex(UUID.randomUUID(), "start");
        Vertex v2 = new Vertex(UUID.randomUUID(), "1");
        Vertex v3 = new Vertex(UUID.randomUUID(), "2");
        Vertex v4 = new Vertex(UUID.randomUUID(), "3");
        Vertex v5 = new Vertex(UUID.randomUUID(), "4");
        Vertex v6 = new Vertex(UUID.randomUUID(), "5");
        Vertex v7 = new Vertex(UUID.randomUUID(), "6");
        Vertex v8 = new Vertex(UUID.randomUUID(), "7");
        Vertex v9 = new Vertex(UUID.randomUUID(), "8");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);
        graph.addVertex(v9);

        graph.addEdge(new Edge(UUID.randomUUID(), v1, v2));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, v3));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, v4));
        graph.addEdge(new Edge(UUID.randomUUID(), v3, v5));
        graph.addEdge(new Edge(UUID.randomUUID(), v4, v6));
        graph.addEdge(new Edge(UUID.randomUUID(), v5, v7));
        graph.addEdge(new Edge(UUID.randomUUID(), v6, v8));
        graph.addEdge(new Edge(UUID.randomUUID(), v7, v9));
        graph.addEdge(new Edge(UUID.randomUUID(), v8, v9));

        for(Edge e: graph.getEdges()){
            e.getStart().addOutgoingEdge(e);
            e.getEnd().addIncomingEdge(e);
        }
    }

    public void BranchGraphSetUp(){
        graph = new Graph();

        Vertex v1 = new Vertex(UUID.randomUUID(), "start");
        Vertex v2 = new Vertex(UUID.randomUUID(), "1");
        Vertex v3 = new Vertex(UUID.randomUUID(), "2");
        Vertex v4 = new Vertex(UUID.randomUUID(), "3");
        Vertex v5 = new Vertex(UUID.randomUUID(), "4");
        Vertex v6 = new Vertex(UUID.randomUUID(), "5");
        Vertex v7 = new Vertex(UUID.randomUUID(), "6");
        Vertex v8 = new Vertex(UUID.randomUUID(), "7");
        Vertex v9 = new Vertex(UUID.randomUUID(), "8");
        Vertex b1 = new Vertex(UUID.randomUUID(), "b1");
        Vertex b2 = new Vertex(UUID.randomUUID(), "b2");
        Vertex b3 = new Vertex(UUID.randomUUID(), "b3");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);
        graph.addVertex(v9);
        graph.addVertex(b1);
        graph.addVertex(b2);
        graph.addVertex(b3);

        graph.addEdge(new Edge(UUID.randomUUID(), v1, v2));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, v3));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, v4));
        graph.addEdge(new Edge(UUID.randomUUID(), v3, v5));
        graph.addEdge(new Edge(UUID.randomUUID(), v4, v6));
        graph.addEdge(new Edge(UUID.randomUUID(), v5, v7));
        graph.addEdge(new Edge(UUID.randomUUID(), v6, v8));
        graph.addEdge(new Edge(UUID.randomUUID(), v7, v9));
        graph.addEdge(new Edge(UUID.randomUUID(), v8, v9));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, b1));
        graph.addEdge(new Edge(UUID.randomUUID(), b1, b2));
        graph.addEdge(new Edge(UUID.randomUUID(), b2, b3));
        graph.addEdge(new Edge(UUID.randomUUID(), b3, v9));

        for(Edge e: graph.getEdges()){
            e.getStart().addOutgoingEdge(e);
            e.getEnd().addIncomingEdge(e);
        }
    }

    public void ReplacementGraphSetUp(){
        graph = new Graph();

        Vertex v1 = new Vertex(UUID.randomUUID(), "start");
        Vertex v2 = new Vertex(UUID.randomUUID(), "1");
        Vertex v3 = new Vertex(UUID.randomUUID(), "2");
        Vertex v4 = new Vertex(UUID.randomUUID(), "3");
        Vertex v5 = new Vertex(UUID.randomUUID(), "4");
        Vertex r  = new Vertex(UUID.randomUUID(), "new");
        Vertex v8 = new Vertex(UUID.randomUUID(), "7");
        Vertex v9 = new Vertex(UUID.randomUUID(), "8");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(r);
        graph.addVertex(v8);
        graph.addVertex(v9);

        graph.addEdge(new Edge(UUID.randomUUID(), v1, v2));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, v3));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, v4));
        graph.addEdge(new Edge(UUID.randomUUID(), v3, v5));
        graph.addEdge(new Edge(UUID.randomUUID(), v4, r));
        graph.addEdge(new Edge(UUID.randomUUID(), v5, r));
        graph.addEdge(new Edge(UUID.randomUUID(), r, v8));
        graph.addEdge(new Edge(UUID.randomUUID(), r, v9));
        graph.addEdge(new Edge(UUID.randomUUID(), v8, v9));

        for(Edge e: graph.getEdges()){
            e.getStart().addOutgoingEdge(e);
            e.getEnd().addIncomingEdge(e);
        }
    }

    public void MoveGraphSetUp(){
        graph = new Graph();

        Vertex v1 = new Vertex(UUID.randomUUID(), "start");
        Vertex v2 = new Vertex(UUID.randomUUID(), "1");
        Vertex v3 = new Vertex(UUID.randomUUID(), "2");
        Vertex v4 = new Vertex(UUID.randomUUID(), "3");
        Vertex v5 = new Vertex(UUID.randomUUID(), "4");
        Vertex v6 = new Vertex(UUID.randomUUID(), "5");
        Vertex v7 = new Vertex(UUID.randomUUID(), "6");
        Vertex v8 = new Vertex(UUID.randomUUID(), "7");
        Vertex v9 = new Vertex(UUID.randomUUID(), "8");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);
        graph.addVertex(v9);

        graph.addEdge(new Edge(UUID.randomUUID(), v1, v2));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, v5));
        graph.addEdge(new Edge(UUID.randomUUID(), v2, v4));
        graph.addEdge(new Edge(UUID.randomUUID(), v3, v8));
        graph.addEdge(new Edge(UUID.randomUUID(), v4, v6));
        graph.addEdge(new Edge(UUID.randomUUID(), v5, v7));
        graph.addEdge(new Edge(UUID.randomUUID(), v6, v8));
        graph.addEdge(new Edge(UUID.randomUUID(), v7, v3));
        graph.addEdge(new Edge(UUID.randomUUID(), v8, v9));

        for(Edge e: graph.getEdges()){
            e.getStart().addOutgoingEdge(e);
            e.getEnd().addIncomingEdge(e);
        }
    }

    @Before
    public void Setup(){
        DefaultGraphSetUp();

        WorkflowGraph defaultWorkflow = new WorkflowGraph(graph.getVertices(), graph.getEdges());
        TransitionDiagram t = defaultWorkflow.translate();
        t.setFilePath(testFilePath);
        t.createASPCodeWithoutPrefix();

        assertEquals(8, t.getStates().size());
        assertEquals(9, t.getActions().size());
        assertEquals(8, t.getFluents().size());
    }

    @Test
    public void TranslationTest(){
        ClingoRunner clingo = new ClingoRunner();
        clingo.checkIfSatisfiable(new File(testFilePath), true);
    }

    @Test
    public void BranchTest(){
        BranchGraphSetUp();

        WorkflowGraph branchWorkflow = new WorkflowGraph(graph.getVertices(), graph.getEdges());
        TransitionDiagram branchDiagram = branchWorkflow.translate();
        branchDiagram.setFilePath("../ASP/logic_programs/testBranch.lp");
        File branchFile = branchDiagram.createASPCode();

        ClingoRunner runner = new ClingoRunner();
        runner.getDifferences(new File (testFilePath), branchFile, true);
    }

    @Test
    public void ReplacementTest(){
        ReplacementGraphSetUp();

        WorkflowGraph replaceWorkflow = new WorkflowGraph(graph.getVertices(), graph.getEdges());
        TransitionDiagram replaceDiagram = replaceWorkflow.translate();
        replaceDiagram.setFilePath("../ASP/logic_programs/testReplace.lp");
        File branchFile = replaceDiagram.createASPCode();

        ClingoRunner runner = new ClingoRunner();
        runner.getDifferences(new File (testFilePath), branchFile, true);
    }

    @Test
    public void MovementTest(){
        MoveGraphSetUp();

        WorkflowGraph branchWorkflow = new WorkflowGraph(graph.getVertices(), graph.getEdges());
        TransitionDiagram branchDiagram = branchWorkflow.translate();
        branchDiagram.setFilePath("../ASP/logic_programs/testMove.lp");
        File branchFile = branchDiagram.createASPCode();

        ClingoRunner runner = new ClingoRunner();
        runner.getDifferences(new File (testFilePath), branchFile, true);
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