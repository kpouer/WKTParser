package com.kpouer.wkt.shape;

import java.util.ArrayList;
import java.util.List;

public class GeometryCollection {
    private final List<Shape> shapes;

    public GeometryCollection(List<Shape> shapes) {
        this.shapes = new ArrayList<>(shapes);
    }

    public List<Shape> getShapes() {
        return shapes;
    }
}
