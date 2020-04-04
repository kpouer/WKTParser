package com.kpouer.wkt;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WKTTestMultiPoint {
    @Test
    void multiPoint() throws ParseException {
        Start   start = new WKT(new StringReader("MULTIPOINT (10 40, 40 30, 20 20, 30 10)")).Start();
        MultiPoint multiPoint = (MultiPoint) start.jjtGetChild(0);
        assertEquals(4, multiPoint.jjtGetNumChildren());
    }

    @Test
    void multiPoint2() throws ParseException {
        Start   start = new WKT(new StringReader("MULTIPOINT ((10 40),( 40 30), (20 20), (30 10))")).Start();
        MultiPoint multiPoint = (MultiPoint) start.jjtGetChild(0);
        assertEquals(4, multiPoint.jjtGetNumChildren());
    }
}