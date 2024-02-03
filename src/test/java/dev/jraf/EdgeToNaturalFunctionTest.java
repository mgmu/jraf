package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class EdgeToNaturalFunctionTest {

    @Test
    void addCapacityValueOfMinus1ThrowsIAEWithMessage() {
        EdgeToNaturalFunction sut = new EdgeToNaturalFunction();
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> sut.add(Vertex.of(0), Vertex.of(1), -1));
        assertEquals("value must be >= 0", e.getMessage());
    }
}
