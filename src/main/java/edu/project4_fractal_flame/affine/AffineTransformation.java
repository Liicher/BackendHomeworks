package edu.project4_fractal_flame.affine;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import static edu.project4_fractal_flame.flameParts.Pixel.generateStarterColor;

public class AffineTransformation {
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;

    private final Color color;

    public AffineTransformation() {
        this.a = ThreadLocalRandom.current().nextDouble(-1, 1);
        this.b = ThreadLocalRandom.current().nextDouble(-1, 1);
        this.c = ThreadLocalRandom.current().nextDouble(-1, 1);
        this.d = ThreadLocalRandom.current().nextDouble(-1, 1);
        this.e = ThreadLocalRandom.current().nextDouble(-1, 1);
        this.f = ThreadLocalRandom.current().nextDouble(-1, 1);

        this.color = generateStarterColor();
    }

    public static AffineTransformation[] affine(int amount) {
        AffineTransformation[] affineTransformations = new AffineTransformation[amount];

        ADDER:
        for (int i = 0; i < amount; i++) {

            while (true) {
                AffineTransformation affine = new AffineTransformation();
                if (affine.checkAffine()) {
                    affineTransformations[i] = affine;
                    continue ADDER;
                }
                affine.remakeCoefficients();
            }
        }
        return affineTransformations;
    }

    private void remakeCoefficients() {
        a = ThreadLocalRandom.current().nextDouble(-1, 1);
        b = ThreadLocalRandom.current().nextDouble(-1, 1);
        c = ThreadLocalRandom.current().nextDouble(-1, 1);
        d = ThreadLocalRandom.current().nextDouble(-1, 1);
        e = ThreadLocalRandom.current().nextDouble(-1, 1);
        f = ThreadLocalRandom.current().nextDouble(-1, 1);
    }

    private boolean checkAffine() {
        if ((a * a + d * d) >= 1) {
            return false;
        }
        if ((b * b + e * e) >= 1) {
            return false;
        }
        if ((a * a + d * d + b * b + e * e) >= 1 + Math.pow((a * e - b * d), 2)) {
            return false;
        }
        return true;
    }

    public Color getColor() {
        return color;
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
