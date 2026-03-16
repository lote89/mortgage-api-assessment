package com.example.mortgage.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MortgageCheckRequest(
        @NotNull
        @Min(value = 1, message = "income must be greater than 0")
        double income,

        @NotNull
        @Min(value = 1, message = "maturityPeriod must be greater than 0")
        int maturityPeriod,

        @NotNull
        @Min(value = 1, message = "loanValue must be greater than 0")
        double loanValue,

        @NotNull
        @Min(value = 1, message = "homeValue must be greater than 0")
        double homeValue
) {}