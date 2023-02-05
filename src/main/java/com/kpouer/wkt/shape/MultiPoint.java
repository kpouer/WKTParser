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
package com.kpouer.wkt.shape;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.1.0
 * @author Matthieu Casanova
 */
public class MultiPoint extends AbstractShape {
    private final List<Point> points;

    public MultiPoint(List<Point> points) {
        this.points = points;
    }

    public MultiPoint() {
        points = new ArrayList<>();
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    protected Point computeBarycenter() {
        double x = points.get(0).getX();
        double y = points.get(0).getY();
        int size = points.size();
        for (int i = 1; i < size; i++) {
            Point point = points.get(i);
            x += point.getX();
            y += point.getY();
        }
        return new Point(x / size, y / size);
    }

    @Override
    public Rectangle2D.Double getBounds2D() {
        double x1 = points.get(0).getX();
        double y1 = points.get(0).getY();
        double x2 = x1;
        double y2 = y1;

        int size = points.size();
        for (int i = 1; i < size; i++) {
            Point point = points.get(i);
            x1 = Math.min(x1, point.getX());
            y1 = Math.min(y1, point.getY());
            x2 = Math.max(x2, point.getX());
            y2 = Math.max(y2, point.getY());
        }
        return new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1);
    }

    @Override
    public MultiPoint clone() {
        return (MultiPoint) super.clone();
    }
}
