package dev.jraf;

/**
 * A utilitary class that contains methods for argument verification of graph
 * related methods.
 *
 * @author Guillermo Morón Usón
 * @see    Graph
 * @see    AbstractGraph
 */
class Graphs {
    
    private static final String NON_NULL_MSG = "must be non-null";
    private static final String NULL_VERTEX_MSG = "vertex " + NON_NULL_MSG;
    private static final String NULL_EDGE_MSG = "edge " + NON_NULL_MSG;
    private static final String NULL_LABEL_MSG = "label " + NON_NULL_MSG;

    /**
     * Returns the given vertex. If the given vertex is null, throws a
     * NullPointerException.
     *
     * @param vertex a nullable vertex
     * @return       the given vertex
     */
    static Vertex requireNonNull(Vertex vertex) {
        throwNPEIfNull(vertex, NULL_VERTEX_MSG);
        return vertex;
    }

    /**
     * Returns the given edge. If the given edge is null, throws a
     * NullPointerException.
     *
     * @param edge a nullable edge
     * @return     the given edge
     */
    static Edge requireNonNull(Edge edge) {
        throwNPEIfNull(edge, NULL_EDGE_MSG);
        return edge;
    }

    /**
     * Returns the given label. If the given label is null, throws a
     * NullPointerException.
     *
     * @param label a nullable label
     * @return      the given label
     */
    static String requireNonNull(String label) {
        throwNPEIfNull(label, NULL_LABEL_MSG);
        return label;
    }

    // If o is null, throws an NPE with given message
    private static void throwNPEIfNull(Object o, String msg) {
        if (o == null)
            throw new NullPointerException(msg);
    }
}
