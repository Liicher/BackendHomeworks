package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;
import static edu.project4_fractal_flame.transformations.TransformationUtils.radius;
import static edu.project4_fractal_flame.transformations.TransformationUtils.theta;

public class DiamondTransformation implements Transformation {
    @Override
    public Point transform(Point point) {
        double t = theta(point);
        double r = radius(point);
        return new Point(Math.sin(t) * Math.cos(r), Math.cos(t) * Math.sin(r));
    }
}
