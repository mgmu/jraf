package dev.jraf;

/**
 * The implementation of a function that associates an integral value superior
 * or equal to 0 to some edge. This function can be refered to as the capacity
 * function.
 *
 * @author Guillermo Morón Usón
 * @see    EdgeFunction
 * @see    Vertex
 */
public class EdgeToNaturalFunction {

    private final EdgeFunction func;

    /**
     * Class constructor that creates an empty capacity function.
     */
    public EdgeToNaturalFunction() {
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
     * Associates the given value to the edge represented by the given tail
     * and head vertices. The vertices must be non null and the value must be
     * superior or equal to 0. If the corresponding edge is already present, the
     * previous value is replaced by the new value.
     *
     * @param tail     a non-null vertex, the tail of the edge
     * @param head     a non-null vertex, the head of the edge
     * @param value    the value to associate to the given edge
     */
    public void add(Vertex tail, Vertex head, int value) {
        if (value < 0)
            throw new IllegalArgumentException("value must be >= 0");
        func.add(tail, head, value);
    }
}
