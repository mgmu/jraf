package dev.jraf;

import java.util.List;

/**
 * The representation of a graph. Here a graph denotes a tuple of two sets: the
 * set of vertices V and the set of arcs called A. The elements of V are
 * abstract entities represented by integers. A is a relation between elements
 * of V that are directed. This interface authorizes loops (that is, arcs of the
 * form (x, x), x in V) but forbids multiple arcs between two vertices.
 *
 * @author Guillermo Morón Usón
 */
public interface Graph {

    /**
     * Adds the given vertex to this graph. The vertex must be non-null.
     *
     * @param vertex a non-null vertex to add to this graph
     */
    void add(Vertex vertex);

    /**
     * Adds the edge tail to head to this graph. Tail and head must be non-null,
     * and they can be equal (self loops are authorized, but can be forbidden in
     * implementations). The edge denotes an arc, a directed edge from tail to
     * head. The addition of an already present edge has no effect.
     *
     * @param tail a non-null vertex, the tail of the edge
     * @param head a non-null vertex, the head of the edge
     */
    void add(Vertex tail, Vertex head);

    /**
     * Returns the list of the neighbors of the given vertex. The vertex must be
     * non-null and present in the graph. A neighbor is a vertex that is the
     * head of an edge that has the given vertex as tail.
     *
     * @param vertex a non-null, present vertex
     * @return       a list of vertices, the neighbors of the given vertex in
     *               this graph
     */
    List<Vertex> neighborsOf(Vertex vertex);

    /**
     * Returns the vertices of this graph.
     *
     * @return a list of vertex objects, the vertices of this graph
     */
    List<Vertex> vertices();
}
