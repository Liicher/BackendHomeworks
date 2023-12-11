package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;
import static edu.project4_fractal_flame.transformations.TransformationUtils.radius;

public class HorseshoeTransformation implements Transformation {

    @Override
    public Point transform(Point point) {
        double x = point.getX();
        double y = point.getY();
        double r = radius(point);
        return new Point(((x - y) * (x + y)) / r, 2 * x * y);
    }
}
