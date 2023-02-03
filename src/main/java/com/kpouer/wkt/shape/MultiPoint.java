package com.kpouer.wkt.shape;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MultiPoint {
    private final List<Point2D.Double> points;

    public MultiPoint(List<Point2D.Double> points) {
        this.points = points;
    }

    public MultiPoint() {
        points = new ArrayList<>();
    }

    public List<Point2D.Double> getPoints() {
        return points;
    }
}
