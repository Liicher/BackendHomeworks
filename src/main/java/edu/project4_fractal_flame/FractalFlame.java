package edu.project4_fractal_flame;

import edu.project4_fractal_flame.flameParts.Pixel;

public class FractalFlame {
    private final Pixel[][] pixels;
    private final int width;
    private final int height;

    public FractalFlame(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = this.generatePixelsFlame();
    }

    public Pixel[][] generatePixelsFlame() {
        Pixel[][] generatedPixels = new Pixel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                generatedPixels[i][j] = new Pixel(0, 0, 0, 0);
            }
        }
        return generatedPixels;
    }

    public void gammaCorrection() {

    }

    public Pixel getPixel(int x, int y) {
        return pixels[x][y];
    }

    public Pixel[][] getPixels() {
        return pixels;
    }
}
