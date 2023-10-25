package edu.hw3.task2;

import edu.hw3.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Напишите функцию, которая группирует строку в кластеры,
 * заключенные в скобки. Каждый кластер должен быть сбалансированным.
 */

public class Task2 {
    private final Util util = new Util();
    private final static char ROUND_OPEN_BRACKET = '(';
    private final static char ROUND_CLOSE_BRACKET = ')';

    // Знаю что не лучшее решение, но всегда нравилось решать задачи со скобками с помощью стека
    public String[] clusterize(String input) {
        if (input == null || input.isEmpty() || input.charAt(0) == ROUND_CLOSE_BRACKET) {
            throw new IllegalArgumentException();
        }
        StringBuilder result = new StringBuilder();
        List<String> output = new ArrayList<>();
        Stack<Character> brackets = new Stack<>();
        List<Character> inputList = util.stringToList(input);

        // Проходимся по всему списку элементов строки
        for (Character character : inputList) {
            // С помощью стека отслеживаем балансировку скобок
            if (character == ROUND_OPEN_BRACKET) {
                result.append(ROUND_OPEN_BRACKET);
                brackets.push(ROUND_OPEN_BRACKET);
            } else if (character == ROUND_CLOSE_BRACKET) {
                result.append(ROUND_CLOSE_BRACKET);
                brackets.pop();

                // В случае полного баланса скобок, добавить строку в список
                if (brackets.isEmpty()) {
                    output.add(result.toString());
                    result.delete(0, result.length());
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        return output.toArray(new String[0]);
    }
}
