package edu.project2_maze.maze;

import edu.project2_maze.cell.Cell;
import edu.project2_maze.cell.TypeOfCell;
import edu.project2_maze.gui.UserInterface;
import edu.project2_maze.interfaces.MazeGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("ParameterAssignment")
public class MazeSessionDepthFirstSearch implements MazeGenerator {
    private static final int LEFT = 0;      // Кодовые значения для окружающих клеток
    private static final int RIGHT = 1;
    private static final int TOP = 2;
    private static final int DOWN = 3;
    private static final int QUARTER = 4;   // Часть из формулы для нахождения всех полей
    private static final int START_POS = 5; // Место для стартовой позиции

    private final List<Cell> wayList = new ArrayList<>();
    private Cell[][] cells;
    private long allFields;

    public MazeSessionDepthFirstSearch() {
        this.cells = MazeSession.getCells();
        this.allFields =
            ((long) (MazeSession.getVerticalCells() - 1) * (MazeSession.getHorizontalCells() - 1)) / QUARTER;
    }

    @Override
    public Cell[][] move() {
        if (cells == null || cells.length < START_POS || cells[0].length < START_POS) {
            throw new IllegalArgumentException();
        }

        cells[cells.length - 2][1].setType(TypeOfCell.WAY);
        Cell cell = cells[cells.length - 2][1];
        UserInterface.drawMaze(cells);
        wayList.add(cells[cell.getX()][cell.getY()]);
        allFields--;
        cells = mazeGenerator(cell.getX(), cell.getY());
        return cells;
    }

    @Override
    public Cell[][] mazeGenerator(int x, int y) {
        Random random = new Random();
        cells[y][x].setType(TypeOfCell.CURRENT);    // Меняем ему тип

        // Пока не пройдены все поля
        while (allFields > 0) {
            // Алгоритм для шага в случайную сторону
            List<Integer> sidesList = getSidesList(x, y);
            int randomMove = sidesList.get(random.nextInt(sidesList.size()));
            for (int i = 0; i < 2; i++) {
                cells[y][x].setType(TypeOfCell.WAY);
                switch (randomMove) {
                    case LEFT -> x--;
                    case RIGHT -> x++;
                    case TOP -> y--;
                    case DOWN -> y++;
                    default -> {
                        throw new IllegalStateException();
                    }
                }
                // Делаем шаг в случайную сторону
                wayList.add(cells[y][x]);
                cells[y][x].setType(TypeOfCell.CURRENT);
                UserInterface.drawMaze(cells);
            }
            allFields--;

            if (isDeadlock(x, y) && allFields > 0) {
                int index = doBackwardMove(x, y);
                x = wayList.get(index).getX();
                y = wayList.get(index).getY();
            }
        }
        Cell.remarkCellsDFS();
        UserInterface.drawMaze(cells);

        return cells;
    }

    private int doBackwardMove(int x, int y) {
        int index = wayList.size() - 1;
        while (isDeadlock(x, y)) {
            index = index - 2;
            cells[y][x].setType(TypeOfCell.WAY);
            x = wayList.get(index).getX();
            y = wayList.get(index).getY();
            cells[y][x].setType(TypeOfCell.CURRENT);
        }
        return index;
    }

    private boolean isDeadlock(int x, int y) {
        boolean isLock = true;
        // Left
        if (x - 2 > 0) {
            if (!(cells[y][x - 2].getType() == TypeOfCell.WAY
                || cells[y][x - 2].getType() == TypeOfCell.CURRENT)) {
                return false;
            }
        }
        // Right
        if (x + 2 < cells[y].length - 1) {
            if (!(cells[y][x + 2].getType() == TypeOfCell.WAY
                || cells[y][x + 2].getType() == TypeOfCell.CURRENT)) {
                return false;
            }
        }
        // Up
        if (y - 2 > 0) {
            if (!(cells[y - 2][x].getType() == TypeOfCell.WAY
                || cells[y - 2][x].getType() == TypeOfCell.CURRENT)) {
                return false;
            }
        }
        // Down
        if (y + 2 < cells.length - 1) {
            if (!(cells[y + 2][x].getType() == TypeOfCell.WAY
                || cells[y + 2][x].getType() == TypeOfCell.CURRENT)) {
                isLock = false;
            }
        }
        return isLock;
    }

    // Метод, добавляющий возможные шаги
    private List<Integer> getSidesList(int x, int y) {
        List<Integer> sidesList = new ArrayList<>();

        if (x - 2 > 0 && cells[y][x - 2].getType() != TypeOfCell.WAY) {
            sidesList.add(LEFT);
        }
        if (x + 2 < cells[0].length - 1 && cells[y][x + 2].getType() != TypeOfCell.WAY) {
            sidesList.add(RIGHT);
        }
        if (y - 2 > 0 && cells[y - 2][x].getType() != TypeOfCell.WAY) {
            sidesList.add(TOP);
        }
        if (y + 2 < cells.length - 1 && cells[y + 2][x].getType() != TypeOfCell.WAY) {
            sidesList.add(DOWN);
        }
        if (sidesList.isEmpty()) {
            sidesList.add(-1);
        }
        return sidesList;
    }
}

