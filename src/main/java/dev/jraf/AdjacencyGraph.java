package dev.jraf;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;

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
    AdjacencyGraph() {
        adjacencyMap = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override public void add(Vertex vertex) {
        if (vertex == null)
            throw new NullPointerException(NULL_VERTEX_ERR);
        adjacencyMap.putIfAbsent(vertex, new ArrayList<>());
    }

    /**
     * {@inheritDoc}
     */
    @Override public void add(Vertex tail, Vertex head) {
        if (tail == null || head == null)
            throw new NullPointerException("vertices must be non-null");
        adjacencyMap.putIfAbsent(tail, new ArrayList<>());
        adjacencyMap.putIfAbsent(head, new ArrayList<>());
        List<Vertex> neigh = adjacencyMap.get(tail);
        if (!neigh.contains(head))
            neigh.add(head);
    }

    /**
     * {@inheritDoc}
     */
    @Override public List<Vertex> neighborsOf(Vertex vertex) {
        if (vertex == null)
            throw new NullPointerException(NULL_VERTEX_ERR);
        if (!adjacencyMap.containsKey(vertex))
            throw new NoSuchElementException("no such vertex");
        List<Vertex> copy = new ArrayList<>();
        copy.addAll(adjacencyMap.get(vertex));
        return copy;
    }

    /**
     * {@inheritDoc}
     */
    @Override public List<Vertex> vertices() {
        List<Vertex> vertices = new ArrayList<>();
        vertices.addAll(adjacencyMap.keySet());
        return vertices;
    }

    /**
     * {@inheritDoc}
     */
    @Override public void remove(Vertex tail, Vertex head) {
        if (tail == null || head == null)
            throw new NullPointerException("vertices must be non-null");
        List<Vertex> vertices = vertices();
        if (!vertices.contains(tail))
            throw new IllegalArgumentException("vertices must be present");
        if (!vertices.contains(head))
            throw new IllegalArgumentException("vertices must be present");
        List<Vertex> assoc = adjacencyMap.get(tail);
        assoc.remove(head);
    }

    /**
     * {@inheritDoc}
     */
    @Override public Map<Integer, Integer> breadthFirstSearch(Vertex source) {
        if (source == null)
            throw new NullPointerException("vertex must be non-null");
        if (!adjacencyMap.containsKey(source))
            throw new IllegalArgumentException("vertex must be present");
        Map<Integer, Integer> parents = new HashMap<>();
        parents.put(source.label(), source.label());
        Queue<Vertex> visit = new ArrayDeque<>();
        Set<Vertex> visited = new HashSet<>();
        visit.add(source);
        Vertex current = null;
        while (!visit.isEmpty()) {
            current = visit.remove();
            visited.add(current);
            for (Vertex neighbor: neighborsOf(current)) {
                if (!visited.contains(neighbor) && !visit.contains(neighbor)) {
                    parents.put(neighbor.label(), current.label());
                    visit.add(neighbor);
                }
            }
        }
        return parents;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isAcyclic() {
        if (adjacencyMap.isEmpty())
            return true;
        List<Vertex> visited = new ArrayList<>();
        Stack<Vertex> stack = new Stack<>();
        return false;
    }
}
