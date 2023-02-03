package com.kpouer.wkt;

public abstract class AbstractWKTVisitor<E> implements WKTVisitor {
    @Override
    public Object visit(SimpleNode node, Object data) {
        if (node instanceof GeometryCollection) {
            return visit((GeometryCollection) node, data);
        } else if (node instanceof Start) {
            return visit((Start) node, data);
        } else if (node instanceof LineString) {
            return visit((LineString) node, data);
        } else if (node instanceof MultiLineString) {
            return visit((MultiLineString) node, data);
        } else if (node instanceof MultiPoint) {
            return visit((MultiPoint) node, data);
        } else if (node instanceof MultiPolygon) {
            return visit((MultiPolygon) node, data);
        } else if (node instanceof Polygon) {
            return visit((Polygon) node, data);
        } else if (node instanceof Point) {
            return visit((Point) node, data);
        }
        throw new RuntimeException("Unexpected token type " + node);
    }

    @Override
    public Object visit(Start node, Object data) {
        Node firstToken = node.jjtGetChild(0);
        if (firstToken instanceof GeometryCollection) {
            return visit((GeometryCollection) firstToken, data);
        } else if (firstToken instanceof Start) {
            return visit((Start) firstToken, data);
        } else if (firstToken instanceof LineString) {
            return visit((LineString) firstToken, data);
        } else if (firstToken instanceof MultiLineString) {
            return visit((MultiLineString) firstToken, data);
        } else if (firstToken instanceof MultiPoint) {
            return visit((MultiPoint) firstToken, data);
        } else if (firstToken instanceof MultiPolygon) {
            return visit((MultiPolygon) firstToken, data);
        } else if (firstToken instanceof Polygon) {
            return visit((Polygon) firstToken, data);
        } else if (firstToken instanceof Point) {
            return visit((Point) firstToken, data);
        }
        throw new RuntimeException("Unexpected token type " + firstToken);
    }

    @Override
    public abstract E visit(Point node, Object data);
}
