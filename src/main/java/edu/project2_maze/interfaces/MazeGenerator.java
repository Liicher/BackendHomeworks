package edu.project2_maze.interfaces;

import edu.project2_maze.cell.Cell;

public interface MazeGenerator {
    Cell[][] move();

    Cell[][] mazeGenerator(int x, int y);
}
