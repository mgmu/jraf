package dev.jraf;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * An edge function associates an integral value to an edge. There can be only
 * one integral associated to each edge.
 */
public class EdgeFunction {

    private final Map<Edge, Integer> assoc;

    /**
     * Class constructor that initializes the empty function.
     */
    public EdgeFunction() {
        assoc = new HashMap<>();
    }

    /**
     * Returns the value associated to the edge denoted by the given vertices.
     * The vertices must be non-null and present.
     *
     * @param tail a non-null vertex, the tail of the edge
     * @param head a non-null vertex, the head of the edge
     * @return     an int, the value associated to the given vertices
     */
    public int get(Vertex tail, Vertex head) {
        if (tail == null || head == null)
            throw new NullPointerException("vertices must be non-null");
        Edge edge = Edge.of(tail.label(), head.label());
        if (!assoc.containsKey(edge))
            throw new NoSuchElementException("no such edge");
        return assoc.get(edge);
    }

    /**
     * Associates the given value to the edge represented by the given tail and
     * head vertices. The vertices must be non null. If the corresponding edge
     * is already present, the previous value is replaced by the new value.
     *
     * @param tail  a non-null vertex, the tail of the edge
     * @param head  a non-null vertex, the head of the edge
     * @param value the value to associate to the given edge
     */
    public void add(Vertex tail, Vertex head, int value) {
        if (tail == null || head == null)
            throw new NullPointerException("vertices must be non-null");
        assoc.put(Edge.of(tail.label(), head.label()), value);
    }

    /**
     * Removes the association between the edge represented by the given
     * vertices and the integer value. The vertices must be non-null and the
     * corresponding edge must be present.
     *
     * @param tail a non-null vertex, the tail of the edge
     * @param head a non-null vertex, the head of the edge
     */
    public void remove(Vertex tail, Vertex head) {
        if (tail == null || head == null)
            throw new NullPointerException("vertices must be non-null");
        Edge edge = Edge.of(tail.label(), head.label());
        if (!assoc.containsKey(edge))
            throw new NoSuchElementException("no such edge");
        assoc.remove(edge);
    }
}
