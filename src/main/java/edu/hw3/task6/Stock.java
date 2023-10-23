package edu.hw3.task6;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private final String name;
    private final int price;

    public Stock(String name, int price) {
        checks(name, price);
        this.name = name;
        this.price = price;
    }

    // Проверк
    private void checks(String name, int price) {
        if (name == null || price < 0) {
            throw new IllegalArgumentException();
        }
    }

    // Красивый вывод
    @Override public String toString() {
        return name + ' ' + price;
    }

    // Сортировка по цене
    @Override
    public int compareTo(@NotNull Stock o) {
        return o.price - this.price;
    }

    // Для тестов
    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        return price == stock.price && Objects.equals(name, stock.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
