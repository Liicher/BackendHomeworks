package edu.project1;

import java.util.List;

class Session {
    private final InfoGenerator infoGenerator = new InfoGenerator();                    // Класс для генерации данных
    private final List<Character> answer = infoGenerator.randomWord();                  // Загаданное слово
    private final String answerString = answer.toString();                              // Хранение ответа в виде строки
    private final List<Character> userAnswer = infoGenerator.userAnswer(answer);        // Ответы игрока
    private final List<Character> allLetters = infoGenerator.letters();                 // Алфавит
    private final int maxAttempts = 5;                                                  // Максимальное кол-во попыток
    private int attempts = 0;                                                           // Используемые попытки

    /**
     *      Проверки на различные состояния игры
     *      checkUserInputCharacter - проверка, что символ еще не использовался
     *      checkAttempts - проверка, на количество ошибок
     *      checkWinnable - проверка, на выигрышную ситуацию
     *      checkExit - проверка, на команду выхода из игры
    **/
    public boolean checkUserInputCharacter(String input) {
        return allLetters.contains(input.charAt(0));
    }

    public boolean checkAttempts() {
        return this.attempts < this.maxAttempts;
    }

    public boolean checkWinnable() {
        return this.userAnswer.toString().equals(answerString);
    }

    public void checkExit(String input) {
        if (input.equals("0")) {
            System.exit(0);
        }
    }

    /**
     *      Метод guess
     *      Получает, символ пользователя
     *      Находит, все эти символы в загаданном слове
     *      И меняет '*' в ответе по индексам загаданного слова
     **/
    public void guess(String input) {
        char answerChar = input.charAt(0);
        if (this.answer.contains(answerChar)) {
            while (this.answer.contains(answerChar)) {
                int index = this.answer.indexOf(answerChar);
                this.answer.set(index, '*');
                this.userAnswer.set(index, answerChar);
            }
        } else {
            this.attempts++;
        }
        // Делаем удаление по индексу
        this.allLetters.remove(this.allLetters.indexOf(answerChar));
    }

    public String getAnswerString() {
        return answerString;
    }

    public List<Character> getUserAnswer() {
        return userAnswer;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getAttempts() {
        return attempts;
    }
}

