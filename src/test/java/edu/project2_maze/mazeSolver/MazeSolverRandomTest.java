package edu.project2_maze.mazeSolver;

import edu.project2_maze.cell.Cell;
import edu.project2_maze.maze.MazeSession;
import org.junit.jupiter.api.Test;

import static edu.project2_maze.cell.TypeOfCell.*;
import static org.assertj.core.api.Assertions.assertThat;

class MazeSolverRandomTest {

    @Test
    void solve() {
        Cell[][] input = {  {new Cell(0, 0, WALL), new Cell(0, 1, WALL), new Cell(0, 3, WALL), new Cell(0, 4, WALL), new Cell(0, 5, WALL) },
                            {new Cell(1, 0, WALL), new Cell(1, 1, PASSAGE), new Cell(1, 3, PASSAGE), new Cell(1, 4, END_POS), new Cell(1, 5, WALL) },
                            {new Cell(2, 0, WALL), new Cell(2, 1, PASSAGE), new Cell(2, 3, WALL), new Cell(2, 4, WALL), new Cell(2, 5, WALL) },
                            {new Cell(3, 0, WALL), new Cell(3, 1, START_POS), new Cell(3, 3, PASSAGE), new Cell(3, 4, PASSAGE), new Cell(3, 5, WALL) },
                            {new Cell(4, 0, WALL), new Cell(4, 1, WALL), new Cell(4, 3, WALL), new Cell(4, 4, WALL), new Cell(4, 5, WALL) } };

        MazeSession.setCells(input);
        MazeSolverRandom mazeSolverRandom = new MazeSolverRandom();
        Cell[][] response = mazeSolverRandom.solve();
        Cell[][] output = MazeSession.getCells();
        assertThat(response).isEqualTo(output);
    }
}
