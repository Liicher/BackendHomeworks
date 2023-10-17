package edu.hw1;

// Не уверен, нужно ли было разбивать на отдельные методы
public class Task5 {
    private static final int TEN = 10;

    public boolean isPalindromeDescendant(int input) {
        // Считаем что числа меньше 11 не могут являться палиндромом
        if (input <= TEN) {
            return false;
        }

        // Логическая переменная для возврата результата
        boolean result = true;
        // Спарсим наше число в строку
        String inputString = Integer.toString(input);
        // Инициализируем переменную для хранения индекса последнего символа числа
        int end = inputString.length() - 1;

        // Проверка на палиндром
        for (int start = 0; start < inputString.length() / 2; start++) {
            // Выполним проверку начального и конечного символов
            // В случае несовпадения, выходим из цикла
            if (inputString.charAt(start) == inputString.charAt(end)) {
                end--;
            } else {
                result = false;
                break;
            }
        }

        // Если палиндром, то возвращаем результат
        if (result) {
            return true;
        }

        // Высчитываем потомка
        // Разделим число посимвольно
        String[] str = inputString.split("");
        StringBuilder sb = new StringBuilder();

        // В цикле с двойным шагом складываем пары чисел
        for (int i = 1; i < inputString.length(); i += 2) {
            sb.append(Integer.parseInt(str[i]) + Integer.parseInt(str[i - 1]));

            // Выполним проверку на то, остался ли у нас элемент без пары
            // Если да, то добавим его в конец строки
            if (inputString.length() - i == 2) {
                sb.append(inputString.charAt(inputString.length() - 1));
            }
        }

        // Рекурсивно вызываем метод для потомка
        return isPalindromeDescendant(Integer.parseInt(sb.toString()));
    }
}
