package dev.jraf;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

/**
 * A simple, directed graph. A directed graph has unidirectionnal edges and a
 * simple graph does not allow arcs that come from and go to the same vertex or
 * multiple unidirectionnal edges between two vertices.
 *
 * @author Guillermo Morón Usón
 * @see    Vertex
 * @see    Edge
 */
public interface Graph {

    static final String NULL_VERTEX_MSG = "vertex must be non-null";

    /**
     * Returns the vertices of this graph.
     *
     * @return a list of vertices, the vertices of this graph.
     */
    List<Vertex> vertices();

    /**
     * Returns the edges of this graph.
     *
     * @return a list of edges, the edges of this graph.
     */
    List<Edge> edges();

    /**
     * Returns the outgoing edges of the given vertex, if it is a vertex of this
     * graph. The given vertex must be non-null. If the given vertex is non-null
     * and present but has no outgoing edges, then an optional of the empty list
     * is returned.
     *
     * @param  vertex a non-null vertex
     * @return an optional list of edges, the outgoing edges of the given vertex
     */
    default Optional<List<Edge>> outgoingEdgesOf(Vertex vertex) {
        if (vertex == null)
            throw new NullPointerException(NULL_VERTEX_MSG);
        if (!vertices().contains(vertex))
            return Optional.empty();
        List<Edge> outgoingEdges = new ArrayList<>();
        for (Edge edge: edges()) {
            if (edge.tail().equals(vertex))
                outgoingEdges.add(edge);
        }
        return Optional.of(outgoingEdges);
    }

    /**
     * Returns the neighbors of the given vertex, that is the vertices that are
     * the heads of the outgoing edges of this vertex, if it is a vertex of this
     * graph. The given vertex must be non-null. If the given vertex is non-null
     * and has no neighbors, an optional of the empty list is returned.
     *
     * @param  vertex a non-null vertex
     * @return an optional list of vertices, the neighbors of the given vertex
     */
    default Optional<List<Vertex>> neighborsOf(Vertex vertex) {
        if (vertex == null)
            throw new NullPointerException(NULL_VERTEX_MSG);
        if (!vertices().contains(vertex))
            return Optional.empty();
        List<Vertex> neighbors = new ArrayList<>();
        for (Edge edge: edges()) {
            if (edge.tail().equals(vertex))
                neighbors.add(edge.head());
        }
        return Optional.of(neighbors);
    }

    /**
     * Returns true if for every two vertices x and y, there is an edge (x, y).
     *
     * @return true if there is an edge (x, y) for every two vertices x and y
     * @throws UnsupportedOperationException until its implementation
     */
    default boolean isComplete() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns true if for every edge (x, y), there is an edge (y, x).
     *
     * @return true if for every edge (x, y), there is an edge (y, x)
     * @throws UnsupportedOperationException until its implementation
     */
    default boolean isSymmetric() {
        throw new UnsupportedOperationException();
    }

    /**
     * Completes this graph by adding all the edges needed in order to be
     * complete, that is for every two vertices x and y there is an edge (x, y).
     *
     * @throws UnsupportedOperationException until its implementation
     */
    void complete();

    /**
     * Symmetrizes this graph by adding for every edge (x, y) an edge (y, x).
     *
     * @throws UnsupportedOperationException until its implementation     
     */
    void symmetrize();

    /**
     * Adds the given vertex to the vertices of this graph. The vertex must be
     * non-null. If the vertex happens to be already present, nothing is done.
     *
     * @param vertex the non-null vertex to add
     */
    void add(Vertex vertex);

    /**
     * Adds the given edge to the edges of this graph. The edge must be non-null
     * and if the tail of the head of the given edge are not present, they are
     * added. If the edge is already present, nothing is done (the head and tail
     * are, of course, not added).
     *
     * @param edge the non-null edge to add
     */
    void add(Edge edge);

    /**
     * Removes the given vertex from this graph. The vertex must be non-null and
     * if it is not present, nothing is done. If the vertex is non-null and
     * present, it is removed, so as all the edges for which it is a head or a
     * tail.
     *
     * @param vertex a non-null vertex to remove
     */
    void remove(Vertex vertex);

    /**
     * Removes the vertex that has the given label. The label must be non-null
     * and if there is no vertex with the given label, nothing is done. If the
     * label is non-null and there is a vertex of given label, its outgoing
     * edges are also removed.
     *
     * @param label the label of the vertex to remove
     */
    void remove(String label);

    /**
     * Removes the given edge from this graph. The edge must be non-null and if
     * it is not present, nothing is done. If the edge is non-null and present,
     * it is removed, but the head and tail are not.
     *
     * @param edge a non-null edge to remove
     */
    void remove(Edge edge);

    /**
     * Removes the edge which tail and head have the given labels. The labels
     * must be non-null.
     *
     * @param lt the label of the tail of the edge to remove
     * @param lh the label of the head of the edge to remove
     */
    void remove(String lt, String lh);

    /**
     * Returns the vertex of given label, if present. The label must be
     * non-null.
     *
     * @param label the label of the vertex to get
     * @return an optional vertex of given label
     * @throws UnsupportedOperationException until its implementation     
     */
    default Optional<Vertex> get(String label) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the edge which tail and head have the given labels, if present.
     * The labels must be both non-null.
     *
     * @param lt the label of the tail of the edge to get
     * @param th the label of the head of the edge to get
     * @return an optional edge between the vertices of given labels
     * @throw UnsupportedOperationException until its implementation
     */
    default Optional<Edge> get(String lt, String lh) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns true if the given object is a graph that contains the same
     * vertices and edges that this graph.
     *
     * @param o the object to be compared with this graph for equality
     * @return true if the given object is a graph that contains the same
     *         vertices and edges that this graph
     */
    @Override boolean equals(Object o);

    /**
     * Returns the hash code value for this graph. The hash code of a graph is
     * defined to be the sum of {@code vertices().hashCode()} and
     * {@code edges().hashCode()}.
     *
     * @return an int, the hash code of this graph
     */
    @Override int hashCode();
}
