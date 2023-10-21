package edu.project1;

import java.util.Scanner;

class ConsoleHangman {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Messages MSG = new Messages();

    /**
     *      run() - метод старта сессии
     *      В цикле while пока не выполнится условие
     *      Проигрыша или Выигрыша
     */
    public void run() {
        Session session = new Session();
        String guess;
        while (session.checkAttempts() && !session.checkWinnable()) {
            printState(session);
            guess = SCANNER.nextLine();
            session.checkExit(guess);
            tryGuess(session, guess);
        }
        gameResult(session);
    }

    /**
     *      gameResult() - метод проверки
     *      Проигрыша или Выигрыша
     */
    private void gameResult(Session session) {
        if (session.checkWinnable()) {
            MSG.messageWin(session.getAnswerString());
        } else {
            MSG.messageLose(session.getAnswerString());
        }
    }

    /**
     *      tryGuess() - метод проверки, что
     *      Введенный символ один
     *      Введенный символ не использовался ранее
     */
    protected void tryGuess(Session session, String input) {
        if (input != null && input.length() == 1 && session.checkUserInputCharacter(input)) {
            session.guess(input);
        }
    }

    /**
     *      printState() - метод вывода состояния сессии в консоль
     */
    private void printState(Session session) {
        MSG.messageState(session.getUserAnswer().toString(), session.getAttempts(), session.getMaxAttempts());
    }
}
