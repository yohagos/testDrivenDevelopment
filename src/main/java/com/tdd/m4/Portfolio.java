package com.tdd.m4;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {

    private final Map<String,Position> positions;

    public Portfolio() {
        positions = new HashMap<>();
    }
    public int getPositionCount() {
        return positions.size();
    }

    public void add(Position position) {
        String symbol = position.getStock().symbol();
        if (position.getQuantity() <= 0 || position.getPrice() <= 0)
            throw new RuntimeException("Quantity and Price cannot be below Zero");
        if (positions.containsKey(symbol)) {
            Position existingPosition = positions.get(symbol);
            int newQuantity = existingPosition.getQuantity() + position.getQuantity();
            double newAveragePrice =
                    (existingPosition.getQuantity() * existingPosition.getAveragePrice() +
                            position.getQuantity() * position.getAveragePrice()) / newQuantity;

            existingPosition.setQuantity(newQuantity);
            existingPosition.setAveragePrice(newAveragePrice);
            /*  // my implementation attempt
                var newQuantity = oldPosition.getQuantity() + position.getQuantity();
                var newPrice = calculateNewPrice(oldPosition.getPrice(), position.getPrice());
                positions.replace(position.getStock().symbol(), new Position(position.getStock(), newQuantity, newPrice));
            */
        } else {
            positions.put(position.getStock().symbol(), position);
        }
    }

    public void removePositionCompletely(String symbol) {
        if (!positions.containsKey(symbol))
            throw new RuntimeException("Stock does not exist");
        positions.remove(symbol);
    }

    public Position getPosition(String symbol) {
        return positions.get(symbol);
    }

    private double calculateNewPrice(double oldPrice, double newPrice) {
        return (oldPrice + newPrice) / 2;
    }

    public double getTotalValue() {
        return positions.values().stream()
                .mapToDouble(Position::getValue)
                .sum();
    }

    public void print() {
        positions.values().forEach(System.out::println);

        System.out.println("###################################");
        System.out.println(getTotalValue());
    }
}
