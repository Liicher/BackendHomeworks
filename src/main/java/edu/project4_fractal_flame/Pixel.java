package edu.project4_fractal_flame;

import java.awt.Color;

// Внешний вид пикселя
// Класс Pixel для окрашивания пикселя
// Класс для хранения информации о пикселе
class Pixel {
    private Point point; // Координаты пикселя
    private Color color; // Цвет пикселя

    public Pixel(int x, int y, Color color) {
        this.point = new Point(x, y);
        this.color = color;
    }

    public int getX() {
        return point.getX();
    }

    public int getY() {
        return point.getY();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
