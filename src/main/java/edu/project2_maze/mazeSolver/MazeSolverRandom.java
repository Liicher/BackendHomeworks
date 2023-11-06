package edu.project2_maze.mazeSolver;

import edu.project2_maze.cell.Cell;
import edu.project2_maze.cell.TypeOfCell;
import edu.project2_maze.gui.UserInterface;
import edu.project2_maze.maze.MazeSession;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ParameterAssignment")
public class MazeSolverRandom {
    private static final int LEFT = 0;       // Кодовые значения для окружающих клеток
    private static final int TOP = 1;
    private static final int RIGHT = 2;
    private static final int DOWN = 3;

    private final Cell[][] cells;
    private final List<Cell> wayList = new ArrayList<>();
    private final List<Cell> solveWayList = new ArrayList<>();

    public MazeSolverRandom() {
        this.cells = MazeSession.getCells();
    }

    public void solve() {
        cells[cells.length - 2][1].setType(TypeOfCell.START_POS);
        cells[1][cells[1].length - 2].setType(TypeOfCell.END_POS);
        Cell cell = cells[cells.length - 2][1];

        wayList.add(cells[cells.length - 2][1]);
        solveWayList.add(cells[cells.length - 2][1]);
        mazeSolver(cell.getX(), cell.getY());
    }

    private void mazeSolver(int x, int y) {
        while (cells[y][x].getType() != TypeOfCell.END_POS) {
            List<Integer> sidesList = getSidesList(x, y);
            while (!sidesList.isEmpty()) {
                int move = sidesList.get(0);
                cells[y][x].setType(TypeOfCell.WAY);
                switch (move) {
                    case LEFT -> x--;
                    case RIGHT -> x++;
                    case TOP -> y--;
                    case DOWN -> y++;
                    default -> {
                        return;
                    }
                }
                wayList.add(cells[y][x]);
                solveWayList.add(cells[y][x]);
                if (cells[y][x].getType() == TypeOfCell.END_POS) {
                    break;
                }
                cells[y][x].setType(TypeOfCell.WAY_CHECKER);
                UserInterface.drawMaze(cells);
                sidesList = getSidesList(x, y);
            }
            if (cells[y][x].getType() != TypeOfCell.END_POS) {
                int index = doBackwardMove(x, y);
                x = wayList.get(index).getX();
                y = wayList.get(index).getY();
                UserInterface.drawMaze(cells);
            }
        }
        Cell.remarkSolveWay(solveWayList);
        UserInterface.drawMaze(cells);
    }

    private int doBackwardMove(int x, int y) {
        int index = wayList.size() - 1;
        int solverIndex = solveWayList.size() - 1;
        List<Integer> checker = getSidesList(x, y);
        while (checker.isEmpty()) {
            solveWayList.remove(solverIndex);
            wayList.remove(solverIndex);

            index = index - 1;
            solverIndex = solverIndex - 1;

            cells[y][x].setType(TypeOfCell.WAY);
            x = wayList.get(index).getX();
            y = wayList.get(index).getY();
            cells[y][x].setType(TypeOfCell.WAY_CHECKER);

            checker = getSidesList(x, y);
        }
        return index;
    }

    private List<Integer> getSidesList(int x, int y) {
        List<Integer> sidesList = new ArrayList<>();

        if (x - 1 > 0 && (cells[y][x - 1].getType() == TypeOfCell.PASSAGE
            || cells[y][x - 1].getType() == TypeOfCell.END_POS)) {
            sidesList.add(LEFT);
        }
        if (y - 1 > 0 && (cells[y - 1][x].getType() == TypeOfCell.PASSAGE
            || cells[y - 1][x].getType() == TypeOfCell.END_POS)) {
            sidesList.add(TOP);
        }
        if (x + 1 < cells[0].length - 1 && (cells[y][x + 1].getType() == TypeOfCell.PASSAGE
            || cells[y][x + 1].getType() == TypeOfCell.END_POS)) {
            sidesList.add(RIGHT);
        }
        if (y + 1 < cells.length - 1 && (cells[y + 1][x].getType() == TypeOfCell.PASSAGE
            || cells[y + 1][x].getType() == TypeOfCell.END_POS)) {
            sidesList.add(DOWN);
        }
        return sidesList;
    }
}
