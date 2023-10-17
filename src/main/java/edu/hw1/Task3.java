package edu.hw1;

import java.util.Arrays;

public class Task3 {

    public boolean isNestable(int[] a1, int[] a2) throws IllegalArgumentException {
        // Выполним изначальную проверку на возможные "плохие данные"
        if (a1 == null || a2 == null || a1.length == 0 || a2.length == 0) {
            throw new IllegalArgumentException();
        }

        // Отсортируем получившиеся массивы (надеюсь не запрещено пользоваться встроенными функциями)
        Arrays.sort(a1);
        Arrays.sort(a2);

        //Вернем логический результат вложенности
        return ((a1[0] > a2[0]) && (a1[a1.length - 1] < a2[a2.length - 1]));
    }

}
