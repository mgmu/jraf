package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CapacityFunctionTest {

    @Test
    void addCapacityValueOf0ThrowsIAEWithMessage() {
        CapacityFunction sut = new CapacityFunction();
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> sut.add(Vertex.of(0), Vertex.of(1), 0));
        assertEquals("capacity must be > 0", e.getMessage());
    }
}
