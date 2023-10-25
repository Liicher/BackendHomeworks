package edu.hw3.task1;

import edu.hw3.Util;
import java.util.List;
import java.util.Set;

/**
 * Задание 1
 * Шифр Атбаша - это метод шифрования,
 * при котором каждая буква слова
 * заменяется на свою "зеркальную"
 * букву в алфавите: A <=> Z; B <=> Y; C <=> X и т.д.
 * Создайте функцию, которая принимает строку и применяет к ней шифр.
 */
public class Task1 {
    private final Set<Character> alphabet = alphabetGenerator();
    private final Util util = new Util();

    public String atbash(String input) {
        // Проверка на null
        if (input == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder result = new StringBuilder();
        List<Character> charList = util.stringToList(input);
        for (Character letter : charList) {
            // Проверка на то, что алфавит латинский
            // И шифрация и добавление к строке
            if (alphabet.contains(letter)) {
                char ch = (char) ('Z' - Character.toUpperCase(letter) + 'A');
                result.append(Character.isUpperCase(letter) ? ch : Character.toLowerCase(ch));
            } else {
                result.append(letter);
            }
        }
        return result.toString();
    }

    // Метод для генерации set-алфавита сразу обоих регистров
    // Храним все элементы латинского алфавита
    private Set<Character> alphabetGenerator() {
        Character[] letters = {'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E',
            'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', 'k', 'K', 'l', 'L',
            'm', 'M', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's',
            'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z'};
        return Set.of(letters);
    }
}
