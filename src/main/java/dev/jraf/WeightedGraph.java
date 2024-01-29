package dev.jraf;

import java.util.List;

/**
 * The implementation of a weighted graph. A weighted graph associates an
 * integer value to all of its edges, called "weight".
 *
 * @author Guillermo Morón Usón
 * @see    Graph
 * @see    AdjacencyGraph
 * @see    EdgeFunction
 */
public class WeightedGraph {
    private final Graph graph;
    private final EdgeFunction weight;
    
    private WeightedGraph(Graph graph) {
        this.graph = graph;
        weight = new EdgeFunction();
    }

    /**
     * Creates a new empty weighted graph that uses an adjacency list
     * and returns it.
     */
    public static WeightedGraph newAdjacencyWeightedGraph() {
        return new WeightedGraph(new AdjacencyGraph());
    }

    /**
     * Adds the given vertex to this graph. The vertex must be non-null.
     *
     * @param vertex a non-null vertex to add to this graph
     */
    public void add(Vertex vertex) {
        graph.add(vertex);
    }

    /**
     * Adds the edge tail to head to this graph. Tail and head must be non-null,
     * and they can be equal (self loops are authorized, but can be forbidden in
     * implementations). The edge denotes an arc, an oriented edge from tail to
     * head.
     *
     * @param tail a non-null vertex, the tail of the edge
     * @param head a non-null vertex, the head of the edge
     */
    public void add(Vertex tail, Vertex head) {
        graph.add(tail, head);
    }

    /**
     * Returns the list of the neighbors of the given vertex. The vertex must be
     * non-null and present in the graph. A neighbor is a vertex that is the
     * head of an edge that has the given vertex as tail.
     *
     * @param vertex a non-null, present vertex
     * @return       a list of vertices, the neighbors of the given vertex in
     *               this graph
     */
    public void neighborsOf(Vertex vertex) {
        graph.neighborsOf(vertex);
    }

    /**
     * Returns the vertices of this graph.
     *
     * @return a list of vertex objects, the vertices of this graph
     */
    public List<Vertex> vertices() {
        return graph.vertices();
    }

    /**
     * Returns the weight associated to the edge of given tail and head
     * vertices. The vertices must be non-null and present.
     *
     * @param tail a non-null vertex, the tail of the edge
     * @param head a non-null vertex, the head of the edge
     * @return int an int, the value associated to the given vertices
     */
    public int weight(Vertex tail, Vertex head) {
        return weight.get(tail, head);
    }

    /**
     * Associates the given integer to the edge represented by the given tail
     * and head vertices. The vertices must be non null. If the corresponding
     * edge is already present, the previous value is replaced by the new
     * value. If one or both vertices of the edge are absent, they are added to
     * the graph, the edge is added and the value is associated.
     *
     * @param tail  a non-null vertex, the tail of the edge
     * @param head  a non-null vertex, the head of the edge
     * @param value the value to associate to the given edge
     */
    public void addWeight(Vertex tail, Vertex head, int value) {
        if (tail == null || head == null)
            throw new NullPointerException("vertices must be non-null");
        graph.add(tail, head);
        weight.add(tail, head, value);
    }
}
