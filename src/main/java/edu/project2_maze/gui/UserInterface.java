package edu.project2_maze.gui;

import edu.project2_maze.cell.Cell;
import edu.project2_maze.maze.MazeSession;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserInterface {
    private static final int SIZE_VER = 40;
    private static final int SIZE_GOR = 15;
    private static final JFrame FRAME = new JFrame("Maze");

    public void runWindow(MazeSession mazeSession) {
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel(mazeSession);
        FRAME.getContentPane().add(BorderLayout.CENTER, panel);
        FRAME.setSize(
            mazeSession.getHorizontalCells() * Cell.WIDTH + SIZE_GOR,
            mazeSession.getVerticalCells() * Cell.HEIGHT + SIZE_VER
        );
        FRAME.setLocationByPlatform(true);
        FRAME.setResizable(false);
        FRAME.setVisible(true);
    }

    public void drawMaze(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                switch (cells[i][j].getType()) {
                    case WALL -> cells[i][j].setColor(Color.BLACK);
                    case WAY -> cells[i][j].setColor(Color.RED);
                    case PASSAGE -> cells[i][j].setColor(Color.WHITE);
                    case CURRENT -> cells[i][j].setColor(Color.YELLOW);
                    case END_POS -> cells[i][j].setColor(Color.PINK);
                    case WAY_CHECKER -> cells[i][j].setColor(Color.GREEN);
                    case SOLVE_WAY -> cells[i][j].setColor(Color.MAGENTA);
                    default -> {
                        return;
                    }
                }

                cells[cells.length - 2][1].setColor(Color.GREEN);
                cells[1][cells[0].length - 2].setColor(Color.PINK);
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
