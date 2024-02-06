package dev.jraf;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Optional;

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
    @Override public void add(int label) {
        add(Vertex.of(label));
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
    @Override public void add(int tail, int head) {
        add(Vertex.of(tail), Vertex.of(head));
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

    /*
     * Dives from src into this graph, adds to visited the vertices visited
     * during the dive, adds the untaken arcs in the untaken set and returns a
     * parent map, such that u -> v means that v is the parent of u in the dive.
     * src is the only vertex that has itself as a parent. If the graph contains
     * a vertex that loops on itself, returns the empty optional.
     */
    private Optional<Map<Integer, Integer>> diveAndUpdate(Vertex src,
            Set<Vertex> visited, Set<Edge> untaken) {
        Map<Integer, Integer> parents = new HashMap<>();
        parents.put(src.label(), src.label());
        Stack<Vertex> stack = new Stack<>();
        stack.add(src);
        Vertex current = null;
        while (!stack.isEmpty()) {
            current = stack.pop();
            visited.add(current);
            for (Vertex neighbor: neighborsOf(current)) {
                if (neighbor.equals(current)) // self-loop
                    return Optional.empty();
                if (visited.contains(neighbor))
                    untaken.add(Edge.of(current.label(), neighbor.label()));
                else {
                    stack.push(neighbor);
                    parents.put(neighbor.label(), current.label());
                }
            }
        }
        return Optional.of(parents);
    }

    /*
     * Returns true if tail is a descendant of head in the parents map. parents
     * is of the same form than returned in diveAndUpdate.
     */
    private static boolean isTailDescendantOfHead(Vertex tail, Vertex head,
            Map<Integer, Integer> parents) {
        int cur = tail.label();
        int parent = parents.get(cur);
        while (parent != head.label() && parent != cur) {
            cur = parent;
            parent = parents.get(cur);
        }
        if (parent == head.label())
            return true;
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isAcyclic() {
        if (adjacencyMap.isEmpty())
            return true;
        Set<Vertex> vertices = Set.copyOf(vertices());
        Set<Vertex> visited = new HashSet<>();
        while (!visited.containsAll(vertices)) {
            Set<Vertex> rem = new HashSet<>();
            for (Vertex vertex: vertices()) {
                if (!visited.contains(vertex))
                    rem.add(vertex);
            }
            if (rem.size() == 0)
                throw new IllegalStateException("remaining vertices set should "
                        + "not be empty");
            // safe because rem contains Vertex type elements and has size != 0
            Vertex source = (Vertex) rem.toArray()[0];
            Set<Edge> untaken = new HashSet<>();
            Optional<Map<Integer, Integer>> opt = diveAndUpdate(source, visited,
                    untaken);
            if (opt.isEmpty())
                return false;
            Map<Integer, Integer> parents = opt.get();
            for (Edge edge: untaken) {
                if (AdjacencyGraph.isTailDescendantOfHead(edge.tail(),
                                edge.head(), parents))
                    return false;
            }
        }
        return true;
    }
}
