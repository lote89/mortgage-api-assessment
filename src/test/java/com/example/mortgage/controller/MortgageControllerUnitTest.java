package com.example.mortgage.controller;

import com.example.mortgage.model.*;
import com.example.mortgage.service.MortgageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MortgageControllerUnitTest {

    private MortgageService service;
    private MortgageController controller;

    @BeforeEach
    void setup() {
        List<MortgageRate> rates = List.of(
                new MortgageRate(20, 3.5, LocalDateTime.now())
        );
        service = new MortgageService(rates);
        controller = new MortgageController(service);
    }

    @Test
    void testGetInterestRates() {
        var rates = controller.getRates();
        assertEquals(1, rates.size());
        assertEquals(20, rates.get(0).maturityPeriod());
    }

    @Test
    void testMortgageCheckFeasible() {
        MortgageCheckRequest request =
                new MortgageCheckRequest(50000, 20, 150000, 200000);

        MortgageCheckResponse response = controller.checkMortgage(request);
        assertTrue(response.feasible());
        assertTrue(response.monthlyCost() > 0);
    }

    @Test
    void testMortgageCheckNotFeasible() {
        MortgageCheckRequest request =
                new MortgageCheckRequest(30000, 20, 200000, 200000);

        MortgageCheckResponse response = controller.checkMortgage(request);
        assertFalse(response.feasible());
    }
}