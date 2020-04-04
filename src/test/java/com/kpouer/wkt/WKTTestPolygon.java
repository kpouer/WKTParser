package com.kpouer.wkt;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WKTTestPolygon {
    @Test
    void polygon1() throws ParseException {
        Start   start = new WKT(new StringReader("POLYGON((23.544921874999982 35.8015794589061," +
                                                     "40.41992187499998 33.70768890799883," +
                                                     "38.22265624999998 18.793768422956614," +
                                                     "23.544921874999982 17.876063967530726," +
                                                     "18.886718749999982 25.86098269006883," +
                                                     "23.544921874999982 35.8015794589061))")).Start();
        Polygon polygon = (Polygon) start.jjtGetChild(0);

        assertEquals(6, polygon.jjtGetNumChildren());
        WKTTestPoint.checkPoint((Point) polygon.jjtGetChild(0), "23.544921874999982", "35.8015794589061");
        WKTTestPoint.checkPoint((Point) polygon.jjtGetChild(1), "40.41992187499998", "33.70768890799883");
        WKTTestPoint.checkPoint((Point) polygon.jjtGetChild(2), "38.22265624999998", "18.793768422956614");
        WKTTestPoint.checkPoint((Point) polygon.jjtGetChild(3), "23.544921874999982", "17.876063967530726");
        WKTTestPoint.checkPoint((Point) polygon.jjtGetChild(4), "18.886718749999982", "25.86098269006883");
        WKTTestPoint.checkPoint((Point) polygon.jjtGetChild(5), "23.544921874999982", "35.8015794589061");
    }
}