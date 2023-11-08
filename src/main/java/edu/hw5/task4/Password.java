package edu.hw5.task4;

/**
 * Предположим, что в целях безопасности вы требуете,
 * чтобы все пароли содержали хотя бы один из следующих символов
 * ~ ! @ # $ % ^ & * |
 * Напишите регулярное выражение, которое возвращает true тогда и только тогда,
 * когда пароль содержит один из требуемых символов.
 */

public class Password {
    public boolean isAllowedPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return password.matches(".*[~!@\\$%\\^&*\\|]");
    }
}
