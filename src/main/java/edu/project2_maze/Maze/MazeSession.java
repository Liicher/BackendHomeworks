/*package edu.project2_maze.Maze;

import edu.project2_maze.GUI.UserInterface;

public class MazeSession {
    UserInterface userInterface;
    public Cell[][] cells;
    private static final int HORIZONTAL_CELLS = 101;
    private static final int VERTICAL_CELLS = 51;

    public void run() {
        userInterface = new UserInterface();
        userInterface.runWindow(this);
        MazeSessionWilson mazeSessionWilson = new MazeSessionWilson(this);
        mazeSessionWilson.move();
    }

    *//*private Cell[][] cellsGenerator() {
        cells = new Cell[VERTICAL_CELLS][HORIZONTAL_CELLS];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(j, i, TypeOfCell.WALL);
            }
        }
        return cells;
    }*//*

    public void drawMaze(Cell[][] cells) {
        this.cells = cells;
        userInterface.drawMaze(cells);
    }

    public int getHorizontalCells() {
        return HORIZONTAL_CELLS;
    }

    public int getVerticalCells() {
        return VERTICAL_CELLS;
    }
}*/
