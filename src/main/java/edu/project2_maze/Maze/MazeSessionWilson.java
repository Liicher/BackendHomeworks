package edu.project2_maze.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings({"ParameterAssignment", "CyclomaticComplexity"})
public class MazeSessionWilson {
    private static final int AROUND_CELLS = 8;              // Клетки вокруг
    private static final int LEFT = 0;                      // Кодовые значения для окружающих клеток
    private static final int RIGHT = 1;
    private static final int TOP = 2;
    private static final int UP = 3;
    private static final int START_POS = 5;                 // Стартовая позиция для обхода

    private final MazeSession mazeSession;
    private final Cell[][] cells;

    public MazeSessionWilson(MazeSession mazeSession, Cell[][] cells) {
        this.mazeSession = mazeSession;
        this.cells = cells;
    }

    public void move() {
        for (int i = 1; i < START_POS; i++) {
            cells[cells.length - 2][i].setType(TypeOfCell.PASSAGE);
        }

        Cell cell = cells[cells.length - 2][START_POS];
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

            // Цикл, чтобы делать два шага, так как при одном шаге
            // метод будет всегда считать что он создал цикл с предыдущим деревом
            for (int i = 0; i < 2; i++) {
                // Делаем шаг в выпавшую сторону
                switch (randomMove) {
                    case LEFT -> x--;
                    case RIGHT -> x++;
                    case TOP -> y--;
                    case UP -> y++;
                    default -> wayList.add(cells[y][x]);
                }

                // Добавляем наш шаг в список с путем
                wayList.add(cells[y][x]);
                cells[y][x].setType(TypeOfCell.WAY);

                // Булиан для проверки
                boolean isLoop = false;
                // Тяжелый случай.
                // Проверяем абстрактный шаг на соседние клетки, и если можно зациклить, то ходим туда
                if (cells[y][x + 1].getType().equals(TypeOfCell.WAY) && randomMove != LEFT) {
                    x++;
                    isLoop = true;
                } else if (cells[y][x - 1].getType().equals(TypeOfCell.WAY) && randomMove != RIGHT) {
                    x--;
                    isLoop = true;
                } else if (cells[y + 1][x].getType().equals(TypeOfCell.WAY) && randomMove != TOP) {
                    y++;
                    isLoop = true;
                } else if (cells[y - 1][x].getType().equals(TypeOfCell.WAY) && randomMove != UP) {
                    y--;
                    isLoop = true;
                }

                // Если цикл
                if (isLoop) {
                    // индекс "клетки" куда пришли
                    int retrace = wayList.indexOf(cells[y][x]);
                    for (int j = retrace + 1; j < wayList.size(); j++) {
                        wayList.get(j).setType(TypeOfCell.WALL);
                    }

                    wayList.subList(retrace + 1, wayList.size()).clear();
                    moveList.subList(retrace / 2 + 1, moveList.size()).clear();
                    mazeSession.drawMaze(cells);
                    break;
                }

                // если все клетки вокруг - проходы
                // переопределяем путь в - PASSAGE
                if (i == 0 && checkPassages(x, y)) {
                    for (Cell c : wayList) {
                        c.setType(TypeOfCell.PASSAGE);
                    }
                    mazeSession.drawMaze(cells);
                    return;
                }

                // Если нет зацикливания, то добавляем в наш список направления, наш ход
                moveList.add(randomMove);
                mazeSession.drawMaze(cells);
            }
        }
    }

    private boolean checkPassages(int x, int y) {
        if ((cells[y][x - 1].getType().equals(TypeOfCell.PASSAGE)
            || cells[y][x + 1].getType().equals(TypeOfCell.PASSAGE)
            || cells[y - 1][x].getType().equals(TypeOfCell.PASSAGE)
            || cells[y + 1][x].getType().equals(TypeOfCell.PASSAGE))) {
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
        if (x - 2 > 0 && previous != RIGHT) {
            sidesList.add(LEFT);
        }
        if (x + 2 < cells[0].length - 1 && previous != LEFT) {
            sidesList.add(RIGHT);
        }
        if (y - 2 > 0 && previous != UP) {
            sidesList.add(TOP);
        }
        if (y + 2 < cells.length - 1 && previous != TOP) {
            sidesList.add(UP);
        }
        return sidesList;
    }

    @SuppressWarnings("ModifiedControlVariable")
    private Cell findNextCell(Cell previousCell) {
        boolean isLoop = true;
        for (int y = cells.length - 2; y >= 1; y--) {
            for (int x = 1; x <= cells[y].length - 2; x++) {
                if (isLoop) {
                    x = previousCell.getX();
                    y = previousCell.getY();
                    isLoop = false;
                }

                if (checkAroundCells(x, y) == AROUND_CELLS) {
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
}