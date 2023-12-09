package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;
import static edu.project4_fractal_flame.transformations.TransformationUtils.radius;
import static edu.project4_fractal_flame.transformations.TransformationUtils.theta;

public class HandkerchiefTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double x = point.getX();
        double y = point.getY();
        double r = radius(point);
        double t = theta(point);
        return new Point(r * (Math.sin(t + r)), Math.cos(t - r));
    }
}
