package com.example.mortgage.util;

public final class MortgageCalculator {

    private MortgageCalculator() {}

    public static double calculateMonthlyPayment(double loanValue, double interestRate, int maturityPeriodYears) {
        double monthlyRate = interestRate / 100 / 12;
        int months = maturityPeriodYears * 12;
        double payment = loanValue *
                (monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);
        return round(payment);
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}