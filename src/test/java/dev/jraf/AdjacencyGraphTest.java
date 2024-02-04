package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.Map;

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
    void isAcyclicReturnsTrueForLineGraph() {
        Graph sut = new AdjacencyGraph();
        sut.add(Vertex.of(0), Vertex.of(1));
        sut.add(Vertex.of(1), Vertex.of(2));
        assertFalse(sut.isAcyclic());
    }

    @Test
    void bfsFromNullThrowsNPEWithMessage() {
        Graph sut = new AdjacencyGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.breadthFirstSearch(null));
        assertEquals("vertex must be non-null", e.getMessage());
    }

    @Test
    void bfsWithAbsentVertexThrowsIAEWithMessage() {
        Graph sut = new AdjacencyGraph();
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> sut.breadthFirstSearch(Vertex.of(0)));
        assertEquals("vertex must be present", e.getMessage());
    }

    @Test
    void bfsFromPresentSrcAndGraphContainsOnlySrcReturnsAssocSrcToSrc() {
        Graph sut = new AdjacencyGraph();
        Vertex src = Vertex.of(0);
        sut.add(src);
        Map<Integer, Integer> parents = sut.breadthFirstSearch(src);
        boolean size1 = parents.size() == 1;
        boolean srcToSrc = src.label() == parents.get(src.label());
        assertTrue(size1 && srcToSrc);
    }

    @Test
    void bfsOfGraphCycleReturnsCorrectAssociation() {
        Graph sut = new AdjacencyGraph();
        Vertex v1 = Vertex.of(1);
        Vertex v2 = Vertex.of(2);
        Vertex v3 = Vertex.of(3);
        sut.add(v1, v2);
        sut.add(v2, v3);
        sut.add(v3, v1);
        Map<Integer, Integer> parents = sut.breadthFirstSearch(v2);
        boolean size3 = parents.size() == 3;
        boolean p3is2 = parents.get(v3.label()) == v2.label();
        boolean p1is3 = parents.get(v1.label()) == v3.label();
        assertTrue(size3 && p3is2 && p1is3);
    }

    @Test
    void bfsOfGraphWithMultipleConnComponentsReturnsCorrectAssociation() {
        Graph sut = new AdjacencyGraph();
        Vertex v1 = Vertex.of(1);
        Vertex v2 = Vertex.of(2);
        Vertex v3 = Vertex.of(3);
        Vertex v4 = Vertex.of(4);
        sut.add(v1, v2);
        sut.add(v3, v4);
        Map<Integer, Integer> parents = sut.breadthFirstSearch(v3);
        boolean size2 = parents.size() == 2;
        boolean p4is3 = parents.get(v4.label()) == v3.label();
        boolean p3is3 = parents.get(v3.label()) == v3.label();
        assertTrue(size2 && p4is3 && p3is3);
    }

    @Test
    void bfsOfSomeGraphReturnsCorrectAssociation() {
        Graph sut = new AdjacencyGraph();
        Vertex v1 = Vertex.of(1);
        Vertex v2 = Vertex.of(2);
        Vertex v3 = Vertex.of(3);
        Vertex v4 = Vertex.of(4);
        Vertex v5 = Vertex.of(5);
        Vertex v6 = Vertex.of(6);
        sut.add(v1, v2);
        sut.add(v1, v3);
        sut.add(v2, v3);
        sut.add(v2, v4);
        sut.add(v2, v6);
        sut.add(v3, v4);
        sut.add(v3, v5);
        sut.add(v4, v5);
        sut.add(v4, v6);
        sut.add(v5, v6);
        Map<Integer, Integer> parents = sut.breadthFirstSearch(v1);
        boolean sizeIs6 = parents.size() == 6;
        boolean p2is1 = parents.get(v2.label()) == v1.label();
        boolean p3is1 = parents.get(v3.label()) == v1.label();
        boolean p6is2 = parents.get(v6.label()) == v2.label();
        boolean p4is2 = parents.get(v4.label()) == v2.label();
        boolean p5is3 = parents.get(v5.label()) == v3.label();
        boolean p1is1 = parents.get(v1.label()) == v1.label();
        assertTrue(sizeIs6 && p2is1 && p3is1 && p6is2 && p4is2 && p5is3
                && p1is1);
    }

    @Test
    void isAcyclicReturnsTrueForGraphWithOneIsolatedVertex() {
        Graph sut = new AdjacencyGraph();
        sut.add(Vertex.of(0));
        assertTrue(sut.isAcyclic());
    }
}
