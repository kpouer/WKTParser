package com.kpouer.wkt.shape;

import com.kpouer.wkt.ParseException;
import com.kpouer.wkt.WKT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private Point point;

    @BeforeEach
    void setUp() throws ParseException {
        point = WKT.parseShape("POINT   (-3.2011243453   -101.12124240)");
    }

    @Test
    void getBarycenter() throws ParseException {
        assertSame(point, point.getBarycenter());
    }

    @Test
    void getBounds2D() {
        var bounds = point.getBounds2D();
        assertEquals(point.getX(), bounds.getMinX());
        assertEquals(point.getY(), bounds.getMinY());
        assertEquals(1, bounds.getWidth());
        assertEquals(1, bounds.getHeight());
    }
}