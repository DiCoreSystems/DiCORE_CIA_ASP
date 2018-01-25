package graph;

import java.util.UUID;

/**
 * Created by CSZ on 14.06.2017.
 */
public class Edge {
    private final String id;
    private final Vertex start;
    private final Vertex end;

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

    public Vertex getEnd() {
        return end;
    }
}
