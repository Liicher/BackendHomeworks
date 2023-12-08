package edu.project4_fractal_flame.FlameParts;

// Расположение точки по координатам
// Класс для хранения координат пикселя
public class Point {
    private final int x;
    private final int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point - (" + x + ',' + y + ')';
    }
}
