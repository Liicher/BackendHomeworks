package edu.project4_fractal_flame;

import edu.project4_fractal_flame.FlameParts.Pixel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("MagicNumber")
public class GraphicUserInterface {
    private static final int FHD_X = 1920;
    private static final int FHD_Y = 1080;
    private static final int MILLISECONDS_PER_FRAME = 1;
    private static final JFrame FRAME = new JFrame("Fractal Flame!");

    public void runFractalFlameWindow() {
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setSize(FHD_X, FHD_Y);
        FRAME.setExtendedState(JFrame.MAXIMIZED_BOTH);
        FlamePanel flamePanel = new FlamePanel();
        FRAME.getContentPane().add(BorderLayout.CENTER, flamePanel);
        flamePanel.setBackground(Color.BLACK);
        FRAME.setLocationByPlatform(true);
        FRAME.setResizable(true);
        FRAME.setVisible(true);
    }

    static class FlamePanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Создание массива пикселей
            Pixel[][] pixels = new Pixel[1920][1080];

            // Просто стартовый градиент
            /*for (int i = 0; i < pixels.length; i++) {
                for (int j = 0; j < pixels[i].length; j++) {
                    pixels[i][j] = new Pixel(i, j, new Color((i + j) / 15, (i + j) / 50, (i + j) / 95));
                }
            }*/

            // Отрисовка пикселей на экране
            /*for (int i = 0; i < pisxels.length; i++) {
                for (int j = 0; j < pixels[i].length; j++) {
                    g.setColor(pixels[i][j].getColor());
                    g.fillRect(pixels[i][j].getX(), pixels[i][j].getY(), 1, 1);
                }
            }*/
        }
    }
}


