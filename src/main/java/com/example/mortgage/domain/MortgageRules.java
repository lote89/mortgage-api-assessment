package com.example.mortgage.domain;

public final class MortgageRules {

    private static final int INCOME_MULTIPLIER = 4;

    private MortgageRules() {}

    public static boolean isWithinIncomeLimit(double loanValue, double income) {
        return loanValue <= income * INCOME_MULTIPLIER;
    }

    public static boolean isWithinHomeValue(double loanValue, double homeValue) {
        return loanValue <= homeValue;
    }

    public static boolean isMortgageFeasible(double loanValue, double income, double homeValue) {
        return isWithinIncomeLimit(loanValue, income) &&
                isWithinHomeValue(loanValue, homeValue);
    }
}