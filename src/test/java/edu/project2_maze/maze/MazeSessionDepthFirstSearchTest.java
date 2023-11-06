package edu.project2_maze.maze;

import edu.project2_maze.cell.*;
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
        mazeSession.setCells(null);
        mazeSessionDepthFirstSearch = new MazeSessionDepthFirstSearch(mazeSession);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionDepthFirstSearch.move();
        });

        mazeSession.setCells(new Cell[0][0]);
        mazeSessionDepthFirstSearch = new MazeSessionDepthFirstSearch(mazeSession);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionDepthFirstSearch.move();
        });

        mazeSession.setCells(new Cell[3][15]);
        mazeSessionDepthFirstSearch = new MazeSessionDepthFirstSearch(mazeSession);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionDepthFirstSearch.move();
        });

        mazeSession.setCells(new Cell[13][3]);
        mazeSessionDepthFirstSearch = new MazeSessionDepthFirstSearch(mazeSession);
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionDepthFirstSearch.move();
        });
    }
}
