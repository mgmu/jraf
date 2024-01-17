package dev.jraf;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public abstract class AbstractGraph implements Graph {

    // the vertices of this graph
    private final List<Vertex> vertices = new ArrayList<>();

    // the edges of this graph
    private final List<Edge> edges = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override public List<Vertex> vertices() {
        return vertices;
    }

    /**
     * {@inheritDoc}
     */
    @Override public List<Edge> edges() {
        return edges;
    }

    /**
     * {@inheritDoc}
     */
    @Override public void add(Vertex vertex) {
        Graphs.requireNonNull(vertex);
        if (!vertices.contains(vertex))
            vertices.add(vertex);
    }

    /**
     * {@inheritDoc}
     */
    @Override public void add(Edge edge) {
        Graphs.requireNonNull(edge);
        if (!vertices.contains(edge.head()))
            vertices.add(edge.head());
        if (!vertices.contains(edge.tail()))
            vertices.add(edge.tail());
        if (!edges.contains(edge))
            edges.add(edge);
    }

    /**
     * {@inheritDoc}
     */
    @Override public void remove(Vertex vertex) {
        Graphs.requireNonNull(vertex);
        List<Edge> toRemove = new ArrayList<>();
        for (Edge edge: edges) {
            if (edge.tail().equals(vertex) || edge.head().equals(vertex))
                toRemove.add(edge);
        }
        edges.removeAll(toRemove);
        vertices.remove(vertex);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(String label) {
        Graphs.requireNonNull(label);
        Vertex toRemove = null;
        for (Vertex vertex: vertices) {
            if (vertex.label().equals(label)) {
                toRemove = vertex;
            }
        }
        vertices.remove(toRemove);
    }

    /**
     * {@inheritDoc}
     */
    @Override public void remove(Edge edge) {
        Graphs.requireNonNull(edge);
        edges.remove(edge);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(String lt, String lh) {
        Graphs.requireNonNull(lt);
        Graphs.requireNonNull(lh);
        Edge toRemove = null;
        for (Edge edge: edges) {
            if (edge.tail().label().equals(lt)
                    && edge.head().label().equals(lh))
                toRemove = edge;
        }
        edges.remove(toRemove);
    }
}
