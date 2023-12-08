package edu.project4_fractal_flame;

import edu.project4_fractal_flame.FlameParts.Pixel;

public class FractalFlame {
    private final Pixel[][] pixels;
    private final int fhdX;
    private final int fhdY;

    public FractalFlame(int fhdX, int fhdY) {
        this.fhdX = fhdX;
        this.fhdY = fhdY;
        this.pixels = this.generatePixelsFlame();
    }

    public Pixel[][] generatePixelsFlame() {
        Pixel[][] generatedPixels = new Pixel[fhdX][fhdY];
        for (int i = 0; i < fhdX; i++) {
            for (int j = 0; j < fhdY; j++) {
                generatedPixels[i][j] = new Pixel(0, 0, 0);
            }
        }
        return generatedPixels;
    }

    public void gammaCorrection() {

    }

    public Pixel[][] getPixels() {
        return pixels;
    }
}
