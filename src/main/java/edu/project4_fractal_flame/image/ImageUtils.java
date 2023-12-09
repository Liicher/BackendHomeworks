package edu.project4_fractal_flame.image;

import edu.project4_fractal_flame.FractalImage;
import edu.project4_fractal_flame.flameParts.Pixel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void save(FractalImage image, String path, ImageFormat format) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Pixel pixel = image.getPixel(i, j);
                Color color = new Color(pixel.getR(), pixel.getG(), pixel.getB());
                bufferedImage.setRGB(i, j, color.getRGB());
            }
        }

        try {
            ImageIO.write(bufferedImage, format.name(), new File(path));
        } catch (IOException e) {
            LOGGER.info(e);
        }
    }
}
