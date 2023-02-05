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
 * @author Matthieu Casanova
 * @since 1.1.0
 */
public abstract class AbstractMultiShape<E extends Shape> extends AbstractShape {
    protected final List<E> shapes;

    protected AbstractMultiShape(List<E> shapes) {
        this.shapes = shapes;
    }

    protected AbstractMultiShape() {
        shapes = new ArrayList<>();
    }

    public List<E> getShapes() {
        return shapes;
    }

    @Override
    protected Point computeBarycenter() {
        Point[] barycenters = getBarycenters();
        return computeBarycenterFromPoints(barycenters);
    }

    private Point[] getBarycenters() {
        Point[] barycenters = new Point[shapes.size()];
        for (int i = 0; i < barycenters.length; i++) {
            barycenters[i] = shapes.get(i).getBarycenter();
        }
        return barycenters;
    }


    private static Point computeBarycenterFromPoints(Point[] barycenters) {
        // compute barycenter
        double x = 0;
        double y = 0;
        for (Point barycenter : barycenters) {
            x += barycenter.getX();
            y += barycenter.getY();
        }
        x /= barycenters.length;
        y /= barycenters.length;
        return new Point(x, y);
    }

    @Override
    public Rectangle2D.Double getBounds2D() {
        if (shapes.isEmpty()) {
            return new Rectangle2D.Double();
        }
        Rectangle2D bounds = shapes.get(0).getBounds2D();
        double x1 = bounds.getMinX();
        double y1 = bounds.getMinY();
        double x2 = bounds.getMaxY();
        double y2 = bounds.getMaxY();
        int size = shapes.size();
        for (int i = 1; i < size; i++) {
            Rectangle2D.Double otherBounds = shapes.get(i).getBounds2D();
            x1 = Math.min(x1, otherBounds.getMinX());
            y1 = Math.min(y1, otherBounds.getMinY());
            x2 = Math.max(x2, otherBounds.getMaxX());
            y2 = Math.max(y2, otherBounds.getMaxY());
        }
        return new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1);
    }
}
