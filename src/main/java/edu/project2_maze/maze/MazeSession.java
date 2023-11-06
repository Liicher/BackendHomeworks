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
    private static final int HORIZONTAL_CELLS = 51;
    private static final int VERTICAL_CELLS = 21;
    private static final int PAUSE = 1000;

    private Cell[][] cells;
    private final MazeGenerator maze;

    public MazeSession() {
        cells = INIT.cellsGeneratorWilson();
        maze = new MazeSessionWilson(this);
    }

    public void run() {
        UI.runWindow(this);
        UserInterface.drawMaze(cells);
        cells = maze.move();

        try {
            Thread.sleep(PAUSE);
        } catch (InterruptedException e) {
            LOGGER.info(e);
        }

        MazeSolverRandom mazeSolverRandom = new MazeSolverRandom();
        mazeSolverRandom.solve(cells);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public static int getHorizontalCells() {
        return HORIZONTAL_CELLS;
    }

    public static int getVerticalCells() {
        return VERTICAL_CELLS;
    }
}
