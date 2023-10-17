package com.tdd.m4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PortfolioPositionTest {
    String microsoft = "MSFT";


    @Test
    void emptyPortfolio_zeroPositions() {
        var port = new Portfolio();
        Assertions.assertEquals(0, port.getPositionCount());
    }

    @Test
    void portfolioWithOnePosition_ReturnsThatPosition() {
        var portfolio = new Portfolio();

        portfolio.add(new Position(new Stock(microsoft), 10, 260));
        Assertions.assertEquals(1, portfolio.getPositionCount());

        Assertions.assertEquals(10, portfolio.getPosition(microsoft).getQuantity());
        Assertions.assertEquals(260, portfolio.getPosition(microsoft).getPrice());
        Assertions.assertEquals(2600, portfolio.getPosition(microsoft).getValue());
    }

    @Test
    void portfolioWithTwoDifferentPositions_ReturnsThosePositions() {
        var portfolio = new Portfolio();

        String apple = "AAPL";

        portfolio.add(position(microsoft, 10, 260));
        portfolio.add(position(apple, 2, 150));

        Assertions.assertEquals(2, portfolio.getPositionCount());

        var microsoftPosition = portfolio.getPosition(microsoft);
        Assertions.assertEquals(10, microsoftPosition.getQuantity());
        Assertions.assertEquals(260, microsoftPosition.getPrice());
        Assertions.assertEquals(2600, microsoftPosition.getValue());

        var applePosition = portfolio.getPosition(apple);
        Assertions.assertEquals(2, applePosition.getQuantity());
        Assertions.assertEquals(150, applePosition.getPrice());
        Assertions.assertEquals(300, applePosition.getValue());
    }

    @Test
    void portfolioWithSameStock_ReturnsOnePosition() {
        var portfolio = new Portfolio();

        portfolio.add(position(microsoft, 1, 240));
        portfolio.add(position(microsoft, 1, 220));

        Assertions.assertEquals(230, portfolio.getPosition(microsoft).getAveragePrice());
        //Assertions.assertEquals(1, portfolio.getAllPositions().size());
        //Assertions.assertEquals(15, portfolio.getPosition(microsoft).getQuantity());
        //Assertions.assertEquals(3450, portfolio.getPosition(microsoft).getValue());
    }

    @Test
    public void portfolioWithSameStock_ReturnsCorrectPositionValue() {
        var portfolio = new Portfolio();

        portfolio.add(position(microsoft, 2, 240));
        portfolio.add(position(microsoft, 1, 220));

        double expected = 2 * 240 + 220;
        Assertions.assertEquals(expected, portfolio.getPosition(microsoft).getValue());
    }

    private static Position position(String symbol, int qty, double px) {
        return new Position(new Stock(symbol), qty, px);
    }

    @Test
    void complexPortfolio_ReturnsCorrectTotalValue() {
        var portfolio = new Portfolio();

        portfolio.add(position(microsoft, 1, 260));
        portfolio.add(position(microsoft, 2, 250));

        portfolio.add(position("AAPL", 5, 90));
        portfolio.add(position("AAPL", 10, 80));

        portfolio.add(position("ORCL", 100, 80));

        Assertions.assertEquals(3, portfolio.getPositionCount());
        Assertions.assertEquals(10010, portfolio.getTotalValue());
    }

    @ParameterizedTest
    @MethodSource("positionToAdd")
    void portfolioWithDifferentStock_ReturnsCorrectPositionCount(int quantity, Position[] positions) {
        var portfolio = new Portfolio();
        for (Position p: positions)
            portfolio.add(p);
        Assertions.assertEquals(quantity, portfolio.getPositionCount());
    }

    public static Stream<Arguments> positionToAdd() {
        Position[] firstPositions = {new Position(new Stock("MSFT"), 5, 200)};
        Position[] secondPositions = {
                                    new Position(new Stock("MSFT"), 5, 200),
                                    new Position(new Stock("MSFT"), 4, 250),
                                    new Position(new Stock("AAPL"), 17, 90)
        };
        return Stream.of(
                Arguments.of(1, firstPositions),
                Arguments.of(2, secondPositions)
        );
    }

    @Test
    void controlsPortfolioThrowsException() {
        var portfolio = new Portfolio();

        Assertions.assertThrows(RuntimeException.class,() -> portfolio.add(position("XWDS", -5, 213)));
        Assertions.assertThrows(RuntimeException.class,() -> portfolio.add(position("XWDS", 5, 0)));
    }

    @Test
    void controlRemovingPositionFromPortfolio() {
        var portfolio = new Portfolio();
        portfolio.add(position(microsoft, 2, 240));
        portfolio.add(position(microsoft, 1, 220));
        portfolio.add(position("test", 11, 1100));

        portfolio.removePositionCompletely(microsoft);
        Assertions.assertEquals(1, portfolio.getPositionCount());
    }

    @Test
    void controlRemovingPositionFromPortfolio_ThrowsException() {
        var portfolio = new Portfolio();

        portfolio.add(position("test", 11, 1100));

        Assertions.assertThrows(RuntimeException.class, () -> portfolio.removePositionCompletely(microsoft));
    }


    /*
    * TODO
    *  1) Parameterized Test            ### completed
    *   - One Parameterized Test for counts tests
    *   - Move all assertions of getPositionCount() in all tests to the new parameterized Test
    *  2) ZOMBIES                       ### completed
    *   - None negative test in which Exceptions are checked
    *  3) RemoveTest                    ### completed
    *   - If you can buy stock, you also should be able to remove a whole stock or a specific quantity
    *   - Calculate then the new totalValue and AveragePrice
    *  4) MoneyAPI (Bigger challenge)
    *   - primitive Data Type double will have problems to calculate complex decimal numbers
    *   - for rounding and understands currencies
    *   - github.com/JavaMoney/jsr354-api -> library as MoneyAPI
    * */
}
