package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleVertexTest {

    @Test
    void emptyLabelThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new SimpleVertex(""));
    }

    @Test
    void msgOfIllegalArgumentExceptionOnInstantiationIndicatesBadLength() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> new SimpleVertex(""));
        assertEquals("label must have a length superior or equal to 1 and "
                + "strictly inferior to 256", e.getMessage());
    }

    @Test
    void nullLabelThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SimpleVertex(null));
    }

    @Test
    void msgOfNullPointerExceptionOnInstantiationTellsThatLabelIsNull() {
        Exception e = assertThrows(NullPointerException.class,
                () -> new SimpleVertex(null));
        assertEquals("label must be non-null", e.getMessage());
    }

    @Test
    void labelLengthStrictlySuperiorTo255ThrowsIllArgExc() {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" // 50 a
            + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" // 100 a
            + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" // 150 a
            + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" // 200 a
            + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" // 250 a
            + "aaaaaa"; // 256 a in total
        assertThrows(IllegalArgumentException.class, () -> new SimpleVertex(s));
    }

    @Test
    void labelGetterReturnsLabelGivenAtInstantiation() {
        String label = "Zaphod";
        Vertex sut = new SimpleVertex(label);
        assertEquals(label, sut.label());
    }

    @Test
    void equalsOnSameVertexObjectReturnsTrue() {
        Vertex sut = new SimpleVertex("Ford");
        assertEquals(sut, sut);
    }

    @Test
    void equalsWithObjectThatIsNotAVertexReturnsFalse() {
        Vertex sut = new SimpleVertex("Trillian");
        assertNotEquals(sut, 5);
    }

    @Test
    void equalsOnVerticesWithEqualLabelsAreEqual() {
        Vertex v1 = new SimpleVertex("hello");
        Vertex v2 = new SimpleVertex("hello");
        assertEquals(v1, v2);
    }

    @Test
    void equalsIsSymmetric() {
        Vertex v1 = new SimpleVertex("Arthur");
        Vertex v2 = new SimpleVertex("Arthur");
        assertEquals(v1, v2);
        assertEquals(v2, v1);
    }

    void equalsIsTransitive() {
        Vertex v1 = new SimpleVertex("Slartibartfast");
        Vertex v2 = new SimpleVertex("Slartibartfast");
        Vertex v3 = new SimpleVertex("Slartibartfast");
        assertEquals(v1, v2);
        assertEquals(v2, v3);
        assertEquals(v1, v3);
    }
}
