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

    // The head vertex
    private final Vertex head;

    // The tail vertex
    private final Vertex tail;

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
        this.head = head;
        this.tail = tail;
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
