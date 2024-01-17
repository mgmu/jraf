package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

class CapacityFunctionTest {
    
    @Test
    void addNullEdgeThrowsNPEWithMessage() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.add(null, 42.0));
        assertEquals("edge must be non-null", e.getMessage());
    }

    @Test
    void getNullEdgeThrowsNPEWithMessage() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.get(null));
        assertEquals("edge must be non-null", e.getMessage());
    }

    @Test
    void getAbsentEdgeThrowsNoSuchElementExceptionWithMessage() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Vertex v1 = new SimpleVertex("1");
        Vertex v2 = new SimpleVertex("2");
        Exception e = assertThrows(NoSuchElementException.class,
                () -> sut.get(new SimpleEdge(v1, v2)));
        assertEquals("no value associated to (1, 2)", e.getMessage());
    }

    @Test
    void addAbsentEdgeReturnsNewNonNullAbstractEdgeFunction() {
        AbstractEdgeFunction sut = new CapacityFunction();
        AbstractEdgeFunction f = sut.add(new SimpleEdge(new SimpleVertex("1"),
                        new SimpleVertex("2")), 42.0);
        assertFalse(f == null);
        assertNotEquals(f, sut);
    }

    @Test
    void getWithPresentEdgeAssociatedto42Returns42() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Edge edge = new SimpleEdge(new SimpleVertex("1"),
                new SimpleVertex("2"));
        AbstractEdgeFunction f = sut.add(edge, 42.0);
        assertEquals(42.0, f.get(edge));
    }

    @Test
    void getWithPresentEdgeAssociatedTo21Returns21() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Edge edge = new SimpleEdge(new SimpleVertex("1"),
                new SimpleVertex("2"));
        AbstractEdgeFunction f = sut.add(edge, 21.0);
        assertEquals(21.0, f.get(edge));
    }

    @Test
    void getFromNullTailLabelThrowsNPEWithMessage() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.get(null, "2"));
        assertEquals("label must be non-null", e.getMessage());
    }

    @Test
    void getFromNullHeadLabelThrowsNPEWithMessage() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.get("1", null));
        assertEquals("label must be non-null", e.getMessage());
    }

    @Test
    void getAbsentEdgeFromLabelsThrowsNoSuchElementExceptionWithMessage() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Exception e = assertThrows(NoSuchElementException.class,
                () -> sut.get("1", "2"));
        assertEquals("no value associated to (1, 2)", e.getMessage());
    }

    @Test
    void getPresentEdgeFromLabelsAssociatedTo42Returns42() {
        AbstractEdgeFunction sut = new CapacityFunction();
        sut = sut.add(
          new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2")), 42.0);
        assertEquals(42.0, sut.get("1", "2"));
    }

    @Test
    void addPresentEdgeAssociationReturnsSameAbstractEdgeFunction() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Edge edge = new SimpleEdge(new SimpleVertex("1"),
                new SimpleVertex("2"));
        sut = sut.add(edge, 42.0);
        AbstractEdgeFunction other = sut.add(edge, 42.0);
        assertTrue(sut == other);
    }

    @Test
    void addPresentEdgeWithDifferentValueReturnsSameAbstractEdgeFunction() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Edge edge = new SimpleEdge(new SimpleVertex("1"),
                new SimpleVertex("2"));
        sut = sut.add(edge, 42.0);
        AbstractEdgeFunction other = sut.add(edge, 41.0);
        assertTrue(sut == other);
    }

    @Test
    void removeNullEdgeThrowsNPEWithMessage() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.remove(null));
        assertEquals("edge must be non-null", e.getMessage());
    }

    @Test
    void removeAbsentEdgeReturnsSameAbstractEdgeFunction() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Edge absent =
            new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2"));
        assertTrue(sut == sut.remove(absent));
    }

    @Test
    void getEdgeAssocFromLabelsAfterRemoveThrowsNoSuchElementWithMessage() {
        Exception e = assertThrows(NoSuchElementException.class,
                () -> {
                    AbstractEdgeFunction sut = new CapacityFunction();
                    Edge edge = new SimpleEdge(new SimpleVertex("1"),
                            new SimpleVertex("2"));
                    sut = sut.add(edge, 42.0).remove(edge);
                    sut.get("1", "2");
                });
        assertEquals("no value associated to (1, 2)", e.getMessage());
    }

    @Test
    void getEdgeAssocAfterRemoveThrowsNoSuchElementWithMessage() {
        Exception e = assertThrows(NoSuchElementException.class,
                () -> {
                    AbstractEdgeFunction sut = new CapacityFunction();
                    Edge edge = new SimpleEdge(new SimpleVertex("1"),
                            new SimpleVertex("2"));
                    sut = sut.add(edge, 42.0).remove(edge);
                    sut.get(edge);
                });
        assertEquals("no value associated to (1, 2)", e.getMessage());
    }

    @Test
    void addPreservesPreviousAssociationsExceptAddition() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Edge e1 = new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2"));
        Edge e2 = new SimpleEdge(new SimpleVertex("2"), new SimpleVertex("3"));
        Edge e3 = new SimpleEdge(new SimpleVertex("3"), new SimpleVertex("1"));
        sut = sut.add(e1, 3).add(e2, 4).add(e3, 5);
        assertEquals(3, sut.get(e1));
        assertEquals(4, sut.get(e2));
    }

    @Test
    void removePreservesPreviousAssociationsExceptRemoval() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Edge e1 = new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2"));
        Edge e2 = new SimpleEdge(new SimpleVertex("2"), new SimpleVertex("3"));
        Edge e3 = new SimpleEdge(new SimpleVertex("3"), new SimpleVertex("1"));
        sut = sut.add(e1, 3).add(e2, 4).add(e3, 5).remove(e2);
        assertEquals(3, sut.get(e1));
        assertEquals(5, sut.get(e3));
    }

    @Test
    void removeEdgeFromNullLabelsThrowsNPEWithMessage() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.remove(null, null));
        assertEquals("label must be non-null", e.getMessage());
    }

    @Test
    void removeAbsentEdgeFromLabelsReturnsSameAbstractEdgeFunction() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Edge e = new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2"));
        assertTrue(sut == sut.remove("1", "2"));
    }

    @Test
    void getAfterRemoveEdgeFromLabelsThrowsNoSuchElementExceptionWithMsg() {
        Exception e = assertThrows(NoSuchElementException.class,
                () -> {
                    AbstractEdgeFunction sut = new CapacityFunction();
                    Edge edge = new SimpleEdge(new SimpleVertex("a"),
                            new SimpleVertex("b"));
                    sut = sut.add(edge, 18.0).remove("a", "b");
                    sut.get(edge);
                });
        assertEquals("no value associated to (a, b)", e.getMessage());
    }

    @Test
    void removeFromLabelsPreservesPreviousAssociationsExceptRemoval() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Edge e1 = new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2"));
        Edge e2 = new SimpleEdge(new SimpleVertex("2"), new SimpleVertex("3"));
        Edge e3 = new SimpleEdge(new SimpleVertex("3"), new SimpleVertex("1"));
        sut = sut.add(e1, 3).add(e2, 4).add(e3, 5).remove("2", "3");
        assertEquals(3, sut.get(e1));
        assertEquals(5, sut.get(e3));
    }

    @Test
    void edgesOnInstanciationReturnsTheEmptyList() {
        AbstractEdgeFunction sut = new CapacityFunction();
        assertTrue(sut.edges().isEmpty());
    }

    @Test
    void edgesReturnsContainedEdges() {
        AbstractEdgeFunction sut = new CapacityFunction();
        Edge e1 = new SimpleEdge("1", "2");
        Edge e2 = new SimpleEdge("41", "42");
        sut = sut.add(e1, 1).add(e2, 2);
        List<Edge> edges = sut.edges();
        boolean twoEdges = edges.size() == 2;
        boolean containsE1 = edges.contains(e1);
        boolean containsE2 = edges.contains(e2);
        assertTrue(twoEdges && containsE1 && containsE2);
    }
}
