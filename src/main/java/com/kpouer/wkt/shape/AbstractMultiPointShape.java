package com.kpouer.wkt.shape;

public abstract class AbstractMultiPointShape implements Shape {
    private static final int MINSIZE = 4;

    private double[] xpoints;
    private double[] ypoints;
    private int npoints;

    protected AbstractMultiPointShape(double[] xpoints, double[] ypoints) {
        this.xpoints = xpoints;
        this.ypoints = ypoints;
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
}
