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

    @Test
    void instantiationOfLoopThrowsIllegalArgumentException() {
        Vertex v1 = new SimpleVertex("loop");
        Vertex v2 = new SimpleVertex("loop");
        assertThrows(IllegalArgumentException.class,
                () -> new SimpleEdge(v1, v2));
    }

    @Test
    void msgOfIAEOnLoopInstantiationIndicatesThatLoopsAreForbidden() {
        Vertex v1 = new SimpleVertex("loop");
        Vertex v2 = new SimpleVertex("loop");
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new SimpleEdge(v1, v2));
        assertEquals("loops are forbidden", e.getMessage());
    }

    @Test
    void instantiationOfValidEdgeDoesNotThrowExceptions() {
        new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2"));
    }

    @Test
    void headReturnsHeadOfEdge() {
        Vertex head = new SimpleVertex("head");
        Vertex tail = new SimpleVertex("tail");
        Edge sut = new SimpleEdge(tail, head);
        assertEquals(head, sut.head());
    }

    @Test
    void tailReturnsTailOfEdge() {
        Vertex head = new SimpleVertex("head");
        Vertex tail = new SimpleVertex("tail");
        Edge sut = new SimpleEdge(tail, head);
        assertEquals(tail, sut.tail());
    }
}
