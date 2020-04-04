package com.kpouer.wkt;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WKTTestPoint {
    @Test
    void point1() throws ParseException {
        Start   start = new WKT(new StringReader("POINT(30 10)")).Start();
        checkPoint((Point) start.jjtGetChild(0), "30", "10");
    }

    @Test
    void point2() throws ParseException {
        Start   start = new WKT(new StringReader("point(3.2011243453   101.12124240)")).Start();
        checkPoint((Point) start.jjtGetChild(0), "3.2011243453", "101.12124240");
    }

    @Test
    void point3() throws ParseException {
        Start   start = new WKT(new StringReader("point   (-3.2011243453   101.12124240)")).Start();
        checkPoint((Point) start.jjtGetChild(0), "-3.2011243453", "101.12124240");
    }

    @Test
    void point4() throws ParseException {
        Start   start = new WKT(new StringReader("POINT   (3.2011243453   -101.12124240)")).Start();
        checkPoint((Point) start.jjtGetChild(0), "3.2011243453", "-101.12124240");
    }

    @Test
    void point5() throws ParseException {
        Start   start = new WKT(new StringReader("POINT   (-3.2011243453   -101.12124240)")).Start();
        checkPoint((Point) start.jjtGetChild(0), "-3.2011243453", "-101.12124240");
    }

    public static void checkPoint(Point point, String x, String y) {
        assertEquals(x, point.jjtGetFirstToken().image);
        assertEquals(y, point.jjtGetLastToken().image);
    }
}