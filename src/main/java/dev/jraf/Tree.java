package dev.jraf;

import java.util.List;
import java.util.Map;

/**
 * The implementation of a directed rooted tree. A directed rooted tree is a
 * directed graph, with a vertex marked as the root, such that there is no cycle
 * (a path in the graph that contains at least twice the same vertex) and there
 * is a unique path from the root to every other vertex of the graph.
 *
 * @author Guillermo Morón Usón
 * @see    Graph
 * @see    Vertex
 * @see    AdjacencyGraph
 */
public class Tree implements Graph {
    private final Graph graph;
    private final Vertex root;

    /**
     * Class constructor that specifies for each vertex label the label of its
     * parent, except for the root vertex that is associated to itself.
     *
     * @param parents a non-null map that associates each label to its parent
     *                label
     */
    Tree(Map<Integer, Integer> parents) {
        if (parents == null)
            throw new NullPointerException("parents map must be non-null");
        graph = new AdjacencyGraph();
        root = Vertex.of(0);
    }

    /**
     * Throws an UnsupportedOperationException as a Tree must be connected (that
     * is it cannot contain isolated vertices).
     *
     * @param vertex a vertex, possibly null
     */
    public void add(Vertex vertex) {
        throw new UnsupportedOperationException("cannot add isolated vertex");
    }

    /**
     * Throws an UnsupportedOperationException as a Tree must be connected (that
     * is it cannot contain isolated vertices).
     *
     * @param label an int, the label of a vertex
     */
    public void add(int label) {
        throw new UnsupportedOperationException("cannot add isolated vertex");
    }

    /**
     * {@inheritDoc}
     */
    public void add(Vertex tail, Vertex head) {
        if (tail == null || head == null)
            throw new NullPointerException("vertices must be non-null");
        if (head.equals(root))
            throw new IllegalArgumentException("root cannot have ingoing "
                    + "edges");
        graph.add(tail, head);
        if (!isAcyclic()) {
            graph.remove(tail, head);
            throw new IllegalArgumentException("cannot form cycle");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void add(int tail, int head) {
        add(Vertex.of(tail), Vertex.of(head));
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
     * Returns the root of this tree.
     *
     * @return a vertex, the tree of this tree
     */
    public Vertex root() {
        return root;
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
    @Override public Map<Integer, Integer> breadthFirstSearch(Vertex source) {
        return graph.breadthFirstSearch(source);
    }

    /**
     * {@inheritDoc}
     */
    @Override public boolean isAcyclic() {
        return false;
    }
}
