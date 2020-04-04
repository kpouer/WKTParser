package com.kpouer.wkt;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WKTTestGeometryCollection {
    @Test
    void polygon1() throws ParseException {
        Start   start = new WKT(new StringReader("GEOMETRYCOLLECTION (POINT (40 10),\n" +
                                                     "LINESTRING (10 10, 20 20, 10 40),\n" +
                                                     "POLYGON ((40 40, 20 45, 45 30, 40 40)))")).Start();
        GeometryCollection geometryCollection = (GeometryCollection) start.jjtGetChild(0);

        assertEquals(3, geometryCollection.jjtGetNumChildren());

        assertTrue(geometryCollection.jjtGetChild(0) instanceof Point);
        assertTrue(geometryCollection.jjtGetChild(1) instanceof LineString);
        assertTrue(geometryCollection.jjtGetChild(2) instanceof Polygon);
    }
}