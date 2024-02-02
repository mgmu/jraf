package dev.jraf;

import java.util.List;
import java.util.ArrayList;
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
public class Network {

    private final Vertex source;
    private final Vertex sink;
    private final Graph graph;

    private Network(Vertex src, Vertex snk, Graph graph) {
        source = src;
        sink = snk;
        this.graph = graph;
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
     * Adds the given vertex to this graph. The vertex must be non-null.
     *
     * @param vertex a non-null vertex to add to this graph
     */
    public void add(Vertex vertex) {
        graph.add(vertex);
    }

    /**
     * Adds the edge tail to head to this graph. Tail and head must be non-null,
     * different, and tail must be different than the sink of this network and
     * the head must be different than the source. The edge denotes an arc, an
     * oriented edge from tail to head.
     *
     * @param tail a non-null vertex, different than the sink, the tail of the
     *             edge
     * @param head a non-null vertex, different than the source, the head of the
     *             edge
     */
    public void add(Vertex tail, Vertex head) {
        if (source.equals(head))
            throw new IllegalArgumentException("head is source");
        if (sink.equals(tail))
            throw new IllegalArgumentException("tail is sink");
        graph.add(tail, head);
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
        throw new NoSuchElementException("no such edge");
    }
}
