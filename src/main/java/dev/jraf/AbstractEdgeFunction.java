package dev.jraf;

import java.util.NoSuchElementException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * A function that associates edges to doubles. This class is the base class to
 * functions on edges, such as capacity, weight or flow and each operation that
 * involves modifying the function returns a new one that is a copy of the
 * previous plus the modification to maximize immutability. To be valid, a
 * function must have for each edge of a graph an associated double value.
 *
 * @author Guillermo Morón Usón
 * @see    Edge
 */
public abstract class AbstractEdgeFunction {

    // the association of edges and doubles
    protected final Map<Edge, Double> assoc = new HashMap<>();

    /**
     * Returns the value associated to the given edge. The edge must be non-null
     * and if no value is associated to the given edge, a NoSuchElementException
     * is thrown.
     *
     * @param edge a non-null edge
     * @return     a double, the value associated to the given edge
     */
    public double get(Edge edge) {
        Graphs.requireNonNull(edge);
        if (!assoc.containsKey(edge))
            throw new NoSuchElementException("no value associated to ("
                    + edge.tail().label() + ", " + edge.head().label() + ")");
        return assoc.get(edge);
    }

    /**
     * Returns the value associated to the edge between vertices of given label.
     * The labels must be non-null and if no value is associated to the given
     * edge, a NoSuchElementException is thrown.
     *
     * @param  lt a non-null string, the label of the tail of the edge
     * @param  lh a non-null string, the label of the head of the edge
     * @return    a double, the value associated to the edge between the
     *            vertices of given labels.
     */
    public double get(String lt, String lh) {
        Graphs.requireNonNull(lt);
        Graphs.requireNonNull(lh);
        for (Edge edge: assoc.keySet()) {
            if (edge.tail().label().equals(lt)
                    && edge.head().label().equals(lh)) {
                return assoc.get(edge);
            }
        }
        throw new NoSuchElementException("no value associated to (" + lt + ", "
                + lh + ")");
    }

    public List<Edge> edges() {
        List<Edge> edges = new ArrayList<>();
        edges.addAll(assoc.keySet());
        return edges;
    }

    /**
     * Returns a new edge function that preserves the associations of this edge
     * function plus the association of the given edge with the given value. The
     * edge must be non-null and if the edge has already a value associated,
     * nothing is done and the returned abstract edge function is this abstract
     * edge function.
     *
     * @param edge  the non-null edge to add
     * @param value the value to associate to the given edge
     * @return      a new abstract edge function if the operation had an effect,
     *              this abstract edge function otherwise.
     */
    public abstract AbstractEdgeFunction add(Edge edge, double value);

    /**
     * Returns a new edge function that preserves the associations of this edge
     * function minus the association of the given edge. The edge must be
     * non-null and if the edge has not a value associated to it, nothing is
     * done and the returned abstract edge function is this abstract edge
     * function.
     *
     * @param edge  the non-null edge to remove
     * @return      a new abstract edge function if the operation had an effect,
     *              this abstract edge function otherwise.
     */
    public abstract AbstractEdgeFunction remove(Edge edge);

    /**
     * Returns a new edge function that preserves the associations of this edge
     * function minus the association of the edge between the vertices specifed
     * by the given labels. The labels must be non-null and if the corresponding
     * edge has not a value associated to it, nothing is done and the returned
     * abstract edge function is this abstract edge function.
     *
     * @param lt the non-null label of the tail of the edge
     * @param lh the non-null label of the head of the edge
     * @return   a new abstract edge function if the operation had an effect,
     *           this abstract edge function otherwise.
     */
    public abstract AbstractEdgeFunction remove(String lt, String lh);
}
