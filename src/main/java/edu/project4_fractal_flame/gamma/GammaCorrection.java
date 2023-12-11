package edu.project4_fractal_flame.gamma;

import edu.project4_fractal_flame.FractalImage;
import edu.project4_fractal_flame.flameParts.Pixel;

@SuppressWarnings("LineLength")
public class GammaCorrection implements ImageProcessor {
    private static final double GAMMA = 1.7;

    @Override
    public void process(FractalImage image) {
        double max = 0;
        Pixel[][] pixels = image.getPixels();
        for (int row = 0; row < image.getWidth(); row++) {
            for (int col = 0; col < image.getHeight(); col++) {
                if (pixels[row][col].getHitCount() != 0) {
                    double n = Math.log10(pixels[row][col].getHitCount());
                    pixels[row][col].setNormal(n);
                    if (pixels[row][col].getNormal() > max) {
                        max = pixels[row][col].getNormal();
                    }
                }
            }
        }

        for (int row = 0; row < image.getWidth(); row++) {
            for (int col = 0; col < image.getHeight(); col++) {
                pixels[row][col].setNormal(pixels[row][col].getNormal() / max);
                pixels[row][col].setR((int) (pixels[row][col].getR() * Math.pow(pixels[row][col].getNormal(), 1 / GAMMA)));
                pixels[row][col].setG((int) (pixels[row][col].getG() * Math.pow(pixels[row][col].getNormal(), 1 / GAMMA)));
                pixels[row][col].setB((int) (pixels[row][col].getB() * Math.pow(pixels[row][col].getNormal(), 1 / GAMMA)));
            }
        }
    }
}
