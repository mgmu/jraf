package dev.jraf;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
     * Creates a new vertex of given label and adds it if not present.
     *
     * @param label an int, the label of the vertex to add
     */
    void add(int label);

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
     * Adds the edge that starts at the vertex that has the label tail and ends
     * at the vertex that has the label head. Tail and head can be equal (self
     * loops are authorized, but can be forbidden in implementations). The edge
     * denotes an arc, a directed edge from tail to head. The addition of an
     * already present edge has no effect.
     *
     * @param tail an int, the label of the tail of the edge
     * @param head an int, the label of the head of the edge
     */
    void add(int tail, int head);

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

    /**
     * Removes the edge that links the given tail to the given head. The
     * vertices must be non-null and present. If the edge does not exist, does
     * nothing.
     *
     * @param tail a non-null present vertex, the tail of the edge to remove
     * @param head a non-null present vertex, the head of the edge to remove
     */
    void remove(Vertex tail, Vertex head);

    /**
     * Performs a breadth-first search starting from the given vertex in this
     * graph and returns the resulting vertex label to parent label association.
     * The map associates the label of every vertex reached from the source to
     * the label of its parent. The parent vertex of vertex v is the vertex
     * preceding vertex v in the path from the source to vertex v. The label of
     * the source is associated to itself.
     *
     * @param source a non-null present vertex
     * @return       a map that associates an integer to an integer, the parents
     *               association obtained by performing the BFS run.
     */
    Map<Integer, Integer> breadthFirstSearch(Vertex source);

    /**
     * Returns true if there is no cycle in the graph. A cycle is a sequence of
     * edges of the graph, such that:
     * - each pair of consecutive edges (e1, e2) respect the property that the
     *   head of e1 is equal to the tail of e2.
     * - the tail of the first edge of the cycle is equal to the head of the
     *   last edge
     * - the sequence contains at least two edges
     *
     * @return a boolean, true if this graph contains no cycle
     */
    boolean isAcyclic();

    /**
     * Returns the degree of the given vertex. The vertex must be non-null and
     * present. The degree of a vertex is the number of arcs that start at it.
     *
     * @param vertex a non-null, present vertex
     * @return       an int, the degree of the vertex
     */
    default int degreeOf(Vertex vertex) {
        if (vertex == null)
            throw new NullPointerException("vertex must be non-null");
        List<Vertex> vertices = vertices();
        if (!vertices.contains(vertex))
            throw new NoSuchElementException("vertex must be present");
        List<Vertex> neighbors = neighborsOf(vertex);
        return neighbors.size();
    }

    /**
     * Returns the degree of the vertex of given label. The vertex must be
     * present. The degree of a vertex is the number of arcs that start at it.
     *
     * @param label the label of the vertex
     * @return      an int, the degree of the vertex of given label
     */
    default int degreeOf(int label) {
        return degreeOf(Vertex.of(label));
    }
}
