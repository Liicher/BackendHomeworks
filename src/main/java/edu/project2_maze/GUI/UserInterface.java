package edu.project2_maze.GUI;

import edu.project2_maze.Maze.Cell;
import edu.project2_maze.Maze.MazeSessionWilson;
import edu.project2_maze.Maze.TypeOfCell;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserInterface {
    private static final JFrame frame = new JFrame("Maze");

    public void runWindowWilson(MazeSessionWilson mazeSession) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel(mazeSession);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(
            mazeSession.getHorizontalCells() * Cell.WIDTH + 20,
            mazeSession.getVerticalCells() * Cell.HEIGHT + 40
        );
        frame.setLocationByPlatform(true);
        frame.setResizable(true);
        frame.setVisible(true);
        drawMaze(mazeSession);
    }

    /*public void runWindowDepthFirstSearch(MazeSessionDepthFirstSearch m) {
    }*/

    public void drawMaze(MazeSessionWilson mazeSession) {
        for (int i = 0; i < mazeSession.cells.length; i++) {
            for (int j = 0; j < mazeSession.cells[i].length; j++) {
                if (mazeSession.cells[i][j].getType().equals(TypeOfCell.WALL)) {
                    mazeSession.cells[i][j].setColor(Color.BLACK);
                } else if (mazeSession.cells[i][j].getType().equals(TypeOfCell.WAY)) {
                    mazeSession.cells[i][j].setColor(Color.RED);
                } else if (mazeSession.cells[i][j].getType().equals(TypeOfCell.PASSAGE)) {
                    mazeSession.cells[i][j].setColor(Color.WHITE);
                }
            }
        }
        frame.repaint();
    }

    static class Panel extends JPanel {
        MazeSessionWilson mazeSession;

        public Panel(MazeSessionWilson mazeSession) {
            this.mazeSession = mazeSession;
        }

        @Override
        public void paintComponent(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            for (int i = 0; i < mazeSession.cells.length; i++) {
                for (int j = 0; j < mazeSession.cells[i].length; j++) {
                    g.setColor(mazeSession.cells[i][j].getColor());
                    g.fillRect(j * Cell.WIDTH, i * Cell.HEIGHT, Cell.WIDTH, Cell.HEIGHT);
                }
            }
        }
    }
}
