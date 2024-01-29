package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
    
    @Test
    void labelOfTailIsGivenLabelAtConstruction() {
        Edge sut = Edge.of(0, 1);
        assertEquals(0, sut.tail().label());
    }

    @Test
    void labelOfHeadIsGivenLabelAtConstruction() {
        Edge sut = Edge.of(0, 1);
        assertEquals(1, sut.head().label());
    }

    @Test
    void equalsReturnsTrueIfTheOtherEdgeHasSameEqualVertices() {
        Edge sut1 = Edge.of(0, 1);
        Edge sut2 = Edge.of(0, 1);
        assertEquals(sut1, sut2);
    }

    @Test
    void equalsReturnsFalseIfTheOtherEdgeHasADifferentHead() {
        Edge sut1 = Edge.of(0, 1);
        Edge sut2 = Edge.of(0, 2);
        assertNotEquals(sut1, sut2);
    }
}
