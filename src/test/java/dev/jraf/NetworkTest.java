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
    void addVerticesWithMinus1CapacityThrowsIAEWithMessage() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(1));
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> sut.add(Vertex.of(0), Vertex.of(2), -1));
        assertEquals("value must be >= 0", e.getMessage());
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

    @Test
    void residualWithNullEdgeToNatFunctionThrowsNPEWithMessage() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(1));
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.residual(null));
        assertEquals("edge function must be non-null", e.getMessage());
    }

    @Test
    void residualOfNewlyCreatedNetworkReturnsNetworkWith2VerticesAndNoEdges() {
        Network sut = Network.newAdjacency(Vertex.of(0), Vertex.of(1));
        Network residual = sut.residual(new EdgeToNaturalFunction());
        List<Vertex> vertices = residual.vertices();
        boolean contains0 = vertices.contains(Vertex.of(0));
        boolean contains1 = vertices.contains(Vertex.of(1));
        boolean containsOnly0And1 = vertices.size() == 2;
        boolean noEdges = residual.neighborsOf(Vertex.of(0)).isEmpty() &&
            residual.neighborsOf(Vertex.of(1)).isEmpty();
        assertTrue(contains0 && contains1 && containsOnly0And1 && noEdges);
    }

    @Test
    void residualOfNewNetWithEdgeAndFlowThatMaxesOutCapacityReturnsEmptyNet() {
        Vertex v0 = Vertex.of(0);
        Vertex v1 = Vertex.of(1);
        Network sut = Network.newAdjacency(v0, v1);
        sut.add(v0, v1, 42);
        EdgeToNaturalFunction flow = new EdgeToNaturalFunction();
        flow.add(v0, v1, 42);
        Network residual = sut.residual(flow);
        List<Vertex> vertices = residual.vertices();
        boolean contains0 = vertices.contains(v0);
        boolean contains1 = vertices.contains(v1);
        boolean containsOnly0And1 = vertices.size() == 2;
        boolean noEdges = residual.neighborsOf(v0).isEmpty() &&
            residual.neighborsOf(v1).isEmpty();
        assertTrue(contains0 && contains1 && containsOnly0And1 && noEdges);
    }

    @Test
    void residualWithFlowThatDoesNotRespectCapacityConstraintThrowsIAEAndMsg() {
        Vertex v0 = Vertex.of(0);
        Vertex v1 = Vertex.of(1);
        Network sut = Network.newAdjacency(v0, v1);
        sut.add(v0, v1, 42);
        EdgeToNaturalFunction flow = new EdgeToNaturalFunction();
        flow.add(v0, v1, 43);
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> sut.residual(flow));
        assertEquals("flow does not respect capacity constraint",
                e.getMessage());
    }

    @Test
    void residualOfBiggerExampleIsCorrect() {
        Vertex v0 = Vertex.of(0);
        Vertex v1 = Vertex.of(1);
        Vertex v2 = Vertex.of(2);
        Vertex v3 = Vertex.of(3);
        Vertex v4 = Vertex.of(4);
        Network sut = Network.newAdjacency(v0, v4);
        sut.add(v0, v1, 7);
        sut.add(v0, v2, 3);
        sut.add(v1, v3, 4);
        sut.add(v1, v2, 1);
        sut.add(v1, v4, 8);
        sut.add(v2, v4, 9);
        sut.add(v3, v4, 2);
        EdgeToNaturalFunction flow = new EdgeToNaturalFunction();
        flow.add(v0, v1, 6);
        flow.add(v0, v2, 2);
        flow.add(v1, v3, 0);
        flow.add(v1, v2, 1);
        flow.add(v1, v4, 5);
        flow.add(v2, v4, 3);
        flow.add(v3, v4, 0);
        Network residual = sut.residual(flow);
        List<Vertex> vertices = residual.vertices();
        boolean contains0 = vertices.contains(v0);
        boolean contains1 = vertices.contains(v1);
        boolean contains2 = vertices.contains(v2);
        boolean contains3 = vertices.contains(v3);
        boolean contains4 = vertices.contains(v4);  
        boolean containsOnlyThose = vertices.size() == 5;
        boolean cap02 = residual.capacity(v0, v2) == 1;
        boolean cap01 = residual.capacity(v0, v1) == 1;
        boolean cap14 = residual.capacity(v1, v4) == 3;
        boolean cap24 = residual.capacity(v2, v4) == 6;
        boolean cap13 = residual.capacity(v1, v3) == 4;
        boolean cap34 = residual.capacity(v3, v4) == 2;
        boolean correctCap = cap02 && cap01 && cap14 && cap24 && cap13 && cap34;
        assertTrue(contains0 && contains1 && containsOnlyThose && correctCap);
    }
}
