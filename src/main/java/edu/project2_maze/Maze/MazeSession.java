package edu.project2_maze.Maze;

import edu.project2_maze.GUI.UserInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeSession {
    private final static Logger LOGGER = LogManager.getLogger();
    UserInterface userInterface;
    private Cell[][] cells = cellsGeneratorDepthFirstSearch();
    private static final int MILLISECONDS_PER_FRAME = 1;
    private static final int HORIZONTAL_CELLS = 501;
    private static final int VERTICAL_CELLS = 301;

    public void run() {
        MazeSessionWilson mazeSessionWilson = new MazeSessionWilson(this, cells);
        MazeSessionDepthFirstSearch mazeSessionDepthFirstSearch = new MazeSessionDepthFirstSearch(this, cells);
        userInterface = new UserInterface();
        userInterface.runWindow(this);
        drawMaze(cells);
        mazeSessionDepthFirstSearch.move();
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
                if ((i % 2 != 0 && j % 2 != 0) /*&& (i < VERTICAL_CELLS - 1 && j < HORIZONTAL_CELLS - 1)*/) {
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
