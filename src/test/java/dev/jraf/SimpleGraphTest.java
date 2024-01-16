package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

class SimpleGraphTest {

    @Test
    void getVerticesOfGraphDoesNotReturnNull() {
        Graph sut = new SimpleGraph();
        assertFalse(sut.vertices() == null);
    }

    @Test
    void getEdgesOfGraphDoesNotReturnNull() {
        Graph sut = new SimpleGraph();
        assertFalse(sut.edges() == null);
    }

    @Test
    void newGraphReturnsEmptyGraph() {
        Graph sut = new SimpleGraph();
        assertTrue(sut.vertices().isEmpty() && sut.vertices().isEmpty());
    }

    @Test
    void addNullVertexThrowsNullPointerException() {
        Graph sut = new SimpleGraph();
        assertThrows(NullPointerException.class, () -> sut.add((Vertex)null));
    }

    @Test
    void addNullVertexThrowsNPEWithMessage() {
        Graph sut = new SimpleGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.add((Vertex)null));
        assertEquals("vertex must be non-null", e.getMessage());
    }

    @Test
    void addAbsentVertexIncreaseSizeOfGraphVerticesByOne() {
        Graph sut = new SimpleGraph();
        sut.add(new SimpleVertex("absent"));
        int size = sut.vertices().size();
        assertEquals(1, size);
    }

    @Test
    void addPresentVertexDoesNotChangeSizeOfGraphVertices() {
        Graph sut = new SimpleGraph();
        sut.add(new SimpleVertex("absent"));
        sut.add(new SimpleVertex("absent"));
        int size = sut.vertices().size();
        assertEquals(1, size);
    }

    @Test
    void verticesReturnsPresentVertices() {
        Graph sut = new SimpleGraph();
        Vertex v1 = new SimpleVertex("1");
        Vertex v2 = new SimpleVertex("2");
        Vertex v3 = new SimpleVertex("3");
        sut.add(v1);
        sut.add(v2);
        sut.add(v3);
        List<Vertex> vertices = sut.vertices();
        assertTrue(vertices.contains(v1) && vertices.contains(v2)
                && vertices.contains(v3) && vertices.size() == 3);
    }

    @Test
    void removeNullVertexThrowsNullPointerException() {
        Graph sut = new SimpleGraph();
        assertThrows(NullPointerException.class,
                () -> sut.remove((Vertex)null));
    }

    @Test
    void removeNullVertexThrowsNPEWithMessage() {
        Graph sut = new SimpleGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.remove((Vertex)null));
        assertEquals("vertex must be non-null", e.getMessage());
    }

    @Test
    void removeAbsentVertexDoesNotChangeGraphVertices() {
        Graph sut = new SimpleGraph();
        sut.remove(new SimpleVertex("absent"));
        int size = sut.vertices().size();
        assertEquals(0, size);
    }

    @Test
    void removePresentVertexDecreasesVerticesSizeByOne() {
        Graph sut = new SimpleGraph();
        sut.add(new SimpleVertex("1"));
        sut.add(new SimpleVertex("2"));
        sut.add(new SimpleVertex("3"));
        sut.remove(new SimpleVertex("2"));
        int size = sut.vertices().size();
        assertEquals(2, size);
    }

    @Test
    void removeVertexByNullLabelThrowsNullPointerException() {
        AbstractGraph sut = new SimpleGraph();
        assertThrows(NullPointerException.class,
                () -> sut.remove((String)null));
    }

    @Test
    void removeVertexByNullLabelThrowsNPEWithMessage() {
        AbstractGraph sut = new SimpleGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.remove((String)null));
        assertEquals("label must be non-null", e.getMessage());
    }

    @Test
    void removeAbsentLabelDoesNotChangeNumberOfVertices() {
        AbstractGraph sut = new SimpleGraph();
        sut.add(new SimpleVertex("1"));
        sut.add(new SimpleVertex("2"));
        sut.remove("absent");
        int size = sut.vertices().size();
        assertEquals(2, size);
    }

    @Test
    void removePresentLabelDecreasesVerticesSizeByOne() {
        AbstractGraph sut = new SimpleGraph();
        sut.add(new SimpleVertex("1"));
        sut.remove("1");
        int size = sut.vertices().size();
        assertEquals(0, size);
    }

    @Test
    void addNullEdgeThrowsNPE() {
        Graph sut = new SimpleGraph();
        assertThrows(NullPointerException.class, () -> sut.add((Edge)null));
    }

    @Test
    void addNullEdgeThrowsNPEWithMessage() {
        Graph sut = new SimpleGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.add((Edge)null));
        assertEquals("edge must be non-null", e.getMessage());
    }

    @Test
    void addAbsentEdgeBetweenPresentVerticesIncreaseEdgesButNotVertices() {
        Graph sut = new SimpleGraph();
        Vertex v1 = new SimpleVertex("1");
        Vertex v2 = new SimpleVertex("2");
        sut.add(v1);
        sut.add(v2);
        sut.add(new SimpleEdge(v1, v2));
        int s1 = sut.vertices().size();
        int s2 = sut.edges().size();
        assertEquals(2, s1);
        assertEquals(1, s2);
    }

    @Test
    void addAbsentEdgeBetweenPresentTailAndAbsentHeadAddsEdgeAndHeadVertex() {
        Graph sut = new SimpleGraph();
        Vertex v1 = new SimpleVertex("1");
        Vertex v2 = new SimpleVertex("2");
        sut.add(v1);
        sut.add(v2);
        sut.add(new SimpleEdge(v1, new SimpleVertex("absent")));
        int s1 = sut.vertices().size();
        int s2 = sut.edges().size();
        assertEquals(3, s1);
        assertEquals(1, s2);
    }

    @Test
    void addAbsentEdgeBetweenAbsentTailAndPresentHeadAddsEdgeAndHeadVertex() {
        Graph sut = new SimpleGraph();
        Vertex v1 = new SimpleVertex("1");
        Vertex v2 = new SimpleVertex("2");
        sut.add(v1);
        sut.add(v2);
        sut.add(new SimpleEdge(new SimpleVertex("absent"), v1));
        int s1 = sut.vertices().size();
        int s2 = sut.edges().size();
        assertEquals(3, s1);
        assertEquals(1, s2);
    }

    @Test
    void addAbsentEdgeBetweenAbsentVerticesAddsHeadAndTail() {
        Graph sut = new SimpleGraph();
        sut.add(new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2")));
        int s1 = sut.vertices().size();
        int s2 = sut.edges().size();
        assertEquals(2, s1);
        assertEquals(1, s2);
    }

    @Test
    void addPresentEdgeDoesNothing() {
        Graph sut = new SimpleGraph();
        sut.add(new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2")));
        sut.add(new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2")));
        int size = sut.edges().size();
        assertEquals(1, size);
    }

    @Test
    void removeNullEdgeThrowsNullPointerExceptionWithMessage() {
        Graph sut = new SimpleGraph();
        Exception e = assertThrows(NullPointerException.class ,
                () -> sut.remove((Edge)null));
        assertEquals("edge must be non-null", e.getMessage());
    }

    @Test
    void removeAbsentEdgeDoesNothing() {
        Graph sut = new SimpleGraph();
        sut.remove(
          new SimpleEdge(new SimpleVertex("1"), new SimpleVertex("2")));
        int size = sut.edges().size();
        assertEquals(0, size);
    }

    @Test
    void removePresentEdgeDoesNotAffectVerticesAndDecreasesEdgesSizeByOne() {
        Graph sut = new SimpleGraph();
        Edge edge = new SimpleEdge(new SimpleVertex("1"),
                new SimpleVertex("2"));
        sut.add(edge);
        sut.remove(edge);
        int s1 = sut.vertices().size();
        int s2 = sut.edges().size();
        assertEquals(2, s1);
        assertEquals(0, s2);
    }

    @Test
    void removeVertexRemovesAlsoEdgesForWhichItIsHeadOrTail() {
        Graph sut = new SimpleGraph();
        Vertex v1 = new SimpleVertex("1");
        Vertex v2 = new SimpleVertex("2");
        Vertex v3 = new SimpleVertex("3");
        Edge e1 = new SimpleEdge(v1, v2);
        Edge e2 = new SimpleEdge(v2, v3);
        Edge e3 = new SimpleEdge(v3, v1);
        sut.add(v1);
        sut.add(v2);
        sut.add(v3);
        sut.add(e1);
        sut.add(e2);
        sut.add(e3);
        sut.remove(v3); // removes v3, e2 and e3
        List<Vertex> vertices = sut.vertices();
        List<Edge> edges = sut.edges();
        boolean v3Absent = !vertices.contains(v3);
        boolean v1AndV2Present = vertices.contains(v2) && vertices.contains(v1);
        boolean e2Ande3Absent = !edges.contains(e2) && !edges.contains(e3);
        boolean e1Present = edges.contains(e1);
        assertTrue(v3Absent && v1AndV2Present && e2Ande3Absent && e1Present);
    }

    @Test
    void removeEdgeFromNullTailLabelThrowsNPEWithMessage() {
        Graph sut = new SimpleGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.remove((String)null, "2"));
        assertEquals("labels must be non-null", e.getMessage());
    }

    @Test
    void removeEdgeFromNullHeadLabelThrowsNPEWithMessage() {
        Graph sut = new SimpleGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.remove("1", (String)null));
        assertEquals("labels must be non-null", e.getMessage());
    }

    @Test
    void removeEdgeFromNullLabelsThrowsNPEWithMessage() {
        Graph sut = new SimpleGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.remove((String)null, (String)null));
        assertEquals("labels must be non-null", e.getMessage());        
    }

    @Test
    void removeAbsentEdgeFromLabelsDoesNotChangeEdges() {
        Graph sut = new SimpleGraph();
        Vertex v1 = new SimpleVertex("1");
        Vertex v2 = new SimpleVertex("2");
        Vertex v3 = new SimpleVertex("3");
        Edge e1 = new SimpleEdge(v1, v2);
        Edge e2 = new SimpleEdge(v2, v3);
        Edge e3 = new SimpleEdge(v3, v1);
        sut.add(v1);
        sut.add(v2);
        sut.add(v3);
        sut.add(e1);
        sut.add(e2);
        sut.add(e3);
        sut.remove("1", "3");
        assertEquals(3, sut.edges().size());
    }

    @Test
    void removePresentEdgeFromLabelsDecreasesNumberOfEdgesByOne() {
        Graph sut = new SimpleGraph();
        Vertex v1 = new SimpleVertex("1");
        Vertex v2 = new SimpleVertex("2");
        Vertex v3 = new SimpleVertex("3");
        Edge e1 = new SimpleEdge(v1, v2);
        Edge e2 = new SimpleEdge(v2, v3);
        Edge e3 = new SimpleEdge(v3, v1);
        sut.add(v1);
        sut.add(v2);
        sut.add(v3);
        sut.add(e1);
        sut.add(e2);
        sut.add(e3);
        sut.remove("2", "3");
        assertEquals(2, sut.edges().size());
    }

    @Test
    void outgoingEdgesOfNullVertexThrowsNPEWithMessage() {
        Graph sut = new SimpleGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.outgoingEdgesOf(null));
        assertEquals("vertex must be non-null", e.getMessage());
    }

    @Test
    void outgoingEdgesOfAbsentVertexReturnsEmptyOptional() {
        Graph sut = new SimpleGraph();
        Optional<List<Edge>> optEdges = sut.outgoingEdgesOf(
          new SimpleVertex("absent"));
        assertFalse(optEdges.isPresent());
    }

    @Test
    void outgoingEdgesOfPresentVertexButWithoutOutgoingEdgesReturnsEmptyList() {
        Graph sut = new SimpleGraph();
        sut.add(new SimpleVertex("1"));
        Optional<List<Edge>> optEdges =
            sut.outgoingEdgesOf(new SimpleVertex("1"));
        List<Edge> res = optEdges.get();
        assertTrue(res.isEmpty());
    }

    @Test
    void outgoingEdgesOfPresentVertexReturnsItsOutgoingEdges() {
        Graph sut = new SimpleGraph();
        Vertex v1 = new SimpleVertex("1");
        Vertex v2 = new SimpleVertex("2");
        Vertex v3 = new SimpleVertex("3");
        sut.add(v1);
        sut.add(v2);
        sut.add(v3);
        Edge e1 = new SimpleEdge(v3, v1);
        Edge e2 = new SimpleEdge(v3, v2);         
        sut.add(e1);
        sut.add(e2);
        List<Edge> sol = new ArrayList<>();
        sol.add(e1);
        sol.add(e2);
        Optional<List<Edge>> optEdges =
            sut.outgoingEdgesOf(new SimpleVertex("3"));
        assertTrue(optEdges.isPresent());
        List<Edge> res = optEdges.get();
        boolean e1Present = res.contains(e1);
        boolean e2Present = res.contains(e2);
        boolean juste1Ande2returned = res.size() == 2;
        assertTrue(e1Present && e2Present && juste1Ande2returned);
    }

    @Test
    void neighborsOfNullVertexThrowsNPEWithMessage() {
        Graph sut = new SimpleGraph();
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.neighborsOf(null));
        assertEquals("vertex must be non-null", e.getMessage());
    }

    @Test
    void neighborsOfAbsentVertexReturnsEmptyOptional() {
        Graph sut = new SimpleGraph();
        Optional<List<Vertex>> opt = sut.neighborsOf(new SimpleVertex("2"));
        assertFalse(opt.isPresent());
    }

    @Test
    void neighborsOfPresentVertexWithoutNeighborsReturnsOptionalOfEmptyList() {
        Graph sut = new SimpleGraph();
        Vertex v1 = new SimpleVertex("1");
        sut.add(v1);
        Optional<List<Vertex>> opt = sut.neighborsOf(v1);
        assertTrue(opt.isPresent());
        List<Vertex> res = opt.get();
        assertTrue(res.isEmpty());
    }

    @Test
    void neighborsOfPresentVertexReturnsNeighborsOfVertex() {
        Graph sut = new SimpleGraph();
        Vertex v1 = new SimpleVertex("1");
        Vertex v2 = new SimpleVertex("2");
        Vertex v3 = new SimpleVertex("3");
        sut.add(new SimpleEdge(v1, v2));
        sut.add(new SimpleEdge(v1, v3));
        Optional<List<Vertex>> opt = sut.neighborsOf(v1);
        assertTrue(opt.isPresent());
        List<Vertex> neighbors = opt.get();
        boolean v2IsNeighbor = neighbors.contains(v2);
        boolean v3IsNeighbor = neighbors.contains(v3);
        boolean justThem = neighbors.size() == 2;
        assertTrue(v2IsNeighbor && v3IsNeighbor && justThem);
    }

    @Test
    void numberOfVerticesInNewGraphIs0() {
        Graph sut = new SimpleGraph();
        assertEquals(0, sut.numVertices());
    }

    @Test
    void numberOfVerticesAfter2VertexAddsIs2() {
        Graph sut = new SimpleGraph();
        sut.add(new SimpleVertex("1"));
        sut.add(new SimpleVertex("2"));
        assertEquals(2, sut.numVertices());
    }

    @Test
    void numberOfVerticesAfter2VertexAnd1EdgeWithNewVertexAddsIs3() {
        Graph sut = new SimpleGraph();
        sut.add(new SimpleVertex("1"));
        sut.add(new SimpleVertex("2"));
        sut.add(new SimpleEdge("2", "3"));
        assertEquals(3, sut.numVertices());
    }
    
    @Test
    void numberOfEdgesInEmptyGraphIs0() {
        Graph sut = new SimpleGraph();
        assertEquals(0, sut.numEdges());
    }

    @Test
    void numberOfEdgesInGraphWith2VerticesIs2() {
        Graph sut = new SimpleGraph();
        sut.add(new SimpleEdge("1", "2"));
        sut.add(new SimpleEdge("2", "3"));
        assertEquals(2, sut.numEdges());
    }
}
