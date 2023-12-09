package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;
import static edu.project4_fractal_flame.transformations.TransformationUtils.radius;
import static edu.project4_fractal_flame.transformations.TransformationUtils.theta;

public class ExTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double x = point.getX();
        double y = point.getY();
        double r = radius(point);
        double t = theta(point);
        double p0 = Math.sin(t + r);
        double p1 = Math.cos(t - r);
        return new Point(r * (Math.pow(p0, 3) + Math.pow(p1, 3)), r * (Math.pow(p0, 3) - Math.pow(p1, 3)));
    }
}
