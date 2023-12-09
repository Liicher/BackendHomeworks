package edu.project4_fractal_flame.flameParts;

import edu.project4_fractal_flame.affine.AffineTransformation;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class Pixel {
    private static final int BOUND = 255;

    private int r;
    private int g;
    private int b;
    private int hitCount;
    private double normal = 0;  // Коррекция

    public Pixel(int r, int g, int b, int hitCount) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCount = hitCount;
    }

    public static Color generateStarterColor() {
        int r = ThreadLocalRandom.current().nextInt(0, BOUND);
        int g = ThreadLocalRandom.current().nextInt(0, BOUND);
        int b = ThreadLocalRandom.current().nextInt(0, BOUND);
        return new Color(r, g, b);
    }

    public void setColor(AffineTransformation affine) {
        if (hitCount == 0) {
            r = affine.getColor().getRed();
            g = affine.getColor().getGreen();
            b = affine.getColor().getBlue();
        } else {
            r = (r + affine.getColor().getRed()) / 2;
            g = (g + affine.getColor().getGreen()) / 2;
            b = (b + affine.getColor().getBlue()) / 2;
        }
    }

    public void incrementHitCount() {
        this.hitCount++;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public double getNormal() {
        return normal;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public int getR() {
        return r;
    }
}
