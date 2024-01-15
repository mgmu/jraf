package dev.jraf;

import java.util.Objects;

/**
 * A simple implementation of {@code Edge}.
 *
 * @author Guillermo Morón Usón
 * @see    Edge
 */
public class SimpleEdge implements Edge {
    
    // Messages of thrown exceptions
    private static final String NULL_VERTICES_MSG = "vertices must be non-null";
    private static final String FORBIDDEN_LOOP_MSG = "loops are forbidden";
    private static final String NULL_LABELS_MSG = "labels must be non-null";

    // The tail vertex
    private final Vertex tail;

    // The head vertex
    private final Vertex head;

    /**
     * Class constructor specifying the tail and head vertices. The given
     * vertices must be non-null and must not form a loop, that is they must be
     * different in the sens of equality.
     *
     * @param tail the tail of the new instance
     * @param head the head of the new instance
     */
    public SimpleEdge(Vertex tail, Vertex head) {
        if (tail == null || head == null)
            throw new NullPointerException(NULL_VERTICES_MSG);
        if (tail.equals(head))
            throw new IllegalArgumentException(FORBIDDEN_LOOP_MSG);
        this.tail = tail;
        this.head = head;
    }

    /**
     * Class constructor specifying the labels for the tail and head vertices.
     * The given labels must be non-null, not equal and of length superior or
     * equal to 1 and strictly inferior to 256. The vertices created are of type
     * {@code SimpleVertex}.
     *
     * @param lt the label of the tail vertex
     * @param lh the label of the head vertex
     */
    public SimpleEdge(String lt, String lh) {
        if (lt == null || lh == null)
            throw new NullPointerException(NULL_LABELS_MSG);
        if (lt.equals(lh))
            throw new IllegalArgumentException(FORBIDDEN_LOOP_MSG);
        tail = new SimpleVertex(lt);
        head = new SimpleVertex(lh);
    }

    /**
     * {@inheritDoc}
     */
    @Override public Vertex head() {
        return head;
    }

    /**
     * {@inheritDoc}
     */
    @Override public Vertex tail() {
        return tail;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge))
            return false;
        Edge other = (Edge) o;
        return head().equals(other.head()) && tail().equals(other.tail());
    }

    /**
     * {@inheritDoc}
     */
    @Override public int hashCode() {
        return Objects.hash(tail(), head());
    }
}
