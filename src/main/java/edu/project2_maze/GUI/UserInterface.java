package edu.project2_maze.GUI;

import edu.project2_maze.Maze.Cell;
import edu.project2_maze.Maze.MazeSession;
import edu.project2_maze.Maze.TypeOfCell;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserInterface {
    private static final int SIZE = 50;
    private static final JFrame FRAME = new JFrame("Maze");

    public void runWindow(MazeSession mazeSession) {
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel(mazeSession);
        FRAME.getContentPane().add(BorderLayout.CENTER, panel);
        FRAME.setSize(
            mazeSession.getHorizontalCells() * Cell.WIDTH + SIZE,
            mazeSession.getVerticalCells() * Cell.HEIGHT + SIZE
        );
        FRAME.setLocationByPlatform(true);
        FRAME.setResizable(false);
        FRAME.setVisible(true);
    }

    public void drawMaze(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getType().equals(TypeOfCell.WALL)) {
                    cells[i][j].setColor(Color.BLACK);
                } else if (cells[i][j].getType().equals(TypeOfCell.WAY)) {
                    cells[i][j].setColor(Color.RED);
                } else if (cells[i][j].getType().equals(TypeOfCell.PASSAGE)) {
                    cells[i][j].setColor(Color.WHITE);
                }
            }
        }
        FRAME.repaint();
    }

    static class Panel extends JPanel {
        MazeSession mazeSession;
        Cell[][] cells;

        private Panel(MazeSession mazeSession) {
            this.mazeSession = mazeSession;
            this.cells = mazeSession.getCells();
        }

        @Override
        public void paintComponent(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    g.setColor(cells[i][j].getColor());
                    g.fillRect(j * Cell.WIDTH, i * Cell.HEIGHT, Cell.WIDTH, Cell.HEIGHT);
                }
            }
        }
    }
}
