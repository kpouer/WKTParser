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

/**
 * @author Matthieu Casanova
 * @since 1.1.0
 */
public abstract class AbstractMultiPointShape extends AbstractShape {
    private static final int MINSIZE = 4;

    private double[] xpoints;
    private double[] ypoints;
    private int npoints;
    private boolean closed;

    protected AbstractMultiPointShape(double[] xpoints, double[] ypoints, boolean closed) {
        this.xpoints = xpoints;
        this.ypoints = ypoints;
        this.closed = closed;
        npoints = xpoints.length;
    }

    protected AbstractMultiPointShape() {
        xpoints = new double[MINSIZE];
        ypoints = new double[MINSIZE];
    }

    public double[] getXpoints() {
        return xpoints;
    }

    public double[] getYpoints() {
        return ypoints;
    }

    public int getNpoints() {
        return npoints;
    }

    @Override
    protected Point computeBarycenter() {
        if (npoints == 0) {
            return new Point();
        }
        if (closed && npoints == 1) {
            return new Point();
        }
        double x = 0;
        double y = 0;
        for (int i = closed ? 1 : 0; i < xpoints.length; i++) {
            x += xpoints[i];
            y += ypoints[i];
        }
        var nbPoints = closed ? npoints - 1 : npoints;
        return new Point(x / nbPoints, y / nbPoints);
    }

    @Override
    public Rectangle2D.Double getBounds2D() {
        double x1 = xpoints[0];
        double y1 = ypoints[0];
        double x2 = xpoints[0];
        double y2 = ypoints[0];

        for (int i = 1; i < npoints; i++) {
            double x = xpoints[i];
            double y = ypoints[i];
            x1 = Math.min(x1, x);
            y1 = Math.min(y1, y);
            x2 = Math.max(x2, x);
            y2 = Math.max(y2, y);
        }
        return new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1);
    }
}
