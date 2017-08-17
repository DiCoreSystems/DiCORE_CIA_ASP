package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by CSZ on 14.06.2017.
 */
public class Vertex {
    private String id;
    private String name;
    private List<Edge> outgoingEdges;
    private List<Edge> incomingEdges;
    public boolean vertexIsAction = false;

    public Vertex(UUID id) {
        this.id = id.toString();
        this.name = "randomVertex";
        outgoingEdges = new ArrayList<>();
        incomingEdges = new ArrayList<>();
    }

    public Vertex(UUID id, String name) {
        this.id = id.toString();
        this.name = name;
        outgoingEdges = new ArrayList<>();
        incomingEdges = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    public void addOutgoingEdge(Edge e){
        outgoingEdges.add(e);
    }

    public List<Edge> getIncomingEdges() {
        return incomingEdges;
    }

    public void addIncomingEdge(Edge e){
        incomingEdges.add(e);
    }

    public String getName() {
        return name;
    }

    public boolean IsAction(){ return vertexIsAction; }

    public List<Vertex> getPredecessors() {
        List<Vertex> result = new ArrayList<>();
        for(Edge e: this.getIncomingEdges()){
            result.add(e.getStart());
        }
        return result;
    }
}
