package com.kpouer.wkt.shape;

import com.kpouer.wkt.ParseException;
import com.kpouer.wkt.WKT;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolygonTest {
    @Test
    void testNoArgConstructor() {
        Polygon polygon = new Polygon();
        assertEquals(0, polygon.getNpoints());
        assertEquals(0, polygon.getBarycenter().getX());
        assertEquals(0, polygon.getBarycenter().getY());
    }

    @Test
    void testBarycenter() throws ParseException {
        Polygon polygon = WKT.parseShape("POLYGON((5 5,5 10, 10 10, 10 5, 5 5))");
        assertEquals(5, polygon.getNpoints());
        assertEquals(7.5, polygon.getBarycenter().getX());
        assertEquals(7.5, polygon.getBarycenter().getY());
    }
}