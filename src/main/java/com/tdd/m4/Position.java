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

    @Override
    public String toString() {
        return String.format("{ Stock: %s | Quantity: %s | Price: %s | Value: %s }", stock, quantity, price, getValue());
    }

    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }

    public double getAveragePrice() {
        return this.price;
    }

    public void setAveragePrice(double newAveragePrice) {
        this.price = newAveragePrice;
    }
}
