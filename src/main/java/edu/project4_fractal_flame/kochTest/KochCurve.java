package edu.project4_fractal_flame.kochTest;

import javax.swing.*;
import java.awt.*;

public class KochCurve extends JFrame {
    private int level = 0;

    public KochCurve() {
        setTitle("Koch Curve");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 900);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawKochCurve(g, level, 100, 450, 900, 450);
    }

    // метод для отрисовки кривой кохи
    private void drawKochCurve(Graphics g, int level, int x1, int y1, int x2, int y2) {
        if (level == 0) {
            g.drawLine(x1, y1, x2, y2);
        } else {
            int deltaX = x2 - x1;
            int deltaY = y2 - y1;

            // вычисляем координаты вершин треугольника
            int x3 = x1 + deltaX / 3;
            int y3 = y1 + deltaY / 3;
            int x4 = (int) (0.5 * (x1 + x2) + Math.sqrt(3) * (y2 - y1) / 6);
            int y4 = (int) (0.5 * (y1 + y2) + Math.sqrt(3) * (x1 - x2) / 6);
            int x5 = x1 + 2 * deltaX / 3;
            int y5 = y1 + 2 * deltaY / 3;

            // рекурсивно рисуем кривую Коха для каждой из четырех отрезков
            drawKochCurve(g, level - 1, x1, y1, x3, y3);
            drawKochCurve(g, level - 1, x3, y3, x4, y4);
            drawKochCurve(g, level - 1, x4, y4, x5, y5);
            drawKochCurve(g, level - 1, x5, y5, x2, y2);
        }
    }

    public static void main(String[] args) {
        KochCurve kochCurve = new KochCurve();
        kochCurve.level = Integer.parseInt(JOptionPane.showInputDialog("Enter Level: "));
        kochCurve.repaint();
    }
}
