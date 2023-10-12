package edu.hw1;

public class Task7 {

    public int bitwiseShift(int n, int shift, boolean direction) throws IllegalArgumentException {

        // В условии сказано, что
        // n -- целое положительное число, поэтому делаем проверку
        // shift -- размер сдвига, из логики не может быть меньше единицы
        if (n < 0 || shift < 1) {
            throw new IllegalArgumentException();
        }

        // Проверим на нулевое или единичное значение, так как циклический сдвиг в таком случае не имеет смысла
        if (n == 0 || n == 1) {
            return n;
        }

        // Преобразуем наше входное значение в бинарную строку
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(n));

        // По значению логической переменной выберем направление сдвига и вызовем соответствующие методы
        // True - Left ; False - Right
        if (direction) {
            for (int i = 0; i < shift; i++) {
                sb = new StringBuilder(leftShift(String.valueOf(sb)));
            }
        } else {
            for (int i = 0; i < shift; i++) {
                sb = new StringBuilder(rightShift(String.valueOf(sb)));
            }
        }

        // Парсим получившуюся строку в int и возвращаем ее
        return Integer.parseInt(sb.toString(), 2);
    }

    // Метод для единичного циклического сдвига влево
    public String leftShift(String binary) {
        StringBuilder resultString = new StringBuilder(binary);
        char leftChar = binary.charAt(0);
        resultString.deleteCharAt(0);
        resultString.append(leftChar);
        return resultString.toString();
    }

    // Метод для единичного циклического сдвига вправо
    public String rightShift(String binary) {
        StringBuilder resultString = new StringBuilder(binary);
        char rightChar = binary.charAt(binary.length() - 1);
        resultString.deleteCharAt(binary.length() - 1);
        resultString.insert(0, rightChar);
        return resultString.toString();
    }

    public int rotateRight(int n, int shift) {
        return bitwiseShift(n, shift, false);
    }

    public int rotateLeft(int n, int shift) {
        return bitwiseShift(n, shift, true);
    }
}
