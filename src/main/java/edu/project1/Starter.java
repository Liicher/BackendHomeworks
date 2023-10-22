package edu.project1;

import java.util.Scanner;

public class Starter {
    private final static Scanner SCANNER = new Scanner(System.in);

    /**
     * startMenu() - метод для вывода стартового сообщение на экран
     * Предоставляет пользователю выбор
     * "Начать игру" или "Выйти из игры"
     */
    public void startMenu() {
        Messages msg = new Messages();
        char input = ' ';
        msg.messageIntro();
        while (input != 'Q') {
            msg.messageStart();
            String str = SCANNER.nextLine().toUpperCase();
            // Добавил проверку на корректный ввод (не null и не больше одного символа)
            if (str.length() != 1) {
                msg.messageIncorrectInput();
                continue;
            }
            input = str.charAt(0);
            switch (input) {
                case 'Y' -> new ConsoleHangman().run();
                case 'Q' -> msg.messageExit();
                default -> msg.messageIncorrectInput();
            }
        }
        System.exit(0);
    }
}
