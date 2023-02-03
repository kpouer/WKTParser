package com.kpouer.wkt;

import com.kpouer.wkt.shape.Shape;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ShapeVisitor extends AbstractWKTVisitor<com.kpouer.wkt.shape.Point> {
    @Override
    public com.kpouer.wkt.shape.Point visit(Point point, Object data) {
        return new com.kpouer.wkt.shape.Point(Double.parseDouble(point.jjtGetFirstToken().image), Double.parseDouble(point.jjtGetLastToken().image));
    }

    @Override
    public com.kpouer.wkt.shape.LineString visit(LineString lineString, Object data) {
        return buildMultiPointShape(lineString, com.kpouer.wkt.shape.LineString::new);
    }

    @Override
    public com.kpouer.wkt.shape.Polygon visit(Polygon polygon, Object data) {
        return buildMultiPointShape(polygon, com.kpouer.wkt.shape.Polygon::new);
    }

    @Override
    public com.kpouer.wkt.shape.MultiLineString visit(MultiLineString multiLineString, Object data) {
        int nb = multiLineString.jjtGetNumChildren();
        List<com.kpouer.wkt.shape.LineString> lineStrings = new ArrayList<>(nb);
        for (int i = 0; i < nb; i++) {
            com.kpouer.wkt.shape.LineString lineString = visit((LineString) multiLineString.jjtGetChild(i), data);
            lineStrings.add(lineString);
        }
        return new com.kpouer.wkt.shape.MultiLineString(lineStrings);
    }

    @Override
    public com.kpouer.wkt.shape.MultiPoint visit(MultiPoint multiPoint, Object data) {
        int nb = multiPoint.jjtGetNumChildren();
        List<Point2D.Double> list = new ArrayList<>(nb);
        for (int i = 0; i < nb; i++) {
            Point2D.Double visit = visit((Point) multiPoint.jjtGetChild(i), data);
            list.add(visit);
        }
        return new com.kpouer.wkt.shape.MultiPoint(list);
    }

    @Override
    public com.kpouer.wkt.shape.MultiPolygon visit(MultiPolygon multiPolygon, Object data) {
        int nb = multiPolygon.jjtGetNumChildren();
        List<com.kpouer.wkt.shape.Polygon> polygons = new ArrayList<>(nb);
        for (int i = 0; i < nb; i++) {
            com.kpouer.wkt.shape.Polygon polygon = visit((Polygon) multiPolygon.jjtGetChild(i), data);
            polygons.add(polygon);
        }
        return new com.kpouer.wkt.shape.MultiPolygon(polygons);
    }

    @Override
    public com.kpouer.wkt.shape.GeometryCollection visit(GeometryCollection geometryCollection, Object data) {
        int nb = geometryCollection.jjtGetNumChildren();
        List<Shape> polygons = new ArrayList<>(nb);
        for (int i = 0; i < nb; i++) {
            Shape visit = (Shape) visit((SimpleNode) geometryCollection.jjtGetChild(i), data);
            polygons.add(visit);
        }
        return new com.kpouer.wkt.shape.GeometryCollection(polygons);
    }

    private static <E> E buildMultiPointShape(SimpleNode node, MultPointShapeBuilder<E> multPointShapeBuilder) {
        int npoints = node.jjtGetNumChildren();
        double[] xpoints = new double[npoints];
        double[] ypoints = new double[npoints];
        for (int i = 0;i<npoints;i++) {
            Point point = (Point) node.jjtGetChild(i);
            xpoints[i] = Double.parseDouble(point.jjtGetFirstToken().image);
            ypoints[i] = Double.parseDouble(point.jjtGetLastToken().image);
        }
        return multPointShapeBuilder.build(xpoints, ypoints);
    }
    @FunctionalInterface
    private interface MultPointShapeBuilder<E> {
        E build(double[] xpoints, double[] ypoints);
    }
}
