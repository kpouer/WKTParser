/*
The MIT License (MIT)
Copyright (c) 2023 Matthieu Casanova

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.kpouer.wkt;

import com.kpouer.wkt.shape.Shape;

import java.util.ArrayList;

/**
 * @since 1.1.0
 * @author Matthieu Casanova
 */
public class ShapeVisitor extends AbstractWKTVisitor {
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
        var lineStrings = new ArrayList<com.kpouer.wkt.shape.LineString>(nb);
        for (int i = 0; i < nb; i++) {
            var lineString = visit((LineString) multiLineString.jjtGetChild(i), data);
            lineStrings.add(lineString);
        }
        return new com.kpouer.wkt.shape.MultiLineString(lineStrings);
    }

    @Override
    public com.kpouer.wkt.shape.MultiPoint visit(MultiPoint multiPoint, Object data) {
        int nb = multiPoint.jjtGetNumChildren();
        var list = new ArrayList<com.kpouer.wkt.shape.Point>(nb);
        for (int i = 0; i < nb; i++) {
            var visit = visit((Point) multiPoint.jjtGetChild(i), data);
            list.add(visit);
        }
        return new com.kpouer.wkt.shape.MultiPoint(list);
    }

    @Override
    public com.kpouer.wkt.shape.MultiPolygon visit(MultiPolygon multiPolygon, Object data) {
        int nb = multiPolygon.jjtGetNumChildren();
        var polygons = new ArrayList<com.kpouer.wkt.shape.Polygon>(nb);
        for (int i = 0; i < nb; i++) {
            var polygon = visit((Polygon) multiPolygon.jjtGetChild(i), data);
            polygons.add(polygon);
        }
        return new com.kpouer.wkt.shape.MultiPolygon(polygons);
    }

    @Override
    public com.kpouer.wkt.shape.GeometryCollection visit(GeometryCollection geometryCollection, Object data) {
        int nb = geometryCollection.jjtGetNumChildren();
        var polygons = new ArrayList<Shape>(nb);
        for (int i = 0; i < nb; i++) {
            var visit = (Shape) visit((SimpleNode) geometryCollection.jjtGetChild(i), data);
            polygons.add(visit);
        }
        return new com.kpouer.wkt.shape.GeometryCollection(polygons);
    }

    private static <E> E buildMultiPointShape(SimpleNode node, MultPointShapeBuilder<E> multPointShapeBuilder) {
        var npoints = node.jjtGetNumChildren();
        var xpoints = new double[npoints];
        var ypoints = new double[npoints];
        for (var i = 0;i<npoints;i++) {
            var point = (Point) node.jjtGetChild(i);
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
