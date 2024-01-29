package dev.jraf;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * An implementation of the graph interface that uses an adjacency list.
 *
 * @author Guillermo Morón Usón
 * @see    Graph
 */
public class AdjacencyGraph implements Graph {

    private static final String NULL_VERTEX_ERR = "vertex must be non-null";
    private final Map<Vertex, List<Vertex>> adjacencyMap;

    /**
     * Class constructor that creates a new empty graph.
     */
    public AdjacencyGraph() {
        adjacencyMap = new HashMap<>();
    }

    @Override public void add(Vertex vertex) {
        if (vertex == null)
            throw new NullPointerException(NULL_VERTEX_ERR);
        adjacencyMap.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override public void add(Vertex tail, Vertex head) {
        if (tail == null || head == null)
            throw new NullPointerException("vertices must be non-null");
        adjacencyMap.putIfAbsent(tail, new ArrayList<>());
        adjacencyMap.putIfAbsent(head, new ArrayList<>());
        List<Vertex> neigh = adjacencyMap.get(tail);
        if (!neigh.contains(head))
            neigh.add(head);
    }

    @Override public List<Vertex> neighborsOf(Vertex vertex) {
        if (vertex == null)
            throw new NullPointerException(NULL_VERTEX_ERR);
        if (!adjacencyMap.containsKey(vertex))
            throw new NoSuchElementException("no such vertex");
        List<Vertex> copy = new ArrayList<>();
        copy.addAll(adjacencyMap.get(vertex));
        return copy;
    }

    @Override public List<Vertex> vertices() {
        List<Vertex> vertices = new ArrayList<>();
        vertices.addAll(adjacencyMap.keySet());
        return vertices;
    }
}
