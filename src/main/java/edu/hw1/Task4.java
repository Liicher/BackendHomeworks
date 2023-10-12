package edu.hw1;

public class Task4 {
    public String fixString(String input) {
        // Выполним проверку, что наша строка содержит хотя бы два элемента для "свапа"
        if (input.length() < 2) {
            return input;
        }

        // Создадим StringBuilder для редактирования строки
        StringBuilder sb = new StringBuilder();

        // Используем цикл с двойным шагом для изменения каждых двух элементов
        for (int i = 1; i < input.length(); i += 2) {
            sb.append(input.charAt(i));
            sb.append(input.charAt(i - 1));

            // Выполним проверку на то, остался ли у нас элемент без пары
            // Если да, то добавим его в конец строки
            if (input.length() - i == 2) {
                sb.append(input.charAt(input.length() - 1));
            }
        }

        // Вернем "исправленную" строку
        return sb.toString();
    }
}
