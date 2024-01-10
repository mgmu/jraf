package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleEdgeTest {

    @Test
    void instantiationWithNullVerticesThrowsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> new SimpleEdge(null, null));
    }

    @Test
    void msgOfNPEOnInstantiationIndicatesThatVerticesMustBeNonNull() {
        Exception e = assertThrows(NullPointerException.class,
                () -> new SimpleEdge(null, null));
        assertEquals("vertices must be non-null", e.getMessage());
    }
}
