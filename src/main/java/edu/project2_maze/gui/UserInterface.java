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
    private static final int MILLISECONDS_PER_FRAME = 5;
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

    public static void drawMaze(Cell[][] cells) {
        try {
            Thread.sleep(MILLISECONDS_PER_FRAME);
        } catch (InterruptedException e) {
            System.exit(0);
        }

        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                switch (value.getType()) {
                    case WALL -> value.setColor(Color.BLACK);
                    case WAY -> value.setColor(Color.RED);
                    case PASSAGE -> value.setColor(Color.WHITE);
                    case CURRENT -> value.setColor(Color.YELLOW);
                    case END_POS -> value.setColor(Color.PINK);
                    case WAY_CHECKER -> value.setColor(Color.GREEN);
                    case SOLVE_WAY -> value.setColor(Color.MAGENTA);
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
