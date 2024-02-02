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
                () -> sut.add(Vertex.of(2), Vertex.of(0)));
        assertEquals("head is source", e.getMessage());
    }

    @Test
    void addEdgeFromSinkThrowsIAEWithMessage() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(1));
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> sut.add(Vertex.of(1), Vertex.of(2)));
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
}
