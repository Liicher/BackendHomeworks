package edu.project4_fractal_flame.flameParts;

public class Rect {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Rect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /*public boolean contains(Point p) {
        return p.getX() < width && p.getY() < height;
    }*/

    public boolean contains(Point p) {
        return p.getX() > x && p.getX() < width + x && p.getY() > y && p.getY() < height + y;
    }
}
