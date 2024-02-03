package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class WeightedGraphTest {
    
    @Test
    void addWeightOfAbsentVerticesAddsVerticesAndAssociation() {
        WeightedGraph sut = WeightedGraph.newAdjacencyWeightedGraph();
        sut.add(Vertex.of(0), Vertex.of(1), 30);
        sut.add(Vertex.of(1), Vertex.of(2), 42);
        List<Vertex> vertices = sut.vertices();
        boolean contains0 = vertices.contains(Vertex.of(0));
        boolean contains1 = vertices.contains(Vertex.of(1));
        boolean contains2 = vertices.contains(Vertex.of(2));
        boolean containsOnlyThose = vertices.size() == 3;
        boolean hasAssocBetween0And1 =
            sut.weight(Vertex.of(0), Vertex.of(1)) == 30;
        boolean hasAssocBetween1And2 =
            sut.weight(Vertex.of(1), Vertex.of(2)) == 42;
        assertTrue(contains0 && contains1 && contains2 && containsOnlyThose &&
                hasAssocBetween0And1 && hasAssocBetween1And2);
    }

    @Test
    void addWeightToPresentAssociationUpdatesAssociationValue() {
        WeightedGraph sut = WeightedGraph.newAdjacencyWeightedGraph();
        sut.add(Vertex.of(0), Vertex.of(1), 30);
        sut.add(Vertex.of(0), Vertex.of(1), 42);
        assertEquals(42, sut.weight(Vertex.of(0), Vertex.of(1)));
    }
}
