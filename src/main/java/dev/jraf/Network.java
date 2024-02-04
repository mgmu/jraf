package dev.jraf;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.NoSuchElementException;

/**
 * The implementation of a network. A network is a graph that has two special
 * vertices, the source and the sink, and a capacity function. The source is a
 * vertex that has no entrant arcs, and the sink is a vertex that has no
 * outgoing arcs. Nothing prevents having multiple vertices that have no
 * entrant or outgoing arcs, but only those given at instantiation will count as
 * such.
 *
 * @author Guillermo Morón Usón
 * @see    Graph
 * @see    CapacityFunction
 * @see    Vertex
 */
public class Network implements Graph {

    private final Vertex source;
    private final Vertex sink;
    private final Graph graph;
    private final EdgeToNaturalFunction cap;
    private static final int DEFAULT_CAPACITY = 1;

    private Network(Vertex src, Vertex snk, Graph graph) {
        source = src;
        sink = snk;
        this.graph = graph;
        cap = new EdgeToNaturalFunction();
    }

    /**
     * Creates and returns a new Network in adjacency list representation with
     * given source and sink vertices. The source and the sink must be non-null
     * and different. Initialliy, the network contains two vertices and no
     * edges.
     *
     * @param src a vertex, the source of the network
     * @param snk a vertex, the sink of the network
     * @return    a network with given vertices as source and network
     */
    public static Network newAdjacency(Vertex src, Vertex snk) {
        if (src == null || snk == null)
            throw new NullPointerException("vertices must be non-null");
        if (src.equals(snk)) {
            throw new IllegalArgumentException("source and sink must be not "
                    + "equal");
        }
        Graph graph = new AdjacencyGraph();
        graph.add(src);
        graph.add(snk);
        return new Network(src, snk, graph);
    }

    /**
     * {@inheritDoc}
     */
    @Override public void add(Vertex vertex) {
        graph.add(vertex);
    }

    /**
     * Adds the edge representated by the vertices tail and head to this graph.
     * The tail and the head must be non-null, different, the tail must be
     * different than the sink of this network and the head must be different
     * than the source. The capacity of the added edge is the default
     * capacity, 1. If the edge was already present, its capacity is updated.
     *
     * @param tail a non-null vertex, the tail of the edge
     * @param head a non-null vertex, the head of the edge
     * 
     */
    @Override public void add(Vertex tail, Vertex head) {
        add(tail, head, DEFAULT_CAPACITY);
    }

    /**
     * Adds the edge represented by the tail and head vertices to this graph.
     * The tail and head must be non-null, different, the tail must be
     * different than the sink of this network and the head must be different
     * than the source. The edge denotes an arc, a directed edge from tail to
     * head. If the edge is already present, its capacity is updated.
     *
     * @param tail     a non-null vertex, different than the sink, the tail of
     *                 the edge
     * @param head     a non-null vertex, different than the source, the head of
     *                 the edge
     * @param capacity an integer strictly superior to 0, the capacity of the
     *                 edge
     */
    public void add(Vertex tail, Vertex head, int capacity) {
        if (tail == null || head == null)
            throw new NullPointerException("vertices must be non-null");
        if (source.equals(head))
            throw new IllegalArgumentException("head is source");
        if (sink.equals(tail))
            throw new IllegalArgumentException("tail is sink");
        if (tail.equals(head))
            throw new IllegalArgumentException("edge cannot loop");
        graph.add(tail, head);
        cap.add(tail, head, capacity);
    }

    /**
     * {@inheritDoc}
     */
    @Override public List<Vertex> vertices() {
        return graph.vertices();
    }

    /**
     * Returns the source of this network.
     *
     * @return the source of this network
     */
    public Vertex source() {
        return source;
    }

    /**
     * Returns the sink of this network.
     *
     * @return the sink of this network
     */
    public Vertex sink() {
        return sink;
    }

    /**
     * Returns the capacity associated to the edge denoted by the given
     * vertices. The vertices must be non-null and denote an edge of the graph.
     *
     * @param tail a non-null vertex, the tail of the edge
     * @param head a non-null vertex, the head of the edge
     * @return     an int, the capacity of the edge denoted by the given
     *             vertices
     */
    public int capacity(Vertex tail, Vertex head) {
        if (tail == null || head == null)
            throw new NullPointerException("vertices must be non-null");
        return cap.get(tail, head);
    }

    /**
     * {@inheritDoc}
     */
    @Override public List<Vertex> neighborsOf(Vertex vertex) {
        return graph.neighborsOf(vertex);
    }

    /**
     * Computes the residual network of this network based on the given flow
     * function. The flow function must respect for every edge of the network
     * the capacity constraint (that is for any edge of the network, its flow is
     * superior or equal to 0 and inferior or equal to its capacity). The
     * residual graph of this network contains the same vertices and the edges
     * of this network such that their capacity minus their flow is strictly
     * superior to 0.
     *
     * @param flow a non-null function that associates edges of this network to
     *             integrals superior or equal to 0 and inferior or equal to
     *             their capacity
     */
    public Network residual(EdgeToNaturalFunction flow) {
        if (flow == null)
            throw new NullPointerException("edge function must be non-null");
        Network res = Network.newAdjacency(source, sink);
        Stack<Vertex> stack = new Stack<>();
        List<Vertex> visited = new ArrayList<>();
        stack.push(source);
        visited.add(source);
        while (!stack.isEmpty()) {
            Vertex cur = stack.pop();
            List<Vertex> neighbors = neighborsOf(cur);
            for (Vertex neighbor: neighbors) {
                int resCap = capacity(cur, neighbor) - flow.get(cur, neighbor);
                if (resCap < 0) {
                    throw new IllegalArgumentException("flow does not respect"
                            + " capacity constraint");
                }
                if (resCap == 0)
                    continue;
                res.add(cur, neighbor, resCap);
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override public void remove(Vertex tail, Vertex head) {
        throw new UnsupportedOperationException("todo");
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isAcyclic() {
        return true;
    }
}
