package edu.hw5.task7;

/**
 * Напишите регулярные выражения для строк из алфавита {0, 1} (это важно и нужно использовать при решении):
 * - содержит не менее 3 символов, причем третий символ равен 0
 * - начинается и заканчивается одним и тем же символом
 * - длина не менее 1 и не более 3
 */

public class BinaryRegex {

    public boolean isContainsThreeSymbolsAndThirdIsZero(String input) {
        inputCheck(input);
        return input.matches("^[01]{2}0[01]+");
    }

    public boolean isFirstAndLastSymbolsAreTheSame(String input) {
        inputCheck(input);
        return input.matches("^(0|1)([0|1]*)(\\1)$");
    }

    public boolean isLengthMoreThanOneAndLessThanThree(String input) {
        inputCheck(input);
        return input.matches("^(0|1){1,3}$");
    }

    // Проверка на наличие строки и на то, что строка состоит только из "0" и "1"
    private void inputCheck(String input) {
        if (input == null || input.isEmpty() || !input.matches("[0|1]+")) {
            throw new IllegalArgumentException();
        }
    }
}
