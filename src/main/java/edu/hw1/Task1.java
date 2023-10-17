package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task1 {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final int SECONDS = 60;

    public int minutesToSeconds(String time) {
        // Переменная для вывода в случая некорректного ввода
        int res = -1;

        // Проверка на случай ввода null
        if (time == null) {
            return res;
        }

        // Передадим нашу входную строку, убрав возможные лишние пробелы в начале и конце строки
        String inputStr = time.trim();

        // Создадим массив строк, разделив нашу строку по символу ":"
        String[] str = inputStr.split(":");

        // Инициализируем целочисленные переменные для минут и секунд
        int min;
        int sec;

        // Используем блок try-catch для случая, если в строке были переданы ни числовые значения
        try {

            // Убедимся что было передано именно два значения (минуты и секунды), иначе вернем "-1"
            if (str.length != 2) {
                return res;
            }

            // Парсим нам массив в наши целочисленные переменные
            min = Integer.parseInt(str[0]);
            sec = Integer.parseInt(str[1]);

            // Если происходит "NumberFormatException", то показываем сообщение об ошибке и возвращаем "-1"
        } catch (NumberFormatException e) {
            LOGGER.info("EXCEPTION: " + e);
            return res;
        }

        // Проверка, что диапазон секунд входит в реальные рамки времени (<60)
        if (sec < SECONDS) {
            res = min * SECONDS + sec;
        }

        // Возвращаем получившийся результат
        return res;
    }
}
