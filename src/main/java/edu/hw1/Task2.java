package edu.hw1;

public class Task2 {
    private static final int TEN = 10;

    public int countDigits(int input) {
        // Заводим счетчик с единицы, так как любое число изначально имеет хотя бы одну цифру
        int count = 1;
        // Преобразуем наше число в положительное
        int num = Math.abs(input);

        // Выполняем деление на 10, каждый раз отбрасывая младший разряд
        // Каждый цикл к счетчику прибавляем единицу
        while (num >= TEN) {
            num /= TEN;
            count++;
        }

        // Возвращаем получившийся результат
        return count;
    }
}
