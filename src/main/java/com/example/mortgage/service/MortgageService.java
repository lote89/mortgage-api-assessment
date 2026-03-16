package com.example.mortgage.service;

import com.example.mortgage.domain.MortgageRules;
import com.example.mortgage.model.MortgageCheckRequest;
import com.example.mortgage.model.MortgageCheckResponse;
import com.example.mortgage.model.MortgageRate;
import com.example.mortgage.util.MortgageCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MortgageService {

    private static final Logger log = LoggerFactory.getLogger(MortgageService.class);

    private final List<MortgageRate> rates;

    public MortgageService(List<MortgageRate> rates) {
        this.rates = rates;
    }

    public List<MortgageRate> getInterestRates() {
        log.info("Returning interest-rates");
        return rates;

    }

    public MortgageCheckResponse checkMortgage(MortgageCheckRequest request) {

        log.info("Received mortgage check request: income={}, loanValue={}",
                request.income(), request.loanValue());

        boolean feasible = MortgageRules.isMortgageFeasible(
                request.loanValue(), request.income(), request.homeValue()
        );

        double interestRate = rates.stream()
                .filter(r -> r.maturityPeriod() == request.maturityPeriod())
                .map(MortgageRate::interestRate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No interest rate found for maturity period"
                ));

        double monthlyCost = MortgageCalculator.calculateMonthlyPayment(
                request.loanValue(), interestRate, request.maturityPeriod()
        );
        log.info("Calculated monthly cost: {}", monthlyCost);

        return new MortgageCheckResponse(feasible, monthlyCost);
    }
}