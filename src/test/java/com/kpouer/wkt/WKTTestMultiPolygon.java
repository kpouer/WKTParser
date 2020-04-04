package com.kpouer.wkt;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WKTTestMultiPolygon {
    @Test
    void polygon1() throws ParseException {
        Start   start = new WKT(new StringReader("MULTIPOLYGON ( ((30 20, 45 40, 10 40, 30 20)),((15 5, 40 10, 10 20, 5 10, 15 5)) )")).Start();
        MultiPolygon multiPolygon = (MultiPolygon) start.jjtGetChild(0);

        assertEquals(2, multiPolygon.jjtGetNumChildren());
    }
}