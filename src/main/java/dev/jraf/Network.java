package dev.jraf;

import java.util.List;

/**
 * A directed graph, with at least two vertices, the source and the sink, such
 * that there is no edge in the graph that has source as head vertex and no edge
 * that has sink as tail vertex. The smallest possible network is made of two
 * vertices, the source and the sink.
 *
 * @author Guillemo Morón Usón
 * @see    AbstractGraph
 * @see    CapacityFunction
 */
public class Network extends AbstractGraph {

    // Exception messages
    private static final String SAME_SRC_SINK_MSG = "source and sink must be " +
        "different";

    // The source and the sink
    private final Vertex source;
    private final Vertex sink;
    private CapacityFunction cap;

    // Indicates if all the edges of the network are associated to some capacity
    private boolean isFullyAssociated;

    /**
     * Class constructor specifying source and sink vertices. The vertices must
     * be non-null and different in the sense of equality. The new network
     * contains two vertices, the source and the sink. The capacity function
     * defaults to the empty association.
     *
     * @param source a non-null vertex, the source of the network
     * @param sink   a non-null vertex, the sink of the network
     */
    public Network(Vertex source, Vertex sink) {
        this.source = Graphs.requireNonNull(source);
        this.sink = Graphs.requireNonNull(sink);
        if (source.equals(sink))
            throw new IllegalArgumentException(SAME_SRC_SINK_MSG);
        this.isFullyAssociated = true;
        this.cap = new CapacityFunction();
        add(source);
        add(sink);
    }

    /**
     * Class constructor specifying source, sink and capacity function. The
     * vertices and the capacity function must be non-null, and the vertices
     * must be different in the sens of equality. Note that the capacity
     * function can contain associations for edges that are not present in the
     * new network.
     *
     * @param source      a non-null vertex, the source of the network
     * @param sink        a non-null vertex, the sinl of the network
     * @param capacityFun a function that associates an edge to its capacity
     */
    public Network(Vertex source, Vertex sink, CapacityFunction capacityFun) {
        if (capacityFun == null)
            throw new NullPointerException("capacity function must be "
                    + "non-null");
        this.source = Graphs.requireNonNull(source);
        this.sink = Graphs.requireNonNull(sink);
        if (source.equals(sink))
            throw new IllegalArgumentException(SAME_SRC_SINK_MSG);
        this.isFullyAssociated = true;
        this.cap = capacityFun;
        add(source);
        add(sink);
    }

    public Vertex source() {
        return source;
    }

    public Vertex sink() {
        return sink;
    }

    @Override public void symmetrize() {
        throw new UnsupportedOperationException();
    }

    @Override public void complete() {
        throw new UnsupportedOperationException();
    }

    @Override public void add(Edge edge) {
        super.add(Graphs.requireNonNull(edge));
        checkAndSetFullAssociation();
    }

    @Override public void remove(Edge edge) {
        throw new UnsupportedOperationException();
    }

    @Override public void remove(String lt, String lh) {
        throw new UnsupportedOperationException();        
    }

    /**
     * Returns the capacity associated to the given edge. The edge must be
     * non-null and if it is not associated to a capacity, a
     * NoSuchElementException is thrown. Note that even if this function returns
     * a value for the given edge, that does not imply that the edge is an edge
     * of this network.
     *
     * @param edge a non-null edge
     * @return     a double, the capacity associated to the given edge, if
     *             present
     */
    public double capacity(Edge edge) {
        return cap.get(edge);
    }

    /**
     * Returns the capacity associated to the edge between vertices of given
     * labels. The labels must be non-null and if the denoted edge is not
     * associated to a capacity, a NoSuchElementException is thrown. Note that
     * even if this function returns a value for the denoted edge, that does not
     * imply that this network contains such edge.
     *
     * @param lt the non-null label of the tail vertex
     * @param lh the non-null lable of the head vertex
     * @return   a double, the capacity associated to the denoted edge, if
     *           present
     */
    public double capacity(String lt, String lh) {
        return cap.get(lt, lh);
    }

    /**
     * Returns the capacity associated to the edge between given vertices. The
     * labels must be non-null and if the denoted edge is not associated to a
     * capacity, a NoSuchElementException is thrown. Note that even if this
     * function returns a value for the denoted edge, that does not imply that
     * this network contains such edge.
     *
     * @param tail a non-null vertex, the tail of the edge
     * @param head a non-null vertex, the head of the edge
     * @return     a double, the capacity associated to the denoted edge, if
     *             present
     */
    public double capacity(Vertex tail, Vertex head) {
        Graphs.requireNonNull(tail);
        Graphs.requireNonNull(head);
        return cap.get(tail.label(), head.label());
    }

    /**
     * Associates the given edge to the given value. The edge must be non-null
     * and if it has already an associated value, nothing is done.
     *
     * @param edge  a non-null edge to associate with a capacity
     * @param value the value of the capacity
     */
    public void addCapacity(Edge edge, double value) {
        Graphs.requireNonNull(edge);
        checkAndSetFullAssociation();
    }

    /**
     * Removes the association of the given edge. The edge must be non-null and
     * if no value is associated to the given edge, nothing is done.
     *
     * @param edge the non-null edge to remove
     */
    public void removeCapacity(Edge edge) {
        cap = (CapacityFunction) cap.remove(edge);
        checkAndSetFullAssociation();
    }

    /**
     * Returns true if all the edges of this network have an associated value in
     * the capacity function.
     *
     * @return a boolean that indicates if every edge of this network have an
     *         associated capacity
     */
    boolean isFullyAssociated() {
        return isFullyAssociated;
    }

    // Sets isFullyAssociated to true if all the edges of this graph are
    // associated to some capacity
    private void checkAndSetFullAssociation() {
        List<Edge> associatedEdges = cap.edges();
        for (Edge e: edges()) {
            if (!associatedEdges.contains(e)) {
                isFullyAssociated = false;
                break;
            }
        }
        isFullyAssociated = true;
    }
}
