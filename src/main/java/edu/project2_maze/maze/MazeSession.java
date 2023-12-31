package edu.project2_maze.maze;

import edu.project2_maze.cell.Cell;
import edu.project2_maze.gui.UserInterface;
import edu.project2_maze.interfaces.MazeGenerator;
import edu.project2_maze.mazeSolver.MazeSolverRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeSession {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int HORIZONTAL_CELLS = 51;
    private static final int VERTICAL_CELLS = 21;
    private static final int PAUSE = 1000;
    private static final MazeStarterCellsGenerators INIT = new MazeStarterCellsGenerators();

    private Cell[][] cells;
    private final MazeGenerator maze;

    public MazeSession() {
        cells = INIT.cellsGeneratorWilson(VERTICAL_CELLS, HORIZONTAL_CELLS);
        maze = new MazeSessionWilson(this);
    }

    public void run() {
        UserInterface.runWindow(this);
        UserInterface.drawMaze(cells);
        cells = maze.move();
        UserInterface.drawMaze(cells);
        try {
            Thread.sleep(PAUSE);
        } catch (InterruptedException e) {
            LOGGER.info(e);
        }

        MazeSolverRandom mazeSolverRandom = new MazeSolverRandom();
        mazeSolverRandom.solve(cells);
        UserInterface.drawMaze(cells);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int getHorizontalCells() {
        return HORIZONTAL_CELLS;
    }

    public int getVerticalCells() {
        return VERTICAL_CELLS;
    }
}
