package com.kpouer.wkt.shape;

import com.kpouer.wkt.ParseException;
import com.kpouer.wkt.WKT;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class LineStringTest {
    @Test
    void testNoArgConstructor() {
        LineString lineString = new LineString();
        assertEquals(0, lineString.getNpoints());
        assertEquals(0, lineString.getBarycenter().getX());
        assertEquals(0, lineString.getBarycenter().getY());
    }

    @ParameterizedTest
    @CsvSource(value = {
        "LINESTRING(5 5,10 10); 2; 7.5; 7.5",
        "LINESTRING(10 10, 5 5); 2; 7.5; 7.5",
        "LINESTRING(5 5,5 10, 10 10, 10 5); 4; 7.5; 7.5",
        "LINESTRING(10 10, 10 5,5 5,5 10); 4; 7.5; 7.5"
    },
    delimiter = ';')
    void testBarycenter2PointsParametized(String wkt, int nPoints, double x, double y) throws ParseException {
        LineString lineString = WKT.parseShape(wkt);
        assertEquals(nPoints, lineString.getNpoints());
        assertEquals(x, lineString.getBarycenter().getX());
        assertEquals(y, lineString.getBarycenter().getY());
    }
}