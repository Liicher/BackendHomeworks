package edu.hw1;

import java.util.Arrays;

public class Task6 {
    // Так как наш нужно посчитать число шагов, а вызов функции производится рекурсивно
    // переменная count будет объявлена статически
    public static int count = 0;
    public static final int K = 6174;
    public static final int ALIKE = 1111;
    public static final int THOUSAND = 1000;
    public static final int TENTH = 10000;

    public int countK(int input) throws IllegalArgumentException {
        // Выполним проверки на данные-исключения (ХХХХ ; <= 1000 ; >= 10000)
        if ((input % ALIKE == 0) || (input <= THOUSAND) || (input >= TENTH)) {
            throw new IllegalArgumentException();
        }

        // Спарсим наше число в строку
        String inputString = Integer.toString(input);
        // Разделим его посимвольно для будущей перестановки цифр
        String[] strArray = inputString.split("");
        // Отсортируем полученный массив цифр
        Arrays.sort(strArray);

        // Создадим две строки для минимального и максимального чисел
        StringBuilder minSb = new StringBuilder();
        StringBuilder maxSb = new StringBuilder();

        // С помощью цикла запишем эти числа в строки
        for (int i = 0; i < strArray.length; i++) {
            minSb.append(strArray[i]);
            maxSb.append(strArray[strArray.length - i - 1]);
        }

        // Спарсим строки в целые числа и посчитаем результат вычисления
        int min = Integer.parseInt(minSb.toString());
        int max = Integer.parseInt(maxSb.toString());
        int result = max - min;

        // Повысим счетчик
        count++;

        // Проверим, равен ли результат постоянной Капрекара
        // Если нет, то вызовем метод рекурсивно
        if (result != K) {
            count = countK(result);
        }

        // Если равен, то вернем count
        return count;
    }
}
