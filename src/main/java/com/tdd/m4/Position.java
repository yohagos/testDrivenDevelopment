package com.tdd.m4;

public class Position {
    private Stock stock;
    private int quantity;
    private double price;

    public Position(Stock stock, int qty, double px) {
        this.stock = stock;
        quantity = qty;
        price = px;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getValue() {
        return getQuantity() * getPrice();
    }
}
