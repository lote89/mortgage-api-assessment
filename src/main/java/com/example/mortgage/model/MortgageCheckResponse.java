package com.example.mortgage.model;

public record MortgageCheckResponse(
        boolean feasible,
        double monthlyCost
) {}