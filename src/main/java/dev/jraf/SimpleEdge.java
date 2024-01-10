package dev.jraf;

public class SimpleEdge implements Edge {
    
    private static final String NULL_VERTICES_MSG = "vertices must be non-null";

    public SimpleEdge(Vertex tail, Vertex head) {
        throw new NullPointerException(NULL_VERTICES_MSG);
    }

    @Override public Vertex head() {
        return null;
    }

    @Override public Vertex tail() {
        return null;
    }
}
