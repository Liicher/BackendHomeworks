package edu.hw2.task2;

public class Square extends Rectangle {

    public Square() {}

    public Square(int side) {
        super(side, side);
    }

    @Override
    Rectangle setSides(int width, int height) {
        if (width == height) {
            return new Square(width);
        }
        return new Rectangle(width, height);
    }
}
