package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import java.util.List;

class AdjacencyGraphTest {

    @Test
    void verticesOfNewGraphReturnsTheEmptyList() {
        Graph sut = new AdjacencyGraph();
        assertTrue(sut.vertices().isEmpty());
    }

    @Test
    void addNullVertexThrowsNullPointerExceptionWithMessage() {
        Graph sut = new AdjacencyGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.add(null));
        assertEquals("vertex must be non-null", e.getMessage());
    }

    @Test
    void verticesOfGraphWith2VerticesReturnsListOfLength2() {
        Graph sut = new AdjacencyGraph();
        sut.add(Vertex.of(0));
        sut.add(Vertex.of(1));
        assertEquals(2, sut.vertices().size());
    }

    @Test
    void addNullVerticesWhenAddingEdgeThrowsNPEWithMessage() {
        Graph sut = new AdjacencyGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.add(null, null));
        assertEquals("vertices must be non-null", e.getMessage());
    }

    @Test
    void addEdgeBetweenUnexistentVerticesAddsTheVerticesToo() {
        Graph sut = new AdjacencyGraph();
        sut.add(Vertex.of(0), Vertex.of(1));
        sut.add(Vertex.of(1), Vertex.of(2));
        assertEquals(3, sut.vertices().size());
    }

    @Test
    void neighborsOfNullVertexThrowsNPEWithMessage() {
        Graph sut = new AdjacencyGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.neighborsOf(null));
        assertEquals("vertex must be non-null", e.getMessage());
    }

    @Test
    void neighborsOfAbsentVertexThrowsNSEEWithMessage() {
        Graph sut = new AdjacencyGraph();
        Exception e = assertThrows(NoSuchElementException.class,
                () -> sut.neighborsOf(Vertex.of(0)));
        assertEquals("no such vertex", e.getMessage());
    }

    @Test
    void neighborsOfVertexWithSelfLoopAnd2OtherVerticesReturnsListOfSize3() {
        Graph sut = new AdjacencyGraph();
        sut.add(Vertex.of(0), Vertex.of(0));
        sut.add(Vertex.of(0), Vertex.of(1));
        sut.add(Vertex.of(0), Vertex.of(2));
        assertEquals(3, sut.neighborsOf(Vertex.of(0)).size());
    }

    @Test
    void neighborsOfVertexReturnsItsNeighbors() {
        Graph sut = new AdjacencyGraph();
        sut.add(Vertex.of(0), Vertex.of(0));
        sut.add(Vertex.of(0), Vertex.of(1));
        sut.add(Vertex.of(0), Vertex.of(2));
        List<Vertex> neighbors = sut.neighborsOf(Vertex.of(0));
        boolean contains0 = neighbors.contains(Vertex.of(0));
        boolean contains1 = neighbors.contains(Vertex.of(1));
        boolean contains2 = neighbors.contains(Vertex.of(2));
        boolean containsOnlyThose = neighbors.size() == 3;
        assertTrue(contains0 && contains1 && contains2 && containsOnlyThose);
    }

    @Test
    void removeNullVerticesThrowsNPEWithMessage() {
        Graph sut = new AdjacencyGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.remove(null, null));
        assertEquals("vertices must be non-null", e.getMessage());
    }

    @Test
    void removeAbsentVerticesThrowsIAEWithMessage() {
        Graph sut = new AdjacencyGraph();
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> sut.remove(Vertex.of(0), Vertex.of(1)));
        assertEquals("vertices must be present", e.getMessage());
    }

    @Test
    void removeEdgeRemovesEdge() {
        Graph sut = new AdjacencyGraph();
        sut.add(Vertex.of(0), Vertex.of(1));
        sut.add(Vertex.of(1), Vertex.of(2));
        sut.remove(Vertex.of(0), Vertex.of(1));
        List<Vertex> vertices = sut.vertices();
        boolean contains0 = vertices.contains(Vertex.of(0));
        boolean contains1 = vertices.contains(Vertex.of(1));
        boolean contains2 = vertices.contains(Vertex.of(2));
        boolean noNeighbors0 = sut.neighborsOf(Vertex.of(0)).size() == 0;
        boolean oneNeighbor1 = sut.neighborsOf(Vertex.of(1)).size() == 1;
        assertTrue(contains0 && contains1 && contains2 && noNeighbors0
                && oneNeighbor1);
    }

    @Test
    void isAcyclicReturnsTrueForTheEmptyGraph() {
        Graph sut = new AdjacencyGraph();
        assertTrue(sut.isAcyclic());
    }

    @Test
    void isAcyclicReturnsFalseForNonEmptyGraphWithoutCycles() {
        Graph sut = new AdjacencyGraph();
        sut.add(Vertex.of(0), Vertex.of(1));
        sut.add(Vertex.of(1), Vertex.of(2));
        assertFalse(sut.isAcyclic());
    }
}
