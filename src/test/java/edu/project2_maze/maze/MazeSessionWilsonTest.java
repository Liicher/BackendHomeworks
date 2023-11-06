package edu.project2_maze.maze;

import edu.project2_maze.cell.Cell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MazeSessionWilsonTest {
    private static MazeSessionWilson mazeSessionWilson;

    @Test
    void invalidInputs() {
        MazeSession.setCells(new Cell[0][10]);
        mazeSessionWilson = new MazeSessionWilson();
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionWilson.move();
        });

        MazeSession.setCells(new Cell[10][0]);
        mazeSessionWilson = new MazeSessionWilson();
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionWilson.move();
        });

        MazeSession.setCells(null);
        mazeSessionWilson = new MazeSessionWilson();
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionWilson.move();
        });
    }
}
