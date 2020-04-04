package com.kpouer.wkt;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WKTTestLineString {
    @Test
    void linestring1() throws ParseException {
        Start   start = new WKT(new StringReader("LINESTRING(30 10,-3.2011243453   -101.12124240)")).Start();
        LineString linestring2D = (LineString) start.jjtGetChild(0);
        Point node =(Point) linestring2D.jjtGetChild(0);
        Point node2 =(Point) linestring2D.jjtGetChild(1);
        assertEquals(2, linestring2D.jjtGetNumChildren());
        WKTTestPoint.checkPoint((Point) node, "30", "10");
        WKTTestPoint.checkPoint((Point) linestring2D.jjtGetChild(1), "-3.2011243453", "-101.12124240");
    }

    @Test
    void linestring2() throws ParseException {
        Start   start = new WKT(new StringReader("LINESTRING(30 10,-3.2011243453   -101.12124240,30 10,-5.2011243453   -105.12124240)")).Start();
        LineString linestring2D = (LineString) start.jjtGetChild(0);
        assertEquals(4, linestring2D.jjtGetNumChildren());
        WKTTestPoint.checkPoint((Point) linestring2D.jjtGetChild(0), "30", "10");
        WKTTestPoint.checkPoint((Point) linestring2D.jjtGetChild(1), "-3.2011243453", "-101.12124240");
        WKTTestPoint.checkPoint((Point) linestring2D.jjtGetChild(2), "30", "10");
        WKTTestPoint.checkPoint((Point) linestring2D.jjtGetChild(3), "-5.2011243453", "-105.12124240");
    }
}