package edu.project2_maze.mazeSolver;

import edu.project2_maze.cell.Cell;
import edu.project2_maze.cell.TypeOfCell;
import edu.project2_maze.maze.MazeSession;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ParameterAssignment")
public class MazeSolverBreadthFirstSearch {
    private static final int LEFT = 0;       // Кодовые значения для окружающих клеток
    private static final int TOP = 1;
    private static final int RIGHT = 2;
    private static final int DOWN = 3;

    private final MazeSession mazeSession;
    private final Cell[][] cells;
    private final List<Cell> wayList = new ArrayList<>();
    private final List<Cell> solveWayList = new ArrayList<>();

    public MazeSolverBreadthFirstSearch(MazeSession mazeSession, Cell[][] cells) {
        this.mazeSession = mazeSession;
        this.cells = cells;
    }

    public void solve() {
        cells[cells.length - 2][1].setType(TypeOfCell.START_POS);
        cells[1][cells[0].length - 2].setType(TypeOfCell.END_POS);
        Cell cell = cells[cells.length - 2][1];

        wayList.add(cells[cell.getX()][cell.getY()]);
        solveWayList.add(cells[cell.getX()][cell.getY()]);
        mazeSolverBFS(cell.getX(), cell.getY());
    }

    private void mazeSolverBFS(int x, int y) {
        while (!cells[y][x].getType().equals(TypeOfCell.END_POS)) {
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
                mazeSession.drawMaze(cells);
                sidesList = getSidesList(x, y);
            }
            if (cells[y][x].getType() != TypeOfCell.END_POS) {
                int index = doBackwardMove(x, y);
                x = wayList.get(index).getX();
                y = wayList.get(index).getY();
                mazeSession.drawMaze(cells);
            }
        }
        repaintSolveWay();
        mazeSession.drawMaze(cells);
    }

    private void repaintSolveWay() {
        for (Cell cell : solveWayList) {
            cell.setType(TypeOfCell.SOLVE_WAY);
        }

        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                if (value.getType().equals(TypeOfCell.WAY)) {
                    value.setType(TypeOfCell.PASSAGE);
                }
            }
        }
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

        // Проверим возможные стороны для шага
        // Первая проверка на грань или соседний проход
        // Вторая проверка на предыдущий шаг
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
