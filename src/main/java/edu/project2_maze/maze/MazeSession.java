package edu.project2_maze.maze;

import edu.project2_maze.cell.Cell;
import edu.project2_maze.gui.UserInterface;
import edu.project2_maze.interfaces.MazeGenerator;
import edu.project2_maze.mazeSolver.MazeSolverBreadthFirstSearch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeSession {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final MazeStarterCellsGenerators GENERATE = new MazeStarterCellsGenerators();
    private static final UserInterface GUI = new UserInterface();
    private static final int MILLISECONDS_PER_FRAME = 20;
    private static final int HORIZONTAL_CELLS = 41;
    private static final int VERTICAL_CELLS = 21;
    private static final int PAUSE = 1000;

    private Cell[][] cells;
    private final MazeGenerator maze;

    public MazeSession() {
        this.cells = GENERATE.cellsGeneratorDepthFirstSearch(this);
        this.maze = new MazeSessionDepthFirstSearch(this, cells);
    }

    public void run() {
        GUI.runWindow(this);
        drawMaze(cells);
        cells = maze.move();

        try {
            Thread.sleep(PAUSE);
        } catch (InterruptedException e) {
            LOGGER.info(e);
        }

        MazeSolverBreadthFirstSearch mazeSolverBreadthFirstSearch = new MazeSolverBreadthFirstSearch(this, cells);
        mazeSolverBreadthFirstSearch.solve();
    }

    public void drawMaze(Cell[][] cells) {
        try {
            Thread.sleep(MILLISECONDS_PER_FRAME);
        } catch (InterruptedException e) {
            LOGGER.info(e);
        }
        GUI.drawMaze(cells);
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
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
