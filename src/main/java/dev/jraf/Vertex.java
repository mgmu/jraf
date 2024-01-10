package dev.jraf;

/**
 * A vertex of a graph, labeled with a string.
 *
 * @author Guillermo Morón Usón
 */
public interface Vertex {

    /**
     * Returns the label of this vertex.
     *
     * @return a string, the label of this vertex
     */
    String label();

    /**
     * Compares the specified object with this vertex for equality. Returns true
     * if and only if the specified object is also a vertex and they both have
     * the same label. In other words, two vertices are equal if their labels
     * are.
     * 
     * @param o the object to be compared with this vertex for equality
     * @return true if the specified object is equal to this vertex
     */
    @Override boolean equals(Object o);

    /**
     * Returns the hash code value for this vertex. The hash code value for this
     * vertex is equal to the hash code of its label.
     *     
     * @return an int, the hash code of this vertex.
     */
    @Override int hashCode();
}
