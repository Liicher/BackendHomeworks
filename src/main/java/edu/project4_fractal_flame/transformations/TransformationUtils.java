package edu.project4_fractal_flame.transformations;

import edu.project4_fractal_flame.flameParts.Point;
import java.util.ArrayList;
import java.util.List;

public class TransformationUtils {
    public static final double PI = 3.141592;

    private TransformationUtils() {
    }

    // Просто для теста
    public static List<Transformation> getAllTransformations() {
        List<Transformation> transformations = new ArrayList<>();
        transformations.add(new CylinderTransformation());
        transformations.add(new DiamondTransformation());
        transformations.add(new DiskTransformation());
        transformations.add(new ExTransformation());
        transformations.add(new HandkerchiefTransformation());
        transformations.add(new HeartTransformation());
        transformations.add(new HorseshoeTransformation());
        transformations.add(new HyperbolicTransformation());
        transformations.add(new LinearTransformation());
        transformations.add(new PolarTransformation());
        transformations.add(new SinTransformation());
        transformations.add(new SphericalTransformation());
        transformations.add(new SpiralTransformation());
        transformations.add(new SwirlTransformation());

        return transformations;
    }

    public static double radius(Point p) {
        double x = p.getX();
        double y = p.getY();
        return Math.sqrt(x * x + y * y);
    }

    public static double theta(Point p) {
        double x = p.getX();
        double y = p.getY();
        return Math.atan(x / y);
    }

    public static double phi(Point p) {
        double x = p.getX();
        double y = p.getY();
        return Math.atan(y / x);
    }
}
