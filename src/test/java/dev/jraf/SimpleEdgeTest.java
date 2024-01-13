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

    @Test
    void equalsOnSameEdgeObjectReturnsTrue() {
        Vertex head = new SimpleVertex("head");
        Vertex tail = new SimpleVertex("tail");
        Edge sut = new SimpleEdge(tail, head);
        assertEquals(sut, sut);
    }

    @Test
    void equalsWithNullReturnsFalse() {
        Vertex head = new SimpleVertex("head");
        Vertex tail = new SimpleVertex("tail");
        Edge sut = new SimpleEdge(tail, head);
        assertNotEquals(sut, null);
    }

    @Test
    void equalsWithObjectThatIsNotAnEdgeReturnsFalse() {
        Vertex head = new SimpleVertex("head");
        Vertex tail = new SimpleVertex("tail");
        Edge sut = new SimpleEdge(tail, head);
        assertNotEquals(sut, 42);
    }

    @Test
    void equalsOnEqualEdgesReturnsTrue() {
        Vertex hd1 = new SimpleVertex("head");
        Vertex tl1 = new SimpleVertex("tail");
        Edge sut1 = new SimpleEdge(tl1, hd1);
        Vertex hd2 = new SimpleVertex("head");
        Vertex tl2 = new SimpleVertex("tail");
        Edge sut2 = new SimpleEdge(tl2, hd2);
        assertEquals(sut1, sut2);
    }

    @Test
    void equalsIsSymmetric() {
        Vertex hd1 = new SimpleVertex("head");
        Vertex tl1 = new SimpleVertex("tail");
        Edge sut1 = new SimpleEdge(tl1, hd1);
        Vertex hd2 = new SimpleVertex("head");
        Vertex tl2 = new SimpleVertex("tail");
        Edge sut2 = new SimpleEdge(tl2, hd2);
        assertEquals(sut1, sut2);
        assertEquals(sut1, sut2);
    }

    @Test
    void equalsOnDifferentEdgesReturnsFalse() {
        Vertex v1 = new SimpleVertex("1");
        Vertex v2 = new SimpleVertex("2");
        Vertex v3 = new SimpleVertex("3");
        Edge sut1 = new SimpleEdge(v1, v2);
        Edge sut2 = new SimpleEdge(v1, v3);
        assertNotEquals(sut1, sut2);
    }

    @Test
    void equalsIsTransitive() {
        Edge e1 = new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2"));
        Edge e2 = new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2"));
        Edge e3 = new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2"));
        assertEquals(e1, e2);
        assertEquals(e2, e3);
        assertEquals(e1, e3);
    }
}
