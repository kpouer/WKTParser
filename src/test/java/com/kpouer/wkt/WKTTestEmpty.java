package com.kpouer.wkt;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class WKTTestEmpty {
    @Test
    void polygon1() {
        assertDoesNotThrow(() -> new WKT(new StringReader("POINT EMPTY")).Start());
    }
}