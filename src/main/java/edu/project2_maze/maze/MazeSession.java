package edu.project2_maze.maze;

import edu.project2_maze.cell.Cell;
import edu.project2_maze.gui.UserInterface;
import edu.project2_maze.interfaces.MazeGenerator;
import edu.project2_maze.mazeSolver.MazeSolverRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeSession {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final MazeStarterCellsGenerators INIT = new MazeStarterCellsGenerators();
    private static final UserInterface UI = new UserInterface();
    private static final int MILLISECONDS_PER_FRAME = 0;
    private static final int HORIZONTAL_CELLS = 41;
    private static final int VERTICAL_CELLS = 21;
    private static final int PAUSE = 1000;

    private Cell[][] cells;
    private final MazeGenerator maze;

    public MazeSession() {
        this.cells = INIT.cellsGeneratorDepthFirstSearch(this);
        this.maze = new MazeSessionDepthFirstSearch(this);
    }

    public void run() {
        UI.runWindow(this);
        drawMaze(cells);
        cells = maze.move();

        try {
            Thread.sleep(PAUSE);
        } catch (InterruptedException e) {
            LOGGER.info(e);
        }

        MazeSolverRandom mazeSolverRandom = new MazeSolverRandom(this);
        mazeSolverRandom.solve();
    }

    public void drawMaze(Cell[][] cells) {
        try {
            Thread.sleep(MILLISECONDS_PER_FRAME);
        } catch (InterruptedException e) {
            LOGGER.info(e);
        }
        UI.drawMaze(cells);
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
