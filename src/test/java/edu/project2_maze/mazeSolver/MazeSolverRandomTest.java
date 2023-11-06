package edu.project2_maze.mazeSolver;

import edu.project2_maze.cell.Cell;
import org.junit.jupiter.api.Test;
import static edu.project2_maze.cell.TypeOfCell.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MazeSolverRandomTest {

    @Test
    void solve() {
        Cell[][] input = {  { new Cell(0, 0, WALL), new Cell(0, 1, WALL),       new Cell(0, 2, WALL),       new Cell(0, 3, WALL),       new Cell(0, 4, WALL) },
                            { new Cell(1, 0, WALL), new Cell(1, 1, PASSAGE),    new Cell(1, 2, PASSAGE),    new Cell(1, 3, END_POS),    new Cell(1, 4, WALL) },
                            { new Cell(2, 0, WALL), new Cell(2, 1, PASSAGE),    new Cell(2, 2, WALL),       new Cell(2, 3, WALL),       new Cell(2, 4, WALL) },
                            { new Cell(3, 0, WALL), new Cell(3, 1, START_POS),  new Cell(3, 2, PASSAGE),    new Cell(3, 3, PASSAGE),    new Cell(3, 4, WALL) },
                            { new Cell(4, 0, WALL), new Cell(4, 1, WALL),       new Cell(4, 2, WALL),       new Cell(4, 3, WALL),       new Cell(4, 4, WALL) }};

        Cell[][] output = { { new Cell(0, 0, WALL), new Cell(0, 1, WALL),       new Cell(0, 2, WALL),       new Cell(0, 3, WALL),       new Cell(0, 4, WALL) },
                            { new Cell(1, 0, WALL), new Cell(1, 1, SOLVE_WAY),  new Cell(1, 2, SOLVE_WAY),  new Cell(1, 3, END_POS),    new Cell(1, 4, WALL) },
                            { new Cell(2, 0, WALL), new Cell(2, 1, SOLVE_WAY),  new Cell(2, 2, WALL),       new Cell(2, 3, WALL),       new Cell(2, 4, WALL) },
                            { new Cell(3, 0, WALL), new Cell(3, 1, START_POS),  new Cell(3, 2, PASSAGE),    new Cell(3, 3, PASSAGE),    new Cell(3, 4, WALL) },
                            { new Cell(4, 0, WALL), new Cell(4, 1, WALL),       new Cell(4, 2, WALL),       new Cell(4, 3, WALL),       new Cell(4, 4, WALL) }};

        MazeSolverRandom solver = new MazeSolverRandom();
        Cell[][] response = solver.solve(input);
        assertThat(response).isEqualTo(output);
    }
}
