package edu.project2_maze.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("ParameterAssignment")
public class MazeSessionDepthFirstSearch {
    private static final int LEFT = 0;                      // Кодовые значения для окружающих клеток
    private static final int RIGHT = 1;
    private static final int TOP = 2;
    private static final int DOWN = 3;
    private static final int QUARTER = 4;

    private final MazeSession mazeSession;
    private final Cell[][] cells;
    private long allFields;
    private final List<Cell> wayList = new ArrayList<>();

    public MazeSessionDepthFirstSearch(MazeSession mazeSession, Cell[][] cells) {
        this.mazeSession = mazeSession;
        this.cells = cells;
        this.allFields =
            ((long) (mazeSession.getVerticalCells() - 1) * (mazeSession.getHorizontalCells() - 1)) / QUARTER;
    }

    public void move() {
        cells[cells.length - 2][1].setType(TypeOfCell.WAY);
        Cell cell = cells[cells.length - 2][1];
        mazeSession.drawMaze(cells);
        wayList.add(cells[cell.getX()][cell.getY()]);
        allFields--;
        mazeDepthFirstSearchGenerator(cell.getX(), cell.getY());
    }

    public void mazeDepthFirstSearchGenerator(int x, int y) {
        List<Integer> moveList = new ArrayList<>();
        Random random = new Random();
        cells[y][x].setType(TypeOfCell.CURRENT);    // Меняем ему тип
        moveList.add(-1);                           // Тупо для старта

        // Пока не тупик и не пройдены все поля
        while (allFields > 0) {
            // Алгоритм для шага в случайную сторону
            List<Integer> sidesList = getSidesList(x, y, moveList);
            int randomMove = sidesList.get(random.nextInt(sidesList.size()));
            for (int i = 0; i < 2; i++) {
                cells[y][x].setType(TypeOfCell.WAY);
                switch (randomMove) {
                    case LEFT -> x--;
                    case RIGHT -> x++;
                    case TOP -> y--;
                    case DOWN -> y++;
                }
                // Делаем шаг в случайную сторону
                wayList.add(cells[y][x]);
                cells[y][x].setType(TypeOfCell.CURRENT);
                moveList.add(randomMove);
                mazeSession.drawMaze(cells);
            }
            allFields--;

            if (isDeadlock(x, y) && allFields > 0) {
                int index = makeBackwardMove(x, y);
                x = wayList.get(index).getX();
                y = wayList.get(index).getY();
                moveList.add(-1);
            }
        }
        remarkCells();
        mazeSession.drawMaze(cells);
    }

    private int makeBackwardMove(int x, int y) {
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
            if (!(cells[y][x - 2].getType().equals(TypeOfCell.WAY)
                || cells[y][x - 2].getType().equals(TypeOfCell.CURRENT))) {
                return false;
            }
        }
        // Right
        if (x + 2 < cells[y].length - 1) {
            if (!(cells[y][x + 2].getType().equals(TypeOfCell.WAY)
                || cells[y][x + 2].getType().equals(TypeOfCell.CURRENT))) {
                return false;
            }
        }
        // Up
        if (y - 2 > 0) {
            if (!(cells[y - 2][x].getType().equals(TypeOfCell.WAY)
                || cells[y - 2][x].getType().equals(TypeOfCell.CURRENT))) {
                return false;
            }
        }
        // Down
        if (y + 2 < cells.length - 1) {
            if (!(cells[y + 2][x].getType().equals(TypeOfCell.WAY)
                || cells[y + 2][x].getType().equals(TypeOfCell.CURRENT))) {
                isLock = false;
            }
        }
        return isLock;
    }

    // Метод, добавляющий возможные шаги
    private List<Integer> getSidesList(int x, int y, List<Integer> moveList) {
        List<Integer> sidesList = new ArrayList<>();

        // Проверим возможные стороны для шага
        // Первая проверка на грань или соседний проход
        // Вторая проверка на предыдущий шаг
        if (x - 2 > 0 && !cells[y][x - 2].getType().equals(TypeOfCell.WAY)) {
            sidesList.add(LEFT);
        }
        if (x + 2 < cells[0].length - 1 && !cells[y][x + 2].getType().equals(TypeOfCell.WAY)) {
            sidesList.add(RIGHT);
        }
        if (y - 2 > 0 && !cells[y - 2][x].getType().equals(TypeOfCell.WAY)) {
            sidesList.add(TOP);
        }
        if (y + 2 < cells.length - 1 && !cells[y + 2][x].getType().equals(TypeOfCell.WAY)) {
            sidesList.add(DOWN);
        }
        if (sidesList.isEmpty()) {
            sidesList.add(-1);
        }
        return sidesList;
    }

    private void remarkCells() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getType().equals(TypeOfCell.WAY) || cells[i][j].getType().equals(TypeOfCell.CURRENT)) {
                    cells[i][j].setType(TypeOfCell.PASSAGE);
                }
            }
        }
    }
}

