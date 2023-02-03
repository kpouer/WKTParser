package com.kpouer.wkt.shape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MultiLineString {
    private final List<LineString> lineStrings;

    public MultiLineString(Collection<LineString> lineStrings) {
        this.lineStrings = new ArrayList<>(lineStrings);
    }

    public List<LineString> getLineStrings() {
        return lineStrings;
    }
}
