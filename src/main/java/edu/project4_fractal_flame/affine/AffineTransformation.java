package edu.project4_fractal_flame.affine;

import java.util.concurrent.ThreadLocalRandom;

public class AffineTransformation {
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    public AffineTransformation() {
        this.a = ThreadLocalRandom.current().nextDouble(-1, 1);
        this.b = ThreadLocalRandom.current().nextDouble(-1, 1);
        this.c = ThreadLocalRandom.current().nextDouble(-1, 1);
        this.d = ThreadLocalRandom.current().nextDouble(-1, 1);
        this.e = ThreadLocalRandom.current().nextDouble(-1, 1);
        this.f = ThreadLocalRandom.current().nextDouble(-1, 1);
    }

    public static AffineTransformation[] affine(int amount) {
        AffineTransformation[] affineTransformations = new AffineTransformation[amount];
        for (int i = 0; i < amount; i++) {
            affineTransformations[i] = new AffineTransformation();
        }
        return affineTransformations;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public double getE() {
        return e;
    }

    public double getF() {
        return f;
    }
}
