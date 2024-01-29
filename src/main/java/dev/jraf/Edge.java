package dev.jraf;

import java.util.Objects;

/**
 * The representation of a directed association between two vertices.
 *
 * @author Guillermo Morón Usón
 * @see    Vertex
 */
public class Edge {

    private final Vertex tail;
    private final Vertex head;

    private Edge(int tail, int head) {
        this.tail = Vertex.of(tail);
        this.head = Vertex.of(head);
    }

    /**
     * Creates a new edge between vertices of given tail and head labels.
     *
     * @param tail an int, the label of the tail of the edge
     * @param head an int, the label of the head of the edge
     * @return     a new edge between vertices of given tail and head labels
     */
    public static Edge of(int tail, int head) {
        return new Edge(tail, head);
    }

    /**
     * Returns the tail of this edge.
     *
     * @return a vertex, the tail of this edge
     */
    public Vertex tail() {
        return tail;
    }

    /**
     * Returns the head of this edge.
     *
     * @return a vertex, the head of this edge
     */
    public Vertex head() {
        return head;
    }

    /**
     * Returns true if the given object is an edge and has equal tail and head
     * vertices.
     *
     * @param object the object to test for equality
     * @return       true if this edge and the given object have equal vertices
     */
    @Override public boolean equals(Object object) {
        if (object == null) return false;
        if (object.getClass() != getClass()) return false;
        Edge edge = (Edge) object;
        return edge.tail().equals(tail()) && edge.head().equals(head());
    }

    /**
     * Returns a hash code for this edge.
     *
     * @return an int, the result of {@code Objects.hash(tail(), head())}
     */
    @Override public int hashCode() {
        return Objects.hash(tail(), head());
    }
}
