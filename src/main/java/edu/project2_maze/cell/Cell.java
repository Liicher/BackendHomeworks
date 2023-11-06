package edu.project2_maze.cell;

import edu.project2_maze.maze.MazeSession;
import java.awt.Color;
import java.util.List;
import java.util.Objects;

public class Cell {
    // Размеры клеток на интерфейсе
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

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

    public static void remarkSolveWay(List<Cell> solveWayList, Cell[][] cells) {
        for (Cell cell : solveWayList) {
            cell.setType(TypeOfCell.SOLVE_WAY);
        }

        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                if (value.getType() == (TypeOfCell.WAY)) {
                    value.setType(TypeOfCell.PASSAGE);
                }
            }
        }
    }

    public static void remarkCellsDFS() {
        for (Cell[] cell : MazeSession.getCells()) {
            for (Cell value : cell) {
                if (value.getType() == TypeOfCell.WAY || value.getType() == TypeOfCell.CURRENT) {
                    value.setType(TypeOfCell.PASSAGE);
                }
            }
        }
    }

    public static void remarkCellsWilson(List<Cell> wayList) {
        for (Cell cell : wayList) {
            cell.setType(TypeOfCell.PASSAGE);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y && type == cell.type && Objects.equals(color, cell.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, type, color);
    }
}

