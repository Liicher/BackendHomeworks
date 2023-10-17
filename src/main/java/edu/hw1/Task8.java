package edu.hw1;

public class Task8 {
    // Переменная для размера матрицы
    public static final byte LEN = 8;
    public static final byte EMPTY = 0;
    public static final byte KNIGHT = 1;

    public boolean knightBoardCapture(int[][] board) throws IllegalArgumentException {
        // Две проверки на соблюдение размеров квадратной матрицы 8х8
        if (board == null || board.length != LEN) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < LEN; i++) {
            if (board[i].length != LEN) {
                throw new IllegalArgumentException();
            }

            // Проверка, что в нашей матрице содержатся только нули и единицы
            for (int j = 0; j < LEN; j++) {
                if (board[i][j] != EMPTY && board[i][j] != KNIGHT) {
                    throw new IllegalArgumentException();
                }
            }
        }

        // Логическая переменная для хранения результата
        boolean result;
        // Цикл для пробега по матрице
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                // В случае если поле пустое, продолжаем
                if (board[i][j] == EMPTY) {
                    continue;
                }

                // Если на поле "конь", то вызываем метод проверки поля
                result = checkKnightPosition(board, i, j);

                // Если "конь" смог захватить другого "коня", то выходим возвращаем false
                if (!result) {
                    return false;
                }
            }
        }
        return true;
    }

    // Метод для проверки позиции "коня"
    private boolean checkKnightPosition(int[][] board, int x, int y) {
        boolean result = true;
        // Если "конь" прижат к верхним двум полям
        if (x > 1) {
            result = checkUpPosition(board, x, y);
        }

        // Если "конь" прижат к нижним двум полям
        if (x < LEN - 2 && result) {
            result = checkDownPosition(board, x, y);
        }

        // Если "конь" прижат к левым двум полям
        if (y > 1 && result) {
            result = checkLeftPosition(board, x, y);
        }

        // Если "конь" прижат к правым двум полям
        if (y < LEN - 2 && result) {
            result = checkRightPosition(board, x, y);
        }
        return result;
    }

    // Проверка верхних полей относительно "коня"
    private boolean checkUpPosition(int[][] board, int x, int y) {
        switch (y) {
            case 0:
                return (board[x - 2][y + 1] == EMPTY);
            case LEN - 1:
                return (board[x - 2][y - 1] == EMPTY);
            default:
                return (board[x - 2][y - 1] == EMPTY && board[x - 2][y + 1] == EMPTY);
        }
    }

    // Проверка нижних полей относительно "коня"
    private boolean checkDownPosition(int[][] board, int x, int y) {
        switch (y) {
            case 0:
                return (board[x + 2][y + 1] == EMPTY);
            case LEN - 1:
                return (board[x + 2][y - 1] == EMPTY);
            default:
                return (board[x + 2][y - 1] == EMPTY && board[x + 2][y + 1] == EMPTY);
        }
    }

    // Проверка левых полей относительно "коня"
    private boolean checkLeftPosition(int[][] board, int x, int y) {
        switch (x) {
            case 0:
                return (board[x + 1][y - 2] == EMPTY);
            case LEN - 1:
                return (board[x - 1][y - 2] == EMPTY);
            default:
                return (board[x + 1][y - 2] == EMPTY && board[x - 1][y - 2] == EMPTY);
        }
    }

    // Проверка правых полей относительно "коня"
    private boolean checkRightPosition(int[][] board, int x, int y) {
        switch (x) {
            case 0:
                return (board[x + 1][y + 2] == EMPTY);
            case LEN - 1:
                return (board[x - 1][y + 2] == EMPTY);
            default:
                return (board[x + 1][y + 2] == EMPTY && board[x - 1][y + 2] == EMPTY);
        }
    }
}
