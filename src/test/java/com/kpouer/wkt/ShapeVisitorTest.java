package com.kpouer.wkt;

import com.kpouer.wkt.shape.GeometryCollection;
import com.kpouer.wkt.shape.LineString;
import com.kpouer.wkt.shape.MultiLineString;
import com.kpouer.wkt.shape.MultiPoint;
import com.kpouer.wkt.shape.MultiPolygon;
import com.kpouer.wkt.shape.Point;
import com.kpouer.wkt.shape.Polygon;
import com.kpouer.wkt.shape.*;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeVisitorTest {
    @Test
    void visitLineGeometryCollection() throws ParseException {
        GeometryCollection geometryCollection = WKT.parseShape("GEOMETRYCOLLECTION (POINT (40 10),\n" +
                                                                   "LINESTRING (30 10,50   77),\n" +
                                                                   "POLYGON ((40 40, 20 45, 45 30, 40 40)))");
        List<Shape> shapes = geometryCollection.getShapes();
        assertEquals(3, shapes.size());
        Point point = (Point) shapes.get(0);
        assertEquals(40, point.getX());
        assertEquals(10, point.getY());
        validateLineString((LineString) shapes.get(1));
        validatePolygon((Polygon) shapes.get(2));
    }

    @Test
    void visitLineString() throws ParseException {
        LineString lineString = WKT.parseShape("LINESTRING(30 10,50   77)");
        validateLineString(lineString);
    }

    @Test
    void visitMultiLineString() throws ParseException {
        MultiLineString multiLineString = WKT.parseShape("MULTILINESTRING ((30 10, 50 77),(40 40, 30 30, 40 20, 30 10))");
        List<LineString> lineStrings = multiLineString.getLineStrings();
        assertEquals(2, lineStrings.size());
        validateLineString(lineStrings.get(0));
    }

    @Test
    void visitMultipoint() throws ParseException {
        MultiPoint multiPoint = WKT.parseShape("MULTIPOINT (10 40, 40 30, 20 20, 30 10)");
        List<Point> points = multiPoint.getPoints();
        assertEquals(10, points.get(0).getX());
        assertEquals(40, points.get(0).getY());
        assertEquals(40, points.get(1).getX());
        assertEquals(30, points.get(1).getY());
        assertEquals(20, points.get(2).getX());
        assertEquals(20, points.get(2).getY());
        assertEquals(30, points.get(3).getX());
        assertEquals(10, points.get(3).getY());
    }

    @Test
    void visitMultiPolygon() throws ParseException {
        MultiPolygon multiPolygon = WKT.parseShape("MULTIPOLYGON ( ((40 40, 20 45, 45 30, 40 40)),((15 5, 40 10, 10 20, 5 10, 15 5)) )");
        List<Polygon> polygons = multiPolygon.getPolygons();
        assertEquals(2, polygons.size());
        validatePolygon(polygons.get(0));
    }

    @Test
    void visitPolygon() throws ParseException {
        Polygon polygon = WKT.parseShape("POLYGON((40 40, 20 45, 45 30, 40 40))");
        validatePolygon(polygon);
    }

    private static void validateLineString(LineString lineString) {
        assertEquals(30, lineString.getXpoints()[0]);
        assertEquals(10, lineString.getYpoints()[0]);
        assertEquals(50, lineString.getXpoints()[1]);
        assertEquals(77, lineString.getYpoints()[1]);
    }

    private void validatePolygon(Polygon polygon) {
        assertEquals(40, polygon.getXpoints()[0]);
        assertEquals(40, polygon.getYpoints()[0]);
        assertEquals(20, polygon.getXpoints()[1]);
        assertEquals(45, polygon.getYpoints()[1]);
        assertEquals(45, polygon.getXpoints()[2]);
        assertEquals(30, polygon.getYpoints()[2]);
        assertEquals(40, polygon.getXpoints()[3]);
        assertEquals(40, polygon.getYpoints()[3]);
    }
}