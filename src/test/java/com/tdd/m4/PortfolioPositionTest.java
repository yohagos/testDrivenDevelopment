package com.tdd.m4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PortfolioPositionTest {

    @Test
    void emptyPortfolio_zeroPositions() {
        var port = new Portfolio();
        Assertions.assertEquals(0, port.getAllPositions().size());
    }

    @Test
    void portfolioWithOnePosition_ReturnsThatPosition() {
        var portfolio = new Portfolio();
        String symbol = "MSFT";
        portfolio.add(new Position(new Stock(symbol), 10, 260));
        Assertions.assertEquals(1, portfolio.getAllPositions().size());

        Assertions.assertEquals(10, portfolio.getPosition(symbol).getQuantity());
        Assertions.assertEquals(260, portfolio.getPosition(symbol).getPrice());
        Assertions.assertEquals(2600, portfolio.getPosition(symbol).getValue());
    }

    @Test
    void portfolioWithTwoDifferentPositions_ReturnsThosePositions() {
        var portfolio = new Portfolio();

        String microsoft = "MSFT";
        String apple = "AAPL";

        portfolio.add(position(microsoft, 10, 260));
        portfolio.add(position(apple, 2, 150));

        Assertions.assertEquals(2, portfolio.getAllPositions().size());

        var microsoftPosition = portfolio.getPosition(microsoft);
        Assertions.assertEquals(10, microsoftPosition.getQuantity());
        Assertions.assertEquals(260, microsoftPosition.getPrice());
        Assertions.assertEquals(2600, microsoftPosition.getValue());

        var applePosition = portfolio.getPosition(apple);
        Assertions.assertEquals(2, applePosition.getQuantity());
        Assertions.assertEquals(150, applePosition.getPrice());
        Assertions.assertEquals(300, applePosition.getValue());
    }

    private static Position position(String symbol, int qty, double px) {
        return new Position(new Stock(symbol), qty, px);
    }
}
