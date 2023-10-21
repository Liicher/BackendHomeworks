package edu.hw2.task2;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    Rectangle setWidth(int width) {
        return new Rectangle(width, this.height);
    }

    Rectangle setHeight(int height) {
        return new Rectangle(this.width, height);
    }

    Rectangle setSides(int width, int height) {
        return new Rectangle(width, height);
    }

    double area() {
        return width * height;
    }
}
