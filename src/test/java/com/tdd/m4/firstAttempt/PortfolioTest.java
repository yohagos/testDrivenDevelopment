package com.tdd.m4.firstAttempt;

import com.tdd.m4.firstAttempt.Portfolio;
import com.tdd.m4.firstAttempt.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PortfolioTest {

    @Test
    public void emptyPortfolio_hasZeroValue() {
        var portfolio = new Portfolio();
        Assertions.assertEquals(0, portfolio.totalValue());
    }

    @Test
    void portfolioWithOneStock_calculateTotalValue() {
        String name = "MSFT";
        int quantity = 10;
        double price = 260;
        var portfolio = new Portfolio();
        portfolio.add(new Stock(name, quantity, price));
        Assertions.assertEquals(2600, portfolio.totalValue());
    }

    @Test
    void portfolioWithMultipleStocks_calculateTotalValue() {
        // First stock
        int microsoftQuantity = 10;
        double microsoftPrice = 260;
        double microsoftValue = microsoftPrice * microsoftQuantity;

        // Second stock
        int appleQuantity = 1;
        double applePrice = 150;
        double appleValue = applePrice * appleQuantity;

        var portfolio = new Portfolio();
        portfolio.add(new Stock("MSFT", microsoftQuantity, microsoftPrice));
        portfolio.add(new Stock("AAPL", appleQuantity, applePrice));
        Assertions.assertEquals(microsoftValue + appleValue, portfolio.totalValue());
    }

    @Test
    void portfolioWithAddedStockAtDifferentPrice_calculateTotalValue() {
        // First stock entry
        int appleQuantity = 1;
        double applePrice = 150;
        double appleValue = applePrice * appleQuantity;

        // Second stock entry
        int appleQuantityTwo = 1;
        double applePriceTwo = 150;
        double appleValueTwo = applePriceTwo * appleQuantityTwo;

        var portfolio = new Portfolio();
        portfolio.add(new Stock("AAPL", appleQuantity, applePrice));
        portfolio.add(new Stock("AAPL", appleQuantityTwo, applePriceTwo));

        Assertions.assertEquals(appleValue + appleValueTwo, portfolio.totalValue());
    }
}
