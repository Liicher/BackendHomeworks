package edu.project2_maze.maze;

import edu.project2_maze.cell.Cell;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeSessionDepthFirstSearchTest {
    private static MazeSession mazeSession;
    private static MazeSessionDepthFirstSearch mazeSessionDepthFirstSearch;

    @BeforeAll
    static void init() {
        mazeSession = new MazeSession();
    }

    @Test
    void invalidInputs() {
        mazeSessionDepthFirstSearch = new MazeSessionDepthFirstSearch(mazeSession, null);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionDepthFirstSearch.move();
        });

        Cell[][] cells = new Cell[0][0];
        mazeSessionDepthFirstSearch = new MazeSessionDepthFirstSearch(mazeSession, cells);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionDepthFirstSearch.move();
        });

        cells = new Cell[4][15];
        mazeSessionDepthFirstSearch = new MazeSessionDepthFirstSearch(mazeSession, cells);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionDepthFirstSearch.move();
        });

        cells = new Cell[13][3];
        mazeSessionDepthFirstSearch = new MazeSessionDepthFirstSearch(mazeSession, cells);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionDepthFirstSearch.move();
        });
    }
}
