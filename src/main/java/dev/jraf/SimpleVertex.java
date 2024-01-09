package dev.jraf;

public class SimpleVertex implements Vertex {

    private static final String ILLEGAL_LABEL_LENGTH_MSG = "label must have a "
        + "length superior or equal to 1 and strictly inferior to 256";
    private static final String NULL_LABEL_MSG = "label must be non-null";
    private static final int MAX_LENGTH_LABEL = 255;

    private final String label;

    public SimpleVertex(String label) {
        if (label == null) {
            throw new NullPointerException(NULL_LABEL_MSG);
        }
        if (label.equals("") || label.length() > MAX_LENGTH_LABEL) {
            throw new IllegalArgumentException(ILLEGAL_LABEL_LENGTH_MSG);
        }
        this.label = label;
    }

    @Override public String label() {
        return this.label;
    }
}
