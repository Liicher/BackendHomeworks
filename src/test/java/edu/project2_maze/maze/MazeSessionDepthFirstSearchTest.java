package edu.project2_maze.maze;

import edu.project2_maze.cell.Cell;
import edu.project2_maze.cell.TypeOfCell;
import edu.project2_maze.mazeSolver.MazeSolverRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static edu.project2_maze.cell.TypeOfCell.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class MazeSessionDepthFirstSearchTest {
    private static MazeSessionDepthFirstSearch mazeSessionDepthFirstSearch;
    private Cell[][] inputCells = null;
    private Cell[][] outputCells = null;

    @BeforeEach
    void init() {
        MazeSession mazeSession = new MazeSession();
        inputCells = new Cell[][]{
            {new Cell(0, 0, WALL), new Cell(0, 1, WALL),        new Cell(0, 2, WALL),       new Cell(0, 3, WALL),       new Cell(0, 4, WALL)},
            {new Cell(1, 0, WALL), new Cell(1, 1, PASSAGE),     new Cell(1, 2, PASSAGE),    new Cell(1, 3, END_POS),    new Cell(1, 4, WALL)},
            {new Cell(2, 0, WALL), new Cell(2, 1, PASSAGE),     new Cell(2, 2, WALL),       new Cell(2, 3, WALL),       new Cell(2, 4, WALL)},
            {new Cell(3, 0, WALL), new Cell(3, 1, START_POS),   new Cell(3, 2, PASSAGE),    new Cell(3, 3, PASSAGE),    new Cell(3, 4, WALL)},
            {new Cell(4, 0, WALL), new Cell(4, 1, WALL),        new Cell(4, 2, WALL),       new Cell(4, 3, WALL),       new Cell(4, 4, WALL)}};
    }

    @Test
    void test() {
        MazeSession.setCells(inputCells);
        MazeSolverRandom mazeSolverRandom = new MazeSolverRandom();
        Cell[][] response = mazeSolverRandom.solve();
        assertThat(response).isEqualTo(inputCells);
    }

    @Test
    void invalidInputs() {
        MazeSessionDepthFirstSearch maze = new MazeSessionDepthFirstSearch();
        MazeSession.setMaze(maze);

        MazeSession.setCells(null);
        assertThrows(IllegalArgumentException.class, maze::move);

        MazeSession.setCells(new Cell[0][10]);
        assertThrows(IllegalArgumentException.class, maze::move);

        MazeSession.setCells(new Cell[10][0]);
        assertThrows(IllegalArgumentException.class, maze::move);
    }
}
