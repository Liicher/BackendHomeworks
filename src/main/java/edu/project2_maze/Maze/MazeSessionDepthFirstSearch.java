/*

package edu.project2_maze.Maze;

import edu.project2_maze.GUI.UserInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeSessionDepthFirstSearch {
    UserInterface userInterface;
    public Cell[][] cells = cellsGenerator();
    private static final int HORIZONTAL_CELLS = 101;
    private static final int VERTICAL_CELLS = 51;
    private static final int MILLISECONDS_PER_FRAME = 2;
    private int fields = (((HORIZONTAL_CELLS - 1) * (VERTICAL_CELLS - 1)) / 4);

    public void run() {
        userInterface = new UserInterface();
        //userInterface.runWindowWilson(this);
        move();
    }

    public void move() {
        cells[cells.length - 2][1].setType(TypeOfCell.WAY);
        Cell cell = cells[cells.length - 2][1];
        mazeDepthFirstSearchGenerator(cell.getX(), cell.getY());
    }

    public void mazeDepthFirstSearchGenerator(int x, int y) {
        List<Cell> wayList = new ArrayList<>();
        List<Integer> moveList = new ArrayList<>();
        Random random = new Random();

        wayList.add(cells[y][x]);               // Добавляем первый шаг
        fields--;                               // Количество полей всего
        cells[y][x].setType(TypeOfCell.WAY);    // Меняем ему тип
        moveList.add(-1);                       // Тупо для старта

        while (!isDeadlock(x, y)) {
            // Алгоритм для шага в случайную сторону
            List<Integer> sidesList = getSidesList(x, y, moveList);
            int randomMove = sidesList.get(random.nextInt(sidesList.size()));
            switch (randomMove) {
                case 0 -> x--;
                case 1 -> x++;
                case 2 -> y--;
                case 3 -> y++;
            }
            // Делаем шаг в случайную сторону
            wayList.add(cells[y][x]);
            fields--;
            cells[y][x].setType(TypeOfCell.WAY);
        }

        if (fields == 0) {
            return;
        }

        // Индекс для обратного прохода
        int index = wayList.size() - 2;
        while (fields != 0 || index != 0) {
            int lastX = wayList.get(index).getX();
            int lastY = wayList.get(index).getY();
            if (isDeadlock(lastX, lastY)) {
                index--;
                continue;
            } else {
                wayList = makeBackwardMove(wayList, lastX, lastY);
            }
        }
    }

    private List<Cell> makeBackwardMove(List<Cell> inputList, int x, int y) {
        List<Cell> wayList = new ArrayList<>(inputList);
        List<Integer> moveList = new ArrayList<>();
        Random random = new Random();

        wayList.add(cells[y][x]);               // Добавляем первый шаг
        fields--;                               // Количество полей всего
        cells[y][x].setType(TypeOfCell.WAY);    // Меняем ему тип
        moveList.add(-1);                       // Тупо для старта

        while (!isDeadlock(x, y)) {
            // Алгоритм для шага в случайную сторону
            List<Integer> sidesList = getSidesList(x, y, moveList);
            int randomMove = sidesList.get(random.nextInt(sidesList.size()));
            switch (randomMove) {
                case 0 -> x--;
                case 1 -> x++;
                case 2 -> y--;
                case 3 -> y++;
            }
            // Делаем шаг в случайную сторону
            wayList.add(cells[y][x]);
            fields--;
            cells[y][x].setType(TypeOfCell.WAY);
        }

        return wayList;
    }

    private int backwardCheckSides(int x, int y) {
        if (cells[y][x - 1].getType().equals(TypeOfCell.WALL)) {
            return 0;
        }
        if (cells[y][x + 1].getType().equals(TypeOfCell.WALL)) {
            return 1;
        }
        if (cells[y - 1][x].getType().equals(TypeOfCell.WALL)) {
            return 2;
        }
        if (cells[y + 1][x].getType().equals(TypeOfCell.WALL)) {
            return 3;
        }

        return -1;
    }

    private boolean isDeadlock(int x, int y) {
        if (cells[y][x - 1].getType().equals(TypeOfCell.WAY) &&
            cells[y][x + 1].getType().equals(TypeOfCell.WAY) &&
            cells[y - 1][x].getType().equals(TypeOfCell.WAY) &&
            cells[y + 1][x].getType().equals(TypeOfCell.WAY)) {
            return true;
        }
        return false;
    }

    private List<Integer> getSidesList(int x, int y, List<Integer> moveList) {
        List<Integer> sidesList = new ArrayList<>();
        int previous = moveList.get(moveList.size() - 1);

        // Проверим возможные стороны для шага
        // Первая проверка на грань или соседний проход
        // Вторая проверка на предыдущий шаг
        if (x - 2 > 0 && previous != 1) {
            sidesList.add(0);
        }
        if (x + 2 < cells[0].length - 1 && previous != 0) {
            sidesList.add(1);
        }
        if (y - 2 > 0 && previous != 3) {
            sidesList.add(2);
        }
        if (y + 2 < cells.length - 1 && previous != 2) {
            sidesList.add(3);
        }
        return sidesList;
    }

    private Cell[][] cellsGenerator() {
        cells = new Cell[VERTICAL_CELLS][HORIZONTAL_CELLS];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(j, i, TypeOfCell.WALL);
            }
        }
        return cells;
    }

    public int getHorizontalCells() {
        return HORIZONTAL_CELLS;
    }

    public int getVerticalCells() {
        return VERTICAL_CELLS;
    }
}
*/
