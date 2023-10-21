package edu.project1;

import java.util.ArrayList;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;

public class InfoGenerator implements Preparation {
    private final static int MIN_LENGTH = 4;

    /**
     *      randomWord() - метод выбора случайного слова
     *      из заранее заготовленного "словаря"
     *      Возвращает список символов этого слова
     */
    @Override
    public @NotNull ArrayList<Character> randomWord() {
        String[] library = {"dictionary", "target", "library",
            "language", "smart", "laptop", "", "yes", "no"};

        // Проверка, что выбранное слово длинное больше MIN_LENGTH (четырех) символов
        String answer = null;
        while (answer == null || answer.length() < MIN_LENGTH) {
            answer = library[(int) (Math.random() * library.length)];
        }

        // Преобразование выбранного слова в список символов
        ArrayList<Character> answerWord = new ArrayList<>();
        for (char ch : answer.toCharArray()) {
            answerWord.add(ch);
        }
        return answerWord;
    }

    /**
     *      userAnswer() - метод создания списка символов с ответов пользователя
     */
    @Override
    public ArrayList<Character> userAnswer(ArrayList<Character> answer) {
        ArrayList<Character> userAnswer = new ArrayList<>();
        for (int i = 0; i < answer.size(); i++) {
            userAnswer.add(i, '*');
        }
        return userAnswer;
    }

    /**
     *      letters() - метод, создающий список с алфавитом
     */
    @Override
    public ArrayList<Character> letters() {
        Character[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        ArrayList<Character> allLetters = new ArrayList<>();
        Collections.addAll(allLetters, letters);
        return allLetters;
    }
}
