package edu.project2_maze.maze;

import edu.project2_maze.cell.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeSessionDepthFirstSearchTest {
    private static MazeSessionDepthFirstSearch mazeSessionDepthFirstSearch;
    private static MazeSession mazeSession;

    @BeforeEach
    public void init() {
        mazeSession = new MazeSession();
    }

    @Test
    void invalidInputs() {
        MazeSession.setCells(new Cell[0][10]);
        mazeSessionDepthFirstSearch = new MazeSessionDepthFirstSearch();
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionDepthFirstSearch.move();
        });

        MazeSession.setCells(new Cell[10][0]);
        mazeSessionDepthFirstSearch = new MazeSessionDepthFirstSearch();
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionDepthFirstSearch.move();
        });

        MazeSession.setCells(null);
        mazeSessionDepthFirstSearch = new MazeSessionDepthFirstSearch();
        assertThrows(IllegalArgumentException.class, () -> {
            mazeSessionDepthFirstSearch.move();
        });
    }
}
