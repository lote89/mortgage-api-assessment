package com.example.mortgage.service;

import com.example.mortgage.model.MortgageCheckRequest;
import com.example.mortgage.model.MortgageCheckResponse;
import com.example.mortgage.model.MortgageRate;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MortgageServiceTest {

    private final MortgageService service =
            new MortgageService(
                    List.of(new MortgageRate(20, 3.5, LocalDateTime.now()))
            );

    @Test
    void mortgageShouldBeFeasible() {
        MortgageCheckRequest request =
                new MortgageCheckRequest(50000, 20, 150000, 200000);

        MortgageCheckResponse response = service.checkMortgage(request);

        assertTrue(response.feasible());
        assertTrue(response.monthlyCost() > 0);
    }

    @Test
    void mortgageShouldNotBeFeasible() {
        MortgageCheckRequest request =
                new MortgageCheckRequest(30000, 20, 200000, 200000);

        MortgageCheckResponse response = service.checkMortgage(request);

        assertFalse(response.feasible());
    }
}