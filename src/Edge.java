import java.util.UUID;

/**
 * Created by CSZ on 14.06.2017.
 */
public class Edge {
    private String id;
    private Vertex start;
    private Vertex end;

    public Edge(UUID id, Vertex start, Vertex end){
        this.id = id.toString();
        this.start = start;
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public Vertex getStart() {
        return start;
    }

    public void setStart(Vertex start) {
        this.start = start;
    }

    public Vertex getEnd() {
        return end;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }
}
