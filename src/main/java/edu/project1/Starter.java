package edu.project1;

import java.util.Scanner;

public class Starter {
    private final static Scanner SCANNER = new Scanner(System.in);

    /**
     *      startMenu() - метод для вывода стартового сообщение на экран
     *      Предоставляет пользователю выбор
     *      "Начать игру" или "Выйти из игры"
     */
    public void startMenu() {
        Messages msg = new Messages();
        msg.messageIntro();
        while (true) {
            msg.messageStart();
            char input = SCANNER.nextLine().toUpperCase().charAt(0);
            switch (input) {
                case 'Y':
                    new ConsoleHangman().run();
                    break;
                case 'Q':
                    System.exit(0);
                    break;
                default:
                    msg.messageIncorrectInput();
            }
        }
    }
}
