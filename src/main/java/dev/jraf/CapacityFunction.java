package dev.jraf;

/**
 * An implementation of an abstract edge function corresponding to the function
 * that associates an edge to its capacity.
 *
 * @author Guillermo Morón Usón
 * @see    AbstractEdgeFunction
 */
public class CapacityFunction extends AbstractEdgeFunction {

    /**
     * {@inheritDoc}
     */
    @Override public AbstractEdgeFunction add(Edge edge, double value) {
        Graphs.requireNonNull(edge);
        if (assoc.containsKey(edge))
            return this;
        AbstractEdgeFunction f = new CapacityFunction();
        f.assoc.putAll(assoc);
        f.assoc.put(edge, value);
        return f;
    }

    /**
     * {@inheritDoc}
     */
    @Override public AbstractEdgeFunction remove(Edge edge) {
        Graphs.requireNonNull(edge);
        if (!assoc.containsKey(edge))
            return this;
        AbstractEdgeFunction f = new CapacityFunction();
        f.assoc.putAll(assoc);
        f.assoc.remove(edge);
        return f;
    }

    /**
     * {@inheritDoc}
     */
    @Override public AbstractEdgeFunction remove(String lt, String lh) {
        Graphs.requireNonNull(lt);
        Graphs.requireNonNull(lh);
        for (Edge edge: assoc.keySet()) {
            if (edge.tail().label().equals(lt)
                    && edge.head().label().equals(lh)) {
                AbstractEdgeFunction f = new CapacityFunction();
                f.assoc.putAll(assoc);
                f.assoc.remove(edge);
                return f;
            }
        }
        return this;
    }
}
