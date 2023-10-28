package edu.project2_maze.Maze;

import edu.project2_maze.GUI.UserInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeSessionWilson {
    UserInterface userInterface;
    public Cell[][] cells = cellsGenerator();
    private static final int HORIZONTAL_CELLS = 13;
    private static final int VERTICAL_CELLS = 13;
    private static final int MILLISECONDS_PER_FRAME = 2;

    public void run() {
        userInterface = new UserInterface();
        userInterface.runWindowWilson(this);
        move();
    }

    public void move() {
        cells[cells.length - 2][1].setType(TypeOfCell.PASSAGE);
        cells[cells.length - 2][2].setType(TypeOfCell.PASSAGE);
        cells[cells.length - 2][3].setType(TypeOfCell.PASSAGE);

        Cell cell = cells[cells.length - 2][5];
        while (cell != null) {
            mazeGenerator(cell.getX(), cell.getY());
            cell = findNextCell(cell);
        }
    }

    private void mazeGenerator(int x, int y) {
        // Список, хранит проверяемый путь из "клеток"
        List<Cell> wayList = new ArrayList<>();
        // Список, хранит случайные направления
        // 0 - лево, 1 - право
        // 2 - низ, 3 - верх
        List<Integer> moveList = new ArrayList<>();
        Random random = new Random();

        // Добавляем первую входную клетку в наш путь
        wayList.add(cells[y][x]);
        // WAY - Проверяемый путь
        // PASSAGE - Уже закрепленный путь
        cells[y][x].setType(TypeOfCell.WAY);
        // Добавим нулевой элемент для последующего сравнения
        moveList.add(-1);

        while (true) {
            // Список, в какую сторону можно сделать шаг
            List<Integer> sidesList = getSidesList(x, y, moveList);
            // Выбираем случайную сторону для шага
            int randomMove = sidesList.get(random.nextInt(sidesList.size()));

            // Цикл, чтобы делать два шага, так как при одном шаге метод будет всегда считать что он создал цикл с предыдущим деревом
            for (int i = 0; i < 2; i++) {
                // Делаем шаг в выпавшую сторону
                switch (randomMove) {
                    case 0 -> x--;
                    case 1 -> x++;
                    case 2 -> y--;
                    case 3 -> y++;
                }

                // Добавляем наш шаг в список с путем
                wayList.add(cells[y][x]);
                cells[y][x].setType(TypeOfCell.WAY);

                // Булиан для проверки
                boolean isLoop = false;

                // Тяжелый случай.
                // Проверяем абстрактный шаг на соседние клетки, и если можно зациклить, то ходим туда
                if (cells[y][x + 1].getType().equals(TypeOfCell.WAY) && randomMove != 0) {
                    x++;
                    isLoop = true;
                } else if (cells[y][x - 1].getType().equals(TypeOfCell.WAY) && randomMove != 1) {
                    x--;
                    isLoop = true;
                } else if (cells[y + 1][x].getType().equals(TypeOfCell.WAY) && randomMove != 2) {
                    y++;
                    isLoop = true;
                } else if (cells[y - 1][x].getType().equals(TypeOfCell.WAY) && randomMove != 3) {
                    y--;
                    isLoop = true;
                } else if (cells[y - 1][x - 1].getType().equals(TypeOfCell.WAY) &&
                    cells[y - 1][x - 1] != wayList.get(wayList.size() - 3)) {
                    x--;
                    y--;
                    isLoop = true;
                } else if (cells[y - 1][x + 1].getType().equals(TypeOfCell.WAY) &&
                    cells[y - 1][x + 1] != wayList.get(wayList.size() - 3)) {
                    x++;
                    y--;
                    isLoop = true;
                } else if (cells[y + 1][x - 1].getType().equals(TypeOfCell.WAY) &&
                    cells[y + 1][x - 1] != wayList.get(wayList.size() - 3)) {
                    x--;
                    y++;
                    isLoop = true;
                } else if (cells[y + 1][x + 1].getType().equals(TypeOfCell.WAY) &&
                    cells[y + 1][x + 1] != wayList.get(wayList.size() - 3)) {
                    x++;
                    y++;
                    isLoop = true;
                }

                // Если цикл
                if (isLoop) {
                    // индекс "клетки" куда пришли
                    int retrace = wayList.indexOf(cells[y][x]);

                    //
                    for (int j = retrace + 1; j < wayList.size(); j++) {
                        wayList.get(j).setType(TypeOfCell.WALL);
                    }

                    wayList.subList(retrace + 1, wayList.size()).clear();
                    moveList.subList(retrace / 2 + 1, moveList.size()).clear();

                    //frame.repaint();
                    userInterface.drawMaze(this);
                    break;
                }

                // если все клетки вокруг - проходы
                // переопределяем путь в - PASSAGE
                if (i == 0 && (cells[y][x - 1].getType().equals(TypeOfCell.PASSAGE) ||
                    cells[y][x + 1].getType().equals(TypeOfCell.PASSAGE) ||
                    cells[y - 1][x].getType().equals(TypeOfCell.PASSAGE) ||
                    cells[y + 1][x].getType().equals(TypeOfCell.PASSAGE))) {
                    for (Cell c : wayList) {
                        c.setType(TypeOfCell.PASSAGE);
                    }
                    userInterface.drawMaze(this);
                    return;
                }

                // Если нет зацикливания, то добавляем в наш список направления, наш ход
                moveList.add(randomMove);
                userInterface.drawMaze(this);

                // Для наглядности алгоритма
                try {
                    Thread.sleep(MILLISECONDS_PER_FRAME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
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

    private Cell findNextCell(Cell previousCell) {
        boolean isLoop = true;
        for (int y = cells.length - 2; y >= 1; y--) {
            for (int x = 1; x <= cells[y].length - 2; x++) {
                if (isLoop) {
                    x = previousCell.getX();
                    y = previousCell.getY();
                    isLoop = false;
                }

                if (checkAroundCells(x, y) == 8) {
                    return cells[y][x];
                }
            }
        }
        return null;
    }

    private int checkAroundCells(int x, int y) {
        int wallCellsAroundCounter = 0;
        // Тяжелый случай №2
        if (cells[y][x - 1].getType().equals(TypeOfCell.WALL)) {
            wallCellsAroundCounter++;
        }
        if (cells[y][x + 1].getType().equals(TypeOfCell.WALL)) {
            wallCellsAroundCounter++;
        }
        if (cells[y - 1][x].getType().equals(TypeOfCell.WALL)) {
            wallCellsAroundCounter++;
        }
        if (cells[y + 1][x].getType().equals(TypeOfCell.WALL)) {
            wallCellsAroundCounter++;
        }
        if (cells[y - 1][x - 1].getType().equals(TypeOfCell.WALL)) {
            wallCellsAroundCounter++;
        }
        if (cells[y - 1][x + 1].getType().equals(TypeOfCell.WALL)) {
            wallCellsAroundCounter++;
        }
        if (cells[y + 1][x - 1].getType().equals(TypeOfCell.WALL)) {
            wallCellsAroundCounter++;
        }
        if (cells[y + 1][x + 1].getType().equals(TypeOfCell.WALL)) {
            wallCellsAroundCounter++;
        }
        return wallCellsAroundCounter;
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
