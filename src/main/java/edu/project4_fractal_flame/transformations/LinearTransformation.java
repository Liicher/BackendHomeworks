package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;

public class LinearTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        return new Point(point.getX(), point.getY());
    }
}
