package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class VertexTest {
    
    @Test
    void equalsReturnsFalseIfVertexHasDifferentLabel() {
        Vertex sut1 = Vertex.of(0);
        Vertex sut2 = Vertex.of(1);
        assertNotEquals(sut1, sut2);
    }

    @Test
    void equalsReturnsFalseIfObjectIsNotAVertex() {
        Vertex sut = Vertex.of(2);
        assertNotEquals(sut, "not a vertex");
    }

    @Test
    void equalsReturnsTrueIfVerticesHaveSameLabel() {
        Vertex sut1 = Vertex.of(42);
        Vertex sut2 = Vertex.of(42);
        assertEquals(sut1, sut2);
    }
}
