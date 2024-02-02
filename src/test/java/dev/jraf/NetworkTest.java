package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.NoSuchElementException;

class NetworkTest {

    @Test
    void verticesOfNewNetworkReturnsListOf2VerticesThatAreEqualToSrcAndSnk() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(1));
        List<Vertex> vertices = sut.vertices();
        boolean size2 = vertices.size() == 2;
        boolean containsSrc = vertices.contains(Vertex.of(0));
        boolean containsSnk = vertices.contains(Vertex.of(1));
        assertTrue(size2 && containsSrc && containsSnk);
    }

    @Test
    void addEdgeToSrcThrowsIAEWithMessage() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(1));
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> sut.add(Vertex.of(2), Vertex.of(0), 1));
        assertEquals("head is source", e.getMessage());
    }

    @Test
    void addEdgeFromSinkThrowsIAEWithMessage() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(1));
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> sut.add(Vertex.of(1), Vertex.of(2), 1));
        assertEquals("tail is sink", e.getMessage());
    }

    @Test
    void newAdjacencyWithEqualSrcAndSnkThrowsIAEWithMessage() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> Network.newAdjacency(Vertex.of(0), Vertex.of(0)));
        assertEquals("source and sink must be not equal", e.getMessage());
    }

    @Test
    void newAdjacencyWithNullVerticesThrowsNPEWithMessage() {
        Exception e = assertThrows(NullPointerException.class,
                () -> Network.newAdjacency(null, null));
        assertEquals("vertices must be non-null", e.getMessage());
    }

    @Test
    void capacityOfNullVerticesThrowsNPEWithMessage() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(1));
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.capacity(null, null));
        assertEquals("vertices must be non-null", e.getMessage());
    }

    @Test
    void capacityOfEdgeAbsentFromGraphThrowsNSEEWithMessage() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(1));
        Exception e = assertThrows(NoSuchElementException.class,
                () -> sut.capacity(Vertex.of(0), Vertex.of(1)));
        assertEquals("no such edge", e.getMessage());
    }

    @Test
    void addVerticesWith0CapacityThrowsIAEWithMessage() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(1));
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> sut.add(Vertex.of(0), Vertex.of(2), 0));
        assertEquals("capacity must be > 0", e.getMessage());
    }

    @Test
    void capacityOfPresentEdgeReturnsCapacityOfEdge() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(1));
        sut.add(Vertex.of(0), Vertex.of(1), 42);
        assertEquals(42, sut.capacity(Vertex.of(0), Vertex.of(1)));
    }

    @Test
    void addLoopThrowsIAEWithMessage() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(1));
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> sut.add(Vertex.of(2), Vertex.of(2), 42));
        assertEquals("edge cannot loop", e.getMessage());
    }

    @Test
    void addNullVerticesThrowNPEWithMessage() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(1));
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.add(null, null, 42));
        assertEquals("vertices must be non-null", e.getMessage());
    }

    @Test
    void neighborsAndCapacitiesAreCorrectInBiggerNetwork() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(4));
        assertEquals(Vertex.of(0), sut.source());
        assertEquals(Vertex.of(4), sut.sink());
        sut.add(Vertex.of(0), Vertex.of(1), 5);
        sut.add(Vertex.of(0), Vertex.of(2), 8);
        sut.add(Vertex.of(0), Vertex.of(3), 6);
        sut.add(Vertex.of(1), Vertex.of(3), 9);
        sut.add(Vertex.of(2), Vertex.of(1), 7);
        sut.add(Vertex.of(2), Vertex.of(3), 4);
        sut.add(Vertex.of(3), Vertex.of(4), 3);
        assertEquals(5, sut.vertices().size());
        assertEquals(7, sut.capacity(Vertex.of(2), Vertex.of(1)));
        assertEquals(8, sut.capacity(Vertex.of(0), Vertex.of(2)));
        assertEquals(5, sut.capacity(Vertex.of(0), Vertex.of(1)));
        assertEquals(3, sut.capacity(Vertex.of(3), Vertex.of(4)));
        assertEquals(9, sut.capacity(Vertex.of(1), Vertex.of(3)));
        assertEquals(6, sut.capacity(Vertex.of(0), Vertex.of(3)));
        assertEquals(4, sut.capacity(Vertex.of(2), Vertex.of(3)));
    }
}
