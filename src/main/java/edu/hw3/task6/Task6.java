package edu.hw3.task6;

import java.util.PriorityQueue;

/**
 * Реализуйте класс:
 * Внутри реализации используйте PriorityQueue для сортировки акций по цене.
 */
public class Task6 implements StockMarket {
    private final PriorityQueue<Stock> stocks = new PriorityQueue<>();

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }
}

