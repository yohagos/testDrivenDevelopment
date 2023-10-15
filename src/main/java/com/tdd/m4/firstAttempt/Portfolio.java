package com.tdd.m4.firstAttempt;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    List<Stock> stocks = new ArrayList<>();

    public double totalValue() {
        var result = stocks.isEmpty() ? 0 : stocks.stream().mapToDouble(Stock::totalValue).sum();
        return result;
    }

    public void add(Stock stock) {
        // if stock is already in the list,
        // merge quantity and price
        boolean update = stocks.stream().anyMatch(s -> s.getName() == stock.getName());
        if (update) {
            for (Stock s: stocks) {
                if (stock.getName() == s.getName()) {
                    s.quantity = s.quantity + stock.quantity;
                    s.price = (s.price + stock.price) / 2;
                }
            }
        } else {
            stocks.add(stock);
        }
    }

    public void printPortfolio() {
        stocks.forEach(System.out::println);
    }
}
