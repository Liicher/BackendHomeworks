package edu.project2_maze.maze;

import edu.project2_maze.cell.Cell;
import edu.project2_maze.cell.TypeOfCell;

public class MazeStarterCellsGenerators {
    private Cell[][] cells;

    public Cell[][] cellsGeneratorDepthFirstSearch() {
        cells = new Cell[MazeSession.getVerticalCells()][MazeSession.getHorizontalCells()];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    cells[i][j] = new Cell(j, i, TypeOfCell.PASSAGE);
                } else {
                    cells[i][j] = new Cell(j, i, TypeOfCell.WALL);
                }
            }
        }
        return cells;
    }

    public Cell[][] cellsGeneratorWilson() {
        cells = new Cell[MazeSession.getVerticalCells()][MazeSession.getHorizontalCells()];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(j, i, TypeOfCell.WALL);
            }
        }
        return cells;
    }
}
