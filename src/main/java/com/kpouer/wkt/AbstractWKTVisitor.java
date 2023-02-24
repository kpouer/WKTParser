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

/**
 * @since 1.1.0
 * @author Matthieu Casanova
 */
public abstract class AbstractWKTVisitor implements WKTVisitor {
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
        var firstToken = node.jjtGetChild(0);
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
}
