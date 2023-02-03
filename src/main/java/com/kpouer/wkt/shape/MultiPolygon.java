package com.kpouer.wkt.shape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MultiPolygon {
    private final List<Polygon> polygons;

    public MultiPolygon(Collection<Polygon> polygons) {
        this.polygons = new ArrayList<>(polygons);
    }

    public MultiPolygon() {
        polygons = new ArrayList<>();
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }
}
