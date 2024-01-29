package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

class EdgeFunctionTest {

    @Test
    void addNullVerticesThrowsNPEWithMessage() {
        EdgeFunction sut = new EdgeFunction();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.add(null, null, 42));
        assertEquals("vertices must be non-null", e.getMessage());
    }

    @Test
    void getNullVerticesThrowsNPEWithMessage() {
        EdgeFunction sut = new EdgeFunction();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.get(null, null));
        assertEquals("vertices must be non-null", e.getMessage());
    }

    @Test
    void getAbsentVerticesThrowsNSEEWithMessage() {
        EdgeFunction sut = new EdgeFunction();
        Exception e = assertThrows(NoSuchElementException.class,
                () -> sut.get(Vertex.of(0), Vertex.of(1)));
        assertEquals("no such edge", e.getMessage());
    }

    @Test
    void getReturnsAssociatedValue() {
        EdgeFunction sut = new EdgeFunction();
        sut.add(Vertex.of(0), Vertex.of(1), 42);
        assertEquals(42, sut.get(Vertex.of(0), Vertex.of(1)));
    }

    @Test
    void removeNullVerticesThrowsNPEWithMessage() {
        EdgeFunction sut = new EdgeFunction();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.remove(null, null));
        assertEquals("vertices must be non-null", e.getMessage());
    }

    @Test
    void getOfRemovedAssociationThrowsNSEEWithMessage() {
        EdgeFunction sut = new EdgeFunction();
        sut.add(Vertex.of(0), Vertex.of(1), 42);
        sut.remove(Vertex.of(0), Vertex.of(1));
        Exception e = assertThrows(NoSuchElementException.class,
                () -> sut.get(Vertex.of(0), Vertex.of(1)));
        assertEquals("no such edge", e.getMessage());
    }
}
