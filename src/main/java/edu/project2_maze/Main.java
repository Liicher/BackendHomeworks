package edu.project2_maze;

import edu.project2_maze.maze.MazeSession;

public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        MazeSession mazeSession = new MazeSession();
        mazeSession.run();
    }
}
