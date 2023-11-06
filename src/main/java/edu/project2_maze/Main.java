package edu.project2_maze;

import edu.project2_maze.gui.UserInterface;
import edu.project2_maze.maze.MazeSession;

public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        MazeSession mazeSession = new MazeSession(ui);
        mazeSession.run();
    }
}
