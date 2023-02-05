package com.kpouer.wkt.shape;

import com.kpouer.wkt.ParseException;
import com.kpouer.wkt.WKT;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineStringTest {
    @Test
    void testNoArgConstructor() {
        LineString lineString = new LineString();
        assertEquals(0, lineString.getNpoints());
        assertEquals(0, lineString.getBarycenter().getX());
        assertEquals(0, lineString.getBarycenter().getY());
    }

    @Test
    void testBarycenter2Points() throws ParseException {
        LineString lineString = WKT.parseShape("LINESTRING(5 5,10 10)");
        assertEquals(2, lineString.getNpoints());
        assertEquals(7.5, lineString.getBarycenter().getX());
        assertEquals(7.5, lineString.getBarycenter().getY());
    }

    @Test
    void testBarycenter2PointsReversed() throws ParseException {
        LineString lineString = WKT.parseShape("LINESTRING(10 10, 5 5)");
        assertEquals(2, lineString.getNpoints());
        assertEquals(7.5, lineString.getBarycenter().getX());
        assertEquals(7.5, lineString.getBarycenter().getY());
    }

    @Test
    void testBarycenterNPoints() throws ParseException {
        LineString lineString = WKT.parseShape("LINESTRING(5 5,5 10, 10 10, 10 5)");
        assertEquals(4, lineString.getNpoints());
        assertEquals(7.5, lineString.getBarycenter().getX());
        assertEquals(7.5, lineString.getBarycenter().getY());
    }

    @Test
    void testBarycenterNPointsReversed() throws ParseException {
        LineString lineString = WKT.parseShape("LINESTRING(10 10, 10 5,5 5,5 10)");
        assertEquals(4, lineString.getNpoints());
        assertEquals(7.5, lineString.getBarycenter().getX());
        assertEquals(7.5, lineString.getBarycenter().getY());
    }
}