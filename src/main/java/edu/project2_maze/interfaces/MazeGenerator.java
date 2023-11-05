package edu.project2_maze.Interface;

import edu.project2_maze.Cell.Cell;

public interface MazeGenerator {
    Cell[][] move();

    Cell[][] mazeGenerator(int x, int y);
}
