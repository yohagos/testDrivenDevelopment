package com.tdd.m4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {

    private final Map<String,Position> positions;

    public Portfolio() {
        positions = new HashMap<>();
    }
    public Map<String, Position> getAllPositions() {
        return positions;
    }

    public void add(Position position) {
        positions.put(position.getStock().symbol(), position);
    }

    public Position getPosition(String symbol) {
        return positions.get(symbol);
    }
}
