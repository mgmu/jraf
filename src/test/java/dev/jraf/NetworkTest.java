package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {

    @Test
    void instanciationWithNullVerticesThrowsNPEWithMessage() {
        Exception e = assertThrows(NullPointerException.class,
                () -> new Network(null, null));
        assertEquals("vertex must be non-null", e.getMessage());
    }

    @Test
    void instanciationWithSameVertexAsSourceAndSinkThrowsIAEWithMessage() {
        Vertex same = new SimpleVertex("2");
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new Network(same, same));
        assertEquals("source and sink must be different", e.getMessage());
    }

    @Test
    void sourceReturnsSourceOfNetwork() {
        Vertex src = new SimpleVertex("1");
        Vertex sink = new SimpleVertex("2");
        Network sut = new Network(src, sink);
        assertEquals(src, sut.source());
    }

    @Test
    void sinkReturnsSinkOfNetwork() {
        Vertex src = new SimpleVertex("1");
        Vertex sink = new SimpleVertex("2");
        Network sut = new Network(src, sink);
        assertEquals(sink, sut.sink());
    }

    @Test
    void numberOfVerticesIs2AfterNetworkCreationWithEmptyCapacityFunction() {
        Network sut = new Network(new SimpleVertex("1"), new SimpleVertex("2"));
        assertEquals(2, sut.numVertices());
    }

    @Test
    void sourceReturnedIs42IfNetworkCreatedWithSource42() {
        Vertex v42 = new SimpleVertex("42");
        Network sut = new Network(v42, new SimpleVertex("2"));
        assertEquals(v42, sut.source());
    }

    @Test
    void sinkReturnedIs42IfNetworkCreatedWithSink42() {
        Vertex v42 = new SimpleVertex("42");
        Network sut = new Network(new SimpleVertex("2"), v42);
        assertEquals(v42, sut.sink());
    }

    @Test
    void instanciationWithNullCapacityFunctionThrowsNPEWithMessage() {
        Vertex v1 = new SimpleVertex("2");
        Vertex v42 = new SimpleVertex("42");
        Exception e = assertThrows(NullPointerException.class,
                () -> new Network(v1, v42, null));
        assertEquals("capacity function must be non-null", e.getMessage());
    }

    @Test
    void instanciationWithCapFunAndNullVerticesThrowsNPEWithMessage() {
        Exception e = assertThrows(NullPointerException.class,
                () -> new Network(null, null, new CapacityFunction()));
        assertEquals("vertex must be non-null", e.getMessage());
    }

    @Test
    void instanciationWithCapFunAndSameVertexAsSrcSinkThrowsIAEWithMsg() {
        Vertex same = new SimpleVertex("same");
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new Network(same, same, new CapacityFunction()));
        assertEquals("source and sink must be different", e.getMessage());
    }

    @Test
    void sourceFromNetWithCapacityFunReturnsSourceGivenAtInstantiation() {
        Vertex src = new SimpleVertex("src");
        Vertex sink = new SimpleVertex("sink");
        Network sut = new Network(src, sink, new CapacityFunction());
        assertEquals(src, sut.source());
    }

    @Test
    void sinkFromNetWithCapacityFunReturnsSinkGivenAtInstantiation() {
        Vertex src = new SimpleVertex("src");
        Vertex sink = new SimpleVertex("sink");
        Network sut = new Network(src, sink, new CapacityFunction());
        assertEquals(sink, sut.sink());
    }

    @Test
    void isFullyAssociatedReturnsTrueOnCreation() {
        Vertex v1 = new SimpleVertex("1");
        Vertex v2 = new SimpleVertex("2");
        Network sut1 = new Network(v1, v2);
        AbstractEdgeFunction cap = new CapacityFunction();
        cap = cap.add(new SimpleEdge(v1, v2), 424);
        Network sut2 = new Network(v1, v2, (CapacityFunction) cap);
        assertTrue(sut1.isFullyAssociated());
        assertTrue(sut2.isFullyAssociated());
    }

    @Test
    void isFullyAssociatedReturnsTrueIfEveryEdgeIsAssociated() {
        Vertex src = new SimpleVertex("source");
        Vertex snk = new SimpleVertex("sink");
        Edge e1 = new SimpleEdge(src, snk);
        Edge e2 = new SimpleEdge("source", "1");
        Edge e3 = new SimpleEdge("1", "sink");
        AbstractEdgeFunction cap = new CapacityFunction();
        cap = cap.add(e1, 1).add(e2, 2).add(e3, 3);
        Network sut = new Network(src, snk, (CapacityFunction) cap);
        sut.add(e1);
        sut.add(e2);
        assertTrue(sut.isFullyAssociated());
    }

    @Test
    void overrideAddEdgeWithNullEdgeThrowsNPEWithMessage() {
        Exception e = assertThrows(NullPointerException.class,
                () -> {
                    Network sut = new Network(new SimpleVertex("1"),
                            new SimpleVertex("2"));                    
                    sut.add((Edge)null);
                });
        assertEquals("edge must be non-null", e.getMessage());
    }

    @Test
    void overrideAddEdgeIncreasesNumberOfEdges() {
        Network sut = new Network(new SimpleVertex("1"), new SimpleVertex("2"));
        sut.add(new SimpleEdge("1", "2"));
        assertEquals(1, sut.numEdges());
    }

    @Test
    void capacityOfNullEdgeThrowsNPEWithMessage() {
        Network sut = new Network(new SimpleVertex("1"), new SimpleVertex("2"));
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.capacity(null));
        assertEquals("edge must be non-null", e.getMessage());
    }

    @Test
    void capacityFromNullLabelsThrowsNPEWithMessage() {
        Network sut = new Network(new SimpleVertex("1"), new SimpleVertex("2"));
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.capacity((String)null, (String)null));
        assertEquals("label must be non-null", e.getMessage());
    }

    @Test
    void capacityFromNullVerticesThrowsNPEWithMessage() {
        Network sut = new Network(new SimpleVertex("1"), new SimpleVertex("2"));
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.capacity((Vertex)null, (Vertex)null));
        assertEquals("vertex must be non-null", e.getMessage());
    }

    @Test
    void addCapacityOfNullEdgeThrowsNPEWithMessage() {
        Network sut = new Network(new SimpleVertex("1"), new SimpleVertex("2"));
        Exception e = assertThrows(NullPointerException.class,
                () -> sut.addCapacity(null, 42));
        assertEquals("edge must be non-null", e.getMessage());
    }

    @Test
    void addCapacityOfMissingEdgeMakesNetworkFullyAssociated() {
        Network sut = new Network(new SimpleVertex("1"), new SimpleVertex("2"));
        Edge e = new SimpleEdge("1", "2");
        sut.add(e);
        sut.addCapacity(e, 42);
        assertTrue(sut.isFullyAssociated());
    }

    @Test
    void addMissingEdgeToNetMakesNetFullyAssociatedIfCapIsAlreadyProvided() {
        Network sut = new Network(new SimpleVertex("1"), new SimpleVertex("2"));
        Edge e1 = new SimpleEdge("1", "2");
        Edge e2 = new SimpleEdge("1", "3");
        Edge e3 = new SimpleEdge("3", "2");
        sut.add(e1);
        sut.add(e2);
        sut.addCapacity(e1, 1);
        sut.addCapacity(e2, 2);
        sut.addCapacity(e3, 3);
        sut.add(e3);
        assertTrue(sut.isFullyAssociated());
    }

    @Test
    void removeCapacityMakesNetNotFullyAssociated() {
        Network sut = new Network(new SimpleVertex("1"), new SimpleVertex("2"));
        Edge e1 = new SimpleEdge("1", "2");
        Edge e2 = new SimpleEdge("1", "3");
        Edge e3 = new SimpleEdge("3", "2");
        sut.add(e1);
        sut.add(e2);
        sut.add(e3);
        sut.addCapacity(e1, 1);
        sut.addCapacity(e2, 2);
        sut.addCapacity(e3, 3);
        assertTrue(sut.isFullyAssociated());
        sut.removeCapacity(e3);
        assertFalse(sut.isFullyAssociated());
    }
}
