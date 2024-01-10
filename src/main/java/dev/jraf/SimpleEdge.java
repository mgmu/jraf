package dev.jraf;

public class SimpleEdge implements Edge {
    
    private static final String NULL_VERTICES_MSG = "vertices must be non-null";
    private static final String FORBIDDEN_LOOP_MSG = "loops are forbidden";

    private final Vertex head;
    private final Vertex tail;

    public SimpleEdge(Vertex tail, Vertex head) {
        if (tail == null || head == null)
            throw new NullPointerException(NULL_VERTICES_MSG);
        if (tail.equals(head))
            throw new IllegalArgumentException(FORBIDDEN_LOOP_MSG);
        this.head = head;
        this.tail = tail;
    }

    @Override public Vertex head() {
        return head;
    }

    @Override public Vertex tail() {
        return tail;
    }
}
