package com.kpouer.wkt;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WKTTestMultiLineString {
    @Test
    void multiLineString() throws ParseException {
        Start   start = new WKT(new StringReader("MULTILINESTRING ((10 10, 20 20, 10 40),(40 40, 30 30, 40 20, 30 10))")).Start();
        MultiLineString multiLineString = (MultiLineString) start.jjtGetChild(0);
        assertEquals(2, multiLineString.jjtGetNumChildren());
    }
}