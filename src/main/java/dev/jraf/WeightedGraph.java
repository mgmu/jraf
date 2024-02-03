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
public class WeightedGraph implements Graph {
    private final Graph graph;
    private final EdgeFunction weightFun;
    private static final int DEFAULT_WEIGHT = 1;
    
    private WeightedGraph(Graph graph) {
        this.graph = graph;
        weightFun = new EdgeFunction();
    }

    /**
     * Creates a new empty weighted graph that uses an adjacency list
     * and returns it.
     *
     * @return a new empty weighted graph
     */
    public static WeightedGraph newAdjacencyWeightedGraph() {
        return new WeightedGraph(new AdjacencyGraph());
    }

    /**
     * {@inheritDoc}
     */
    @Override public void add(Vertex vertex) {
        graph.add(vertex);
    }

    /**
     * Adds the edge tail to head to this graph. Tail and head must be non-null,
     * and they can be equal (self loops are authorized, but can be forbidden in
     * implementations). The edge denotes an arc, an oriented edge from tail to
     * head. Its weight defaults to 1.
     *
     * @param tail a non-null vertex, the tail of the edge
     * @param head a non-null vertex, the head of the edge
     */
    @Override public void add(Vertex tail, Vertex head) {
        add(tail, head, DEFAULT_WEIGHT);
    }

    /**
     * Adds the edge represented by the given vertices tail and head of given
     * weight. If the edge is already in the graph, its weight is updated. The
     * edge can be a loop.
     *
     * @param tail   a non-null vertex, the tail of the edge
     * @param head   a non-null vertex, the head of the edge
     * @param weight an int, the weight of the edge
     */
    public void add(Vertex tail, Vertex head, int weight) {
        graph.add(tail, head);
        weightFun.add(tail, head, weight);
    }

    /**
     * {@inheritDoc}
     */
    public List<Vertex> neighborsOf(Vertex vertex) {
        return graph.neighborsOf(vertex);
    }

    /**
     * {@inheritDoc}
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
        return weightFun.get(tail, head);
    }
}
