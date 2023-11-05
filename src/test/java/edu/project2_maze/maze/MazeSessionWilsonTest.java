package edu.project2_maze.maze;

import edu.project2_maze.cell.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MazeSessionWilsonTest {
    private static MazeSession mazeSession;
    private static MazeSessionWilson mazeSessionWilson;

    @BeforeAll
    static void init() {
        mazeSession = new MazeSession();
    }

    @Test
    void invalidInputs() {
        mazeSessionWilson = new MazeSessionWilson(mazeSession, null);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionWilson.move();
        });

        Cell[][] cells = new Cell[0][0];
        mazeSessionWilson = new MazeSessionWilson(mazeSession, cells);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionWilson.move();
        });

        cells = new Cell[4][15];
        mazeSessionWilson = new MazeSessionWilson(mazeSession, cells);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionWilson.move();
        });

        cells = new Cell[13][3];
        mazeSessionWilson = new MazeSessionWilson(mazeSession, cells);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionWilson.move();
        });
    }
}
