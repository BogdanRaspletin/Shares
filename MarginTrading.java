package Shares;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MarginTrading {

    public static void main(String[] args) {

        double creditPercent = 0.229;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input number of days: ");
        int days = scanner.nextInt();
        if(days == 1) days = 365;
        System.out.println("Days = " + days);

        double realPercent = countPercent(creditPercent, days);

        System.out.print("Input number of shares: ");
        int shares = scanner.nextInt();
        if(shares == 1) shares = 106;
        System.out.println(shares + " shares");

        System.out.print("Input share price: ");
        int sharePrice = scanner.nextInt();
        if(sharePrice == 1) sharePrice = 8051;
        System.out.println(sharePrice);

        System.out.print("Input share growth (%): ");
        double shareGrowth = scanner.nextDouble();
        if(shareGrowth == 1) shareGrowth = 12;
        if(shareGrowth == 2) shareGrowth = 0;
        if(shareGrowth == 3) shareGrowth = 20;
        System.out.println("Shares will grow by " + shareGrowth + "%");

        System.out.println();

        int debt = countDebt(shares, sharePrice);
        double finalDebt = debt * realPercent;

        countings(shares, sharePrice, days, debt, finalDebt, shareGrowth);
    }

    public static int countDebt(int shares, int sharePrice) {
        return shares * sharePrice;
    }

    public static double countPercent(double yearlyPercent, int days) {
        double dailyPercent = yearlyPercent/365;
        double realPercent = 1.0f;

        for (int i = 0; i < days; i++) {
            realPercent *= (1 + dailyPercent);
        }
        System.out.println("Real percent: " + realPercent);
        return realPercent;
    }


    public static void countings(int shares, int sharePrice, int days, int debt, double finalDebt, double shareGrowth) {

        DecimalFormat dF = new DecimalFormat("#,###");

        int otsechka = 1;
        if (days >= 183 && days < 365) otsechka = 2;
        if (days >= 365) otsechka = 3;
        System.out.println("Количество отсечек: " + otsechka);

        double debtIncrease = finalDebt - debt;
        double dividends = shares * 498 * otsechka * 0.87;
        System.out.println("Debt increase: " + dF.format(debtIncrease));
        System.out.println("Dividends will be: " + dF.format(dividends));
        int initialValue = shares * sharePrice;

        shareGrowth = shareGrowth / 365 * days;
        System.out.println("Shares will grow by: " + shareGrowth);
        shareGrowth /= 100; // перевод процентов в десятичные цифры
        shareGrowth += 1;


        double finalValue = initialValue * shareGrowth; // это еще аккуратные оценки на самом деле!
        double valueIncrease = finalValue - initialValue;

        System.out.println("Value of shares increased for " + dF.format(valueIncrease));
        System.out.println();

        double balance = dividends + valueIncrease - debtIncrease;
        System.out.println("Profit: " + dF.format(balance));

        double newSharePrice = sharePrice * shareGrowth;
        System.out.println("New share price will be " + dF.format(newSharePrice));
        System.out.println();
        System.out.println();
    }
}
