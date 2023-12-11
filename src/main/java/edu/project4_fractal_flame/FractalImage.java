package edu.project4_fractal_flame;

import edu.project4_fractal_flame.flameParts.Pixel;

public class FractalImage {
    private final Pixel[][] pixels;
    private final int width;
    private final int height;

    public FractalImage(Pixel[][] pixels, int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public static FractalImage create(int width, int height) {
        Pixel[][] pixels = generatePixelsFlame(width, height);
        return new FractalImage(pixels, width, height);
    }

    public static Pixel[][] generatePixelsFlame(int width, int height) {
        Pixel[][] generatedPixels = new Pixel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                generatedPixels[i][j] = new Pixel(0, 0, 0, 0);
            }
        }
        return generatedPixels;
    }

    public Pixel getPixel(int x, int y) {
        if (isValid(x, y)) {
            return pixels[x][y];
        }
        return null;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
