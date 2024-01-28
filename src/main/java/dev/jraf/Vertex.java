package dev.jraf;

/**
 * A representation of a vertex, labeled with an integer value. This class is
 * immutable.
 *
 * @author Guillermo Morón Usón
 */
public final class Vertex {

    private final int label;

    // Not extendable
    private Vertex(int label) {
        this.label = label;
    }

    /**
     * Creates a new vertex of given label and returns it.
     *
     * @param label the label of the new vertex
     * @return      a new vertex of given label
     */
    public static Vertex of(int label) {
        return new Vertex(label);
    }

    /**
     * Returns the label of this vertex.
     *
     * @return an int, the label of this vertex
     */
    public int label() {
        return label;
    }

    /**
     * Returns true if the given object equals this vertex, that is if the given
     * object is a vertex and their labels are equal.
     *
     * @param object the objet to test for equality
     * @return       true if the given object is a vertex with the same label as
     *               this vertex
     */
    @Override public boolean equals(Object object) {
        if (object == null) return false;
        return object.getClass() == getClass()
            && ((Vertex)object).label() == label();
    }

    /**
     * Returns a hash code for this vertex.
     *
     * @return an int, the label of this vertex
     */
    @Override public int hashCode() {
        return label;
    }
}
