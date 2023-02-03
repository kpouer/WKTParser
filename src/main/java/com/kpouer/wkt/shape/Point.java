package com.kpouer.wkt.shape;

import java.awt.geom.Point2D;

public class Point extends Point2D.Double implements Shape {
    public Point() {
    }

    public Point(double x, double y) {
        super(x, y);
    }
}
