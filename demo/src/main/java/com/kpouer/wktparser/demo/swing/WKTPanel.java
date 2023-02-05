package com.kpouer.wktparser.demo.swing;

import com.kpouer.wkt.shape.Point;
import com.kpouer.wkt.shape.Polygon;
import com.kpouer.wkt.shape.Shape;
import com.kpouer.wkt.shape.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.List;

public class WKTPanel extends Component {
    private Shape shape;

    public void setShape(Shape shape) {
        if (shape == null) {
            this.shape = null;
        }
        Shape newShape = shape.clone();
        recomputeGeometry(newShape, shape.getBounds2D());
        this.shape = newShape;
    }

    private void recomputeGeometry(Shape shape, Rectangle2D.Double bounds) {
        Shape newShape = shape.clone();
        if (newShape instanceof AbstractMultiShape) {
            recomputeAbstractMultiShape((AbstractMultiShape<? extends Shape>) newShape, bounds);
        } else if (newShape instanceof AbstractMultiPointShape) {
            recomputeAbstractMultiPointShape((AbstractMultiPointShape) newShape, bounds);
        } else if (newShape instanceof com.kpouer.wkt.shape.MultiPoint) {
            recomputeMultiPoint((com.kpouer.wkt.shape.MultiPoint) newShape, bounds);
        }
    }

    private void recomputeAbstractMultiShape(AbstractMultiShape<? extends Shape> newShape, Rectangle2D.Double bounds) {
        List<? extends Shape> shapes = newShape.getShapes();
        shapes.forEach(shp -> recomputeGeometry(shp, bounds));
    }

    private void recomputeAbstractMultiPointShape(AbstractMultiPointShape abstractMultiPointShape, Rectangle2D.Double bounds) {
        for (int i = 0; i < abstractMultiPointShape.getNpoints(); i++) {
            abstractMultiPointShape.getXpoints()[i] = (int) ((abstractMultiPointShape.getXpoints()[i] - bounds.x) / bounds.width * getWidth());
            abstractMultiPointShape.getYpoints()[i] = (int) ((abstractMultiPointShape.getYpoints()[i] - bounds.y) / bounds.height * getHeight());
        }
    }

    private void recomputeMultiPoint(com.kpouer.wkt.shape.MultiPoint multiPoint, Rectangle2D.Double bounds) {
        for (com.kpouer.wkt.shape.Point point : multiPoint.getPoints()) {
            point.setLocation((point.getX() - bounds.x) / bounds.width * getWidth(), (point.getY() - bounds.y) / bounds.height * getHeight());
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (shape != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            paintShape(g2d, shape);
        }
    }

    private void paintShape(Graphics2D g2d, Shape shape) {
        if (shape instanceof AbstractMultiShape) {
            AbstractMultiShape<? extends Shape> abstractMultiShape = (AbstractMultiShape<? extends Shape>) shape;
            abstractMultiShape.getShapes().forEach(shp -> paintShape(g2d, shp));
        } else if (shape instanceof Polygon) {
            g2d.setColor(Color.BLACK);
            Polygon polygon = (Polygon) shape;
            g2d.drawPolygon(Arrays.stream(polygon.getXpoints()).mapToInt(i -> (int) i).toArray(),
                            Arrays.stream(polygon.getYpoints()).mapToInt(i -> (int) i).toArray(),
                            polygon.getNpoints());
            g2d.setColor(Color.RED);
            Point barycenter = polygon.getBarycenter();
            g2d.drawRect((int) barycenter.getX(), (int) barycenter.getY(), 1, 1);
        } else if (shape instanceof com.kpouer.wkt.shape.MultiPoint) {
//            paintMultiPoint(g2d, (com.kpouer.wkt.shape.MultiPoint) shape);
        } else if (shape instanceof com.kpouer.wkt.shape.Point) {
            Point point = (com.kpouer.wkt.shape.Point) shape;
            g2d.setColor(Color.BLACK);
            g2d.drawRect((int) point.getX(), (int) point.getY(), 1, 1);
        } else if (shape instanceof com.kpouer.wkt.shape.LineString) {
            LineString lineString = (LineString) shape;
            g2d.setColor(Color.BLACK);
            g2d.drawPolyline(Arrays.stream(lineString.getXpoints()).mapToInt(i -> (int) i).toArray(),
                             Arrays.stream(lineString.getYpoints()).mapToInt(i -> (int) i).toArray(),
                             lineString.getNpoints());
            g2d.setColor(Color.RED);
            Point barycenter = lineString.getBarycenter();
            g2d.drawRect((int) barycenter.getX(), (int) barycenter.getY(), 1, 1);
        }
    }
}
