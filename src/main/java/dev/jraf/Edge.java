package dev.jraf;

/**
 * An oriented arc from a vertex to another different vertex.
 * 
 * @author Guillermo Morón Usón
 * @see    Vertex
 */
public interface Edge {

    /**
     * Returns the head of this edge. The head of an edge is the vertex it
     * points to.
     *
     * @return a vertex, the head of this edge
     */
    Vertex head();

    /**
     * Returns the tail of this edge. The tail of an edge is the vertex it comes
     * from.
     *
     * @return a vertex, the tail of this edge
     */
    Vertex tail();

    /**
     * Compares the specified object with this edge for equality. Returns true
     * if and only if the specified object is an edge and its tail is equal to
     * the tail of this edge and its head is equal to the head of this edge. In
     * other words, two edges are equal if they come from the same vertex and
     * they end at the same vertex.
     * 
     * @param o the object to be compared with this edge for equality
     * @return true if the specified object is equal to this edge
     */
    @Override boolean equals(Object o);

    /**
     * Returns the hash code value for edge. The hash code value for this edge
     * is equal to {@code Objects.hash(tail(), head())}.
     *     
     * @return an int, the hash code of this edge.
     */
    @Override int hashCode();
}
