package dev.jraf;

/**
 * The implementation of the capacity function, a function that associates an
 * integral value strictly superior to 0 to some arc.
 *
 * @author Guillermo Morón Usón
 * @see    EdgeFunction
 * @see    Vertex
 */
public class CapacityFunction {

    private final EdgeFunction func;

    /**
     * Class constructor that creates an empty capacity function.
     */
    public CapacityFunction() {
        func = new EdgeFunction();
    }

    /**
     * Returns the value associated to the edge denoted by the given vertices.
     * The vertices must be non-null and present.
     *
     * @param tail a non-null vertex, the tail of the edge
     * @param head a non-null vertex, the head of the edge
     * @return     an int, the value associated to the given vertices
     */
    public int get(Vertex tail, Vertex head) {
        return func.get(tail, head);
    }

    /**
     * Associates the given capacity to the edge represented by the given tail
     * and head vertices. The vertices must be non null and the capacity must be
     * strictly superior to 0. If the corresponding edge is already present, the
     * previous capacity is replaced by the new capacity.
     *
     * @param tail     a non-null vertex, the tail of the edge
     * @param head     a non-null vertex, the head of the edge
     * @param capacity the capacity to associate to the given edge
     */
    public void add(Vertex tail, Vertex head, int capacity) {
        if (capacity < 1)
            throw new IllegalArgumentException("capacity must be > 0");
        func.add(tail, head, capacity);
    }
}
