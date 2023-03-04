package com.kpouer.wkt.shape;

import com.kpouer.wkt.ParseException;
import com.kpouer.wkt.WKT;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiPolygonTest {
    @Test
    void testMultiPolygon() throws ParseException {
        MultiPolygon multiPolygon = WKT.parseShape("MULTIPOLYGON ( ((30 20, 45 40, 10 40, 30 20)),((15 5, 40 10, 10 20, 5 10, 15 5)) )");
        var polygons = multiPolygon.getPolygons();
        assertEquals(2, polygons.size());
        var multiPolygon2 = new MultiPolygon();
        var polygon1 = new Polygon(new double[]{30, 45, 10, 30}, new double[]{20, 40, 40, 20});
        var polygon2 = new Polygon(new double[]{15, 40, 10, 5, 15}, new double[]{5, 10, 20, 10, 5});
        multiPolygon2.addPolygon(polygon1);
        multiPolygon2.addPolygon(polygon2);
        assertEquals(multiPolygon, multiPolygon2);
    }
}