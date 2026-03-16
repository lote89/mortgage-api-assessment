package com.example.mortgage.exception;

import java.time.LocalDateTime;

public record MortgageError(
        int status,
        String error,
        String message
) {}