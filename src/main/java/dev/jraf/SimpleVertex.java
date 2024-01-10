package dev.jraf;

/**
 * A simple implementation of {@code Vertex}.
 *
 * @author Guillermo Morón Usón
 */
public class SimpleVertex implements Vertex {

    // Messages to show when an exception is thrown
    private static final String ILLEGAL_LABEL_LENGTH_MSG = "label must have a "
        + "length superior or equal to 1 and strictly inferior to 256";
    private static final String NULL_LABEL_MSG = "label must be non-null";

    // Constants
    private static final int MAX_LENGTH_LABEL = 255;

    // The label
    private final String label;

    /**
     * Class constructor specifying the label. The label must be a non-null
     * string of length superior or equal to 1 and strictly inferior to 256.
     *
     * @param label the label for the new instance
     */
    public SimpleVertex(String label) {
        if (label == null) {
            throw new NullPointerException(NULL_LABEL_MSG);
        }
        if (label.equals("") || label.length() > MAX_LENGTH_LABEL) {
            throw new IllegalArgumentException(ILLEGAL_LABEL_LENGTH_MSG);
        }
        this.label = label;
    }

    /**
     * {@inheritDoc}
     */
    @Override public String label() {
        return this.label;
    }
}
