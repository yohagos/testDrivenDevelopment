package com.tdd.m4.firstAttempt;

public class PortfolioTestDrive {

    public static void main(String... args) {
        var portfolio = new Portfolio();
        portfolio.add(new Stock("AAPL", 1, 140));
        portfolio.add(new Stock("AAPL", 3, 120));
        portfolio.add(new Stock("MSFT", 10, 200));

        portfolio.printPortfolio();

        System.out.println("=====================");
        System.out.println(portfolio.totalValue());
    }
}
