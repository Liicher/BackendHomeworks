package edu.hw3.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Задание 1
 * Шифр Атбаша - это метод шифрования,
 * при котором каждая буква слова
 * заменяется на свою "зеркальную"
 * букву в алфавите: A <=> Z; B <=> Y; C <=> X и т.д.
 * Создайте функцию, которая принимает строку и применяет к ней шифр.
 */
public class Task1 {
    private final List<Character> alphabet = alphabetGenerator();

    public String atbash(String input) {
        // Проверка на null
        if (input == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder result = new StringBuilder();
        List<Character> charList = stringToList(input);
        for (int i = 0; i < charList.size(); i++) {
            char letter = charList.get(i);
            // Проверка на то, что алфавит латинский
            // И шифрация и добавление к строке
            if (alphabet.contains(letter)) {
                int index = alphabet.indexOf(letter);
                result.append(alphabet.get(alphabet.size() - 1 - index));
            } else {
                result.append(letter);
            }
        }
        return result.toString();
    }

    // Метод для преобразования строки в список
    private List<Character> stringToList(String input) {
        List<Character> output = new ArrayList<>();
        for (char ch : input.toCharArray()) {
            output.add(ch);
        }
        return output;
    }

    // Метод для генерации списка-алфавита сразу обоих регистров
    // Регистры отзеркалены относительно середины
    private List<Character> alphabetGenerator() {
        Character[] letters = {'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E',
            'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', 'k', 'K', 'l', 'L',
            'm', 'M', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's',
            'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z'};
        List<Character> alphabetList = new ArrayList<>();
        Collections.addAll(alphabetList, letters);
        return alphabetList;
    }
}