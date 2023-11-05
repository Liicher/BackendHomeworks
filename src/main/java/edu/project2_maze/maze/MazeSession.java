package edu.project2_maze.maze;

import edu.project2_maze.cell.Cell;
import edu.project2_maze.cell.TypeOfCell;
import edu.project2_maze.gui.UserInterface;
import edu.project2_maze.interfaces.MazeGenerator;
import edu.project2_maze.mazeSolver.MazeSolverBreadthFirstSearch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeSession {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final MazeStarterCellsGenerators generator = new MazeStarterCellsGenerators();
    private static final UserInterface userInterface = new UserInterface();;
    private static final int MILLISECONDS_PER_FRAME = 20;
    private static final int HORIZONTAL_CELLS = 41;
    private static final int VERTICAL_CELLS = 21;
    private static final int PAUSE = 1000;

    private Cell[][] cells;
    private MazeGenerator maze;

    public MazeSession() {
        this.cells = generator.cellsGeneratorDepthFirstSearch(this);
        this.maze = new MazeSessionDepthFirstSearch(this, cells);
    }

    public void run() {
        //MazeGenerator maze = new MazeSessionDepthFirstSearch(this, cells);
        //MazeSessionWilson mazeSessionWilson = new MazeSessionWilson(this, cells);
        //userInterface = new UserInterface();
        userInterface.runWindow(this);
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
