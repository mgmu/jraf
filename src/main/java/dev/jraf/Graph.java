package dev.jraf;

import java.util.List;
import java.util.Optional;

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
     * graph. The given vertex must be non-null.
     *
     * @param  vertex a non-null vertex
     * @return an optional list of edges, the outgoing edges of the given vertex
     */
    default Optional<List<Edge>> outgoingEdgesOf(Vertex vertex) {
        return Optional.ofNullable(null);
    }

    /**
     * Returns the neighbors of the given vertex, that is the vertices that are
     * the heads of the outgoing edges of this vertex, if it is a vertex of this
     * graph. The given vertex must be non-null.
     *
     * @param  vertex a non-null vertex
     * @return an optional list of vertices, the neighbors of the given vertex
     */
    default Optional<List<Vertex>> neighborsOf(Vertex vertex) {
        return Optional.ofNullable(null);
    }

    /**
     * Returns true if for every two vertices x and y, there is an edge (x, y).
     *
     * @return true if there is an edge (x, y) for every two vertices x and y
     */
    default boolean isComplete() {
        return false;
    }

    /**
     * Returns true if for every edge (x, y), there is an edge (y, x).
     *
     * @return true if for every edge (x, y), there is an edge (y, x)
     */
    default boolean isSymmetric() {
        return false;
    }

    /**
     * Completes this graph by adding all the edges needed in order to be
     * complete, that is for every two vertices x and y there is an edge (x, y).
     */
    void complete();

    /**
     * Symmetrizes this graph by adding for every edge (x, y) an edge (y, x).
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
     * Removes the given edge from this graph. The edge must be non-null and if
     * it is not present, nothing is done. If the edge is non-null and present,
     * it is removed, but the head and tail are not.
     *
     * @param edge a non-null edge to remove
     */
    void remove(Edge edge);

    /**
     * Returns the vertex of given label, if present. The label must be
     * non-null.
     *
     * @param label the label of the vertex to get
     * @return an optional vertex of given label
     */
    default Optional<Vertex> get(String label) {
        return null;
    }

    /**
     * Returns the edge which tail and head have the given labels, if present.
     * The labels must be both non-null.
     *
     * @param lt the label of the tail of the edge to get
     * @param th the label of the head of the edge to get
     * @return an optional edge between the vertices of given labels
     */
    default Optional<Edge> get(String lt, String lh) {
        return null;
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
