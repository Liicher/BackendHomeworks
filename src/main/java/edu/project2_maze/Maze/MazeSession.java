package edu.project2_maze.Maze;

import edu.project2_maze.Cell.Cell;
import edu.project2_maze.Cell.TypeOfCell;
import edu.project2_maze.GUI.UserInterface;
import edu.project2_maze.MazeSolver.MazeSolverBreadthFirstSearch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeSession {
    private final static Logger LOGGER = LogManager.getLogger();
    private UserInterface userInterface;
    private Cell[][] cells = cellsGeneratorWilson();
    private static final int MILLISECONDS_PER_FRAME = 1;
    private static final int HORIZONTAL_CELLS = 21;
    private static final int VERTICAL_CELLS = 21;
    private static final int PAUSE = 1000;

    public void run() {
        MazeSessionDepthFirstSearch mazeSessionDepthFirstSearch = new MazeSessionDepthFirstSearch(this, cells);
        MazeSessionWilson mazeSessionWilson = new MazeSessionWilson(this, cells);
        userInterface = new UserInterface();
        userInterface.runWindow(this);
        drawMaze(cells);
        cells = mazeSessionWilson.move();

        try {
            Thread.sleep(PAUSE);
        } catch (InterruptedException e) {
            LOGGER.info(e);
        }

        MazeSolverBreadthFirstSearch mazeSolverBreadthFirstSearch = new MazeSolverBreadthFirstSearch(this, cells);
        mazeSolverBreadthFirstSearch.solve();
    }

    private Cell[][] cellsGeneratorWilson() {
        cells = new Cell[VERTICAL_CELLS][HORIZONTAL_CELLS];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(j, i, TypeOfCell.WALL);
            }
        }
        return cells;
    }

    private Cell[][] cellsGeneratorDepthFirstSearch() {
        cells = new Cell[VERTICAL_CELLS][HORIZONTAL_CELLS];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    cells[i][j] = new Cell(j, i, TypeOfCell.PASSAGE);
                } else {
                    cells[i][j] = new Cell(j, i, TypeOfCell.WALL);
                }
            }
        }
        return cells;
    }

    public void drawMaze(Cell[][] cells) {
        try {
            Thread.sleep(MILLISECONDS_PER_FRAME);
        } catch (InterruptedException e) {
            LOGGER.info(e);
        }
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
