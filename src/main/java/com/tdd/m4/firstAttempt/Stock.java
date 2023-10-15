package com.tdd.m4.firstAttempt;

public class Stock {

    double price;
    int quantity;
    String name;

    public Stock(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public double totalValue() {
        return quantity * price;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("{ %s | Qty: %s | Price: %s | Value: %s }", name, quantity, price, quantity*price);
    }
}
