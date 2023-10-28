package edu.project2_maze.Maze;

import edu.project2_maze.GUI.UserInterface;

public class MazeSession {
    UserInterface userInterface;
    private Cell[][] cells = cellsGenerator();
    private static final int HORIZONTAL_CELLS = 101;
    private static final int VERTICAL_CELLS = 101;

    public void run() {
        MazeSessionWilson mazeSessionWilson = new MazeSessionWilson(this, cells);
        userInterface = new UserInterface();
        userInterface.runWindow(this);
        mazeSessionWilson.move();
    }

    private Cell[][] cellsGenerator() {
        cells = new Cell[VERTICAL_CELLS][HORIZONTAL_CELLS];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(j, i, TypeOfCell.WALL);
            }
        }
        return cells;
    }

    public void drawMaze(Cell[][] cells) {
        userInterface.drawMaze(cells);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getHorizontalCells() {
        return HORIZONTAL_CELLS;
    }

    public int getVerticalCells() {
        return VERTICAL_CELLS;
    }
}
