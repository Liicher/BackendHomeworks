package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;

public class CylinderTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        return new Point(Math.sin(point.getY()), point.getY());
    }
}
