package dev.jraf;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    @Test
    void treeFromNullMapThrowsNPEWithMessage() {
        Exception e = assertThrows(NullPointerException.class,
                () -> new Tree(null));
        assertEquals("parents map must be non-null", e.getMessage());
    }
}
