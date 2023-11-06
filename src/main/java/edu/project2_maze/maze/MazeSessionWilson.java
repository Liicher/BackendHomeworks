package edu.project2_maze.maze;

import edu.project2_maze.cell.Cell;
import edu.project2_maze.cell.TypeOfCell;
import edu.project2_maze.gui.UserInterface;
import edu.project2_maze.interfaces.MazeGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("ParameterAssignment")
public class MazeSessionWilson implements MazeGenerator {
    private static final int AROUND_CELLS = 8;      // Клетки вокруг
    private static final int LEFT = 0;              // Кодовые значения для окружающих клеток
    private static final int RIGHT = 1;
    private static final int TOP = 2;
    private static final int UP = 3;
    private static final int START_POS = 5;         // Стартовая позиция для обхода

    private final Cell[][] cells;
    private final List<Cell> wayList = new ArrayList<>();       // Список, хранит проверяемый путь из "клеток"
    private final List<Integer> moveList = new ArrayList<>();   // Список, хранит случайные направления

    public MazeSessionWilson(MazeSession maze) {
        this.cells = maze.getCells();
    }

    @Override
    public Cell[][] move() {
        if (cells == null || cells.length < START_POS || cells[0].length < START_POS) {
            throw new IllegalArgumentException();
        }

        // Немного стартового пути, чтобы генерация была побыстрее
        for (int i = 1; i < START_POS; i++) {
            cells[cells.length - 2][i].setType(TypeOfCell.PASSAGE);
        }

        Cell cell = cells[cells.length - 2][START_POS];
        while (cell != null) {
            mazeGenerator(cell.getX(), cell.getY());
            cell = findNextCell(cell);
        }

        return cells;
    }

    @Override
    public Cell[][] mazeGenerator(int x, int y) {
        Random random = new Random();
        wayList.add(cells[y][x]);               // Добавляем первую входную клетку в наш путь
        cells[y][x].setType(TypeOfCell.WAY);    // WAY - Проверяемый путь
        moveList.add(-1);                       // Добавим нулевой элемент для последующего сравнения

        boolean isDone = true;
        while (isDone) {
            List<Integer> sidesList = getSidesList(x, y);
            int randomMove = sidesList.get(random.nextInt(sidesList.size()));   // Шаг в случайную сторону

            // Цикл, чтобы делать два шага, для образования "толстых" стен
            for (int i = 0; i < 2; i++) {
                switch (randomMove) {
                    case LEFT -> x--;
                    case RIGHT -> x++;
                    case TOP -> y--;
                    case UP -> y++;
                    default -> {
                        throw new IllegalStateException();
                    }
                }

                // Добавляем наш шаг в список с путем
                wayList.add(cells[y][x]);
                cells[y][x].setType(TypeOfCell.WAY);

                // Тяжелый случай.
                // Проверяем абстрактный шаг на соседние клетки, и если можно зациклить, то ходим туда
                if (cells[y][x + 1].getType() == TypeOfCell.WAY && randomMove != LEFT) {
                    x++;
                    retraceAndClearWay(wayList.indexOf(cells[y][x]));
                    break;
                } else if (cells[y][x - 1].getType() == TypeOfCell.WAY && randomMove != RIGHT) {
                    x--;
                    retraceAndClearWay(wayList.indexOf(cells[y][x]));
                    break;
                } else if (cells[y + 1][x].getType() == TypeOfCell.WAY && randomMove != TOP) {
                    y++;
                    retraceAndClearWay(wayList.indexOf(cells[y][x]));
                    break;
                } else if (cells[y - 1][x].getType() == TypeOfCell.WAY && randomMove != UP) {
                    y--;
                    retraceAndClearWay(wayList.indexOf(cells[y][x]));
                    break;
                }

                // если все клетки вокруг - проходы, то переопределяем путь в - PASSAGE
                if (/*i == 0 &&*/ checkPassages(x, y)) {
                    Cell.remarkCellsWilson(wayList);
                    UserInterface.drawMaze(cells);
                    wayList.clear();
                    moveList.clear();
                    isDone = false;
                    break;
                }

                moveList.add(randomMove);       // Если нет зацикливания, то добавляем в наш список направления, наш ход
                UserInterface.drawMaze(cells);
            }
        }
        return cells;
    }

    private void retraceAndClearWay(int retrace) {
        for (int j = retrace + 1; j < wayList.size(); j++) {
            wayList.get(j).setType(TypeOfCell.WALL);
        }
        wayList.subList(retrace + 1, wayList.size()).clear();
        moveList.subList(retrace / 2 + 1, moveList.size()).clear();
        UserInterface.drawMaze(cells);
    }

    private boolean checkPassages(int x, int y) {
        return cells[y][x - 1].getType() == TypeOfCell.PASSAGE
            || cells[y][x + 1].getType() == TypeOfCell.PASSAGE
            || cells[y - 1][x].getType() == TypeOfCell.PASSAGE
            || cells[y + 1][x].getType() == TypeOfCell.PASSAGE;
    }

    // Метод, добавляющий возможные шаги
    private List<Integer> getSidesList(int x, int y) {
        List<Integer> sidesList = new ArrayList<>();
        int previous = moveList.get(moveList.size() - 1);

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
        if (cells[y][x - 1].getType() == TypeOfCell.WALL) {
            wallCellsAroundCounter++;
        }
        if (cells[y][x + 1].getType() == TypeOfCell.WALL) {
            wallCellsAroundCounter++;
        }
        if (cells[y - 1][x].getType() == TypeOfCell.WALL) {
            wallCellsAroundCounter++;
        }
        if (cells[y + 1][x].getType() == TypeOfCell.WALL) {
            wallCellsAroundCounter++;
        }
        if (cells[y - 1][x - 1].getType() == TypeOfCell.WALL) {
            wallCellsAroundCounter++;
        }
        if (cells[y - 1][x + 1].getType() == TypeOfCell.WALL) {
            wallCellsAroundCounter++;
        }
        if (cells[y + 1][x - 1].getType() == TypeOfCell.WALL) {
            wallCellsAroundCounter++;
        }
        if (cells[y + 1][x + 1].getType() == TypeOfCell.WALL) {
            wallCellsAroundCounter++;
        }
        return wallCellsAroundCounter;
    }
}
