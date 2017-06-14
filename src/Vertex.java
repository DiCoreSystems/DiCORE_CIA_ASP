import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by CSZ on 14.06.2017.
 */
public class Vertex {
    private String id;
    private List<Edge> outgoingEdges;
    private List<Edge> incomingEdges;

    public Vertex(UUID id) {
        this.id = id.toString();
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
}
