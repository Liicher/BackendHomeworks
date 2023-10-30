package edu.project2_maze.Cell;

import java.awt.Color;

public class Cell {
    // Размеры клеток на интерфейсе
    public static final int WIDTH = 4;
    public static final int HEIGHT = 4;

    // Координаты клеток
    private final int x;
    private final int y;
    private TypeOfCell type;

    // Цвет клетки. Использую для вывода
    private Color color;

    public Cell(int x, int y, TypeOfCell type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.color = Color.BLACK;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setType(TypeOfCell type) {
        this.type = type;
    }

    public TypeOfCell getType() {
        return this.type;
    }
}

