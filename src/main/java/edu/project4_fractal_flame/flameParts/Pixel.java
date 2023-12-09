package edu.project4_fractal_flame.flameParts;

import java.util.concurrent.ThreadLocalRandom;

// Внешний вид пикселя
// Класс Pixel для окрашивания пикселя
// Класс для хранения информации о пикселе
public class Pixel {
    private boolean isRevers = true;
    private int r;
    private int g;
    private int b;
    private int hitCount;

    public Pixel(int r, int g, int b, int hitCount) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCount = hitCount;
    }

    public void setColor() {
        this.r = 147;
        this.g = 112;
        this.b = 216;
    }

    public void incrementHitCount() {
        this.hitCount++;
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
