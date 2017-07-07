package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CSZ on 14.06.2017.
 */
public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;

    public Graph(){
        vertices = new ArrayList<>();
        edges = new ArrayList();
    }

    public Graph(List<Vertex> vertices, List<Edge> edges){
        this.vertices = vertices;
        this.edges = edges;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }
}
