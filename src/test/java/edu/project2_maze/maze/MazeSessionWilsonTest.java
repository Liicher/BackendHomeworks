package edu.project2_maze.maze;

import edu.project2_maze.cell.Cell;
import edu.project2_maze.gui.UserInterface;
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
        mazeSession.setCells(null);
        mazeSessionWilson = new MazeSessionWilson(mazeSession);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionWilson.move();
        });

        mazeSession.setCells(new Cell[0][0]);
        mazeSessionWilson = new MazeSessionWilson(mazeSession);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionWilson.move();
        });

        mazeSession.setCells(new Cell[3][15]);
        mazeSessionWilson = new MazeSessionWilson(mazeSession);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionWilson.move();
        });

        mazeSession.setCells(new Cell[13][3]);
        mazeSessionWilson = new MazeSessionWilson(mazeSession);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionWilson.move();
        });
    }
}
