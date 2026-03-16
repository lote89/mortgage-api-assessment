package com.example.mortgage.controller;

import com.example.mortgage.model.MortgageCheckRequest;
import com.example.mortgage.model.MortgageCheckResponse;
import com.example.mortgage.model.MortgageRate;
import com.example.mortgage.service.MortgageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MortgageController {

    private final MortgageService service;

    public MortgageController(MortgageService service) {
        this.service = service;
    }

    @GetMapping("/interest-rates")
    public List<MortgageRate> getRates() {
        return service.getInterestRates();
    }

    @PostMapping("/mortgage-check")
    public MortgageCheckResponse checkMortgage(@Valid @RequestBody MortgageCheckRequest request) {
        return service.checkMortgage(request);
    }
    @GetMapping("/")
    public String home() {
        return "Mortgage API is running. Available endpoints: /api/interest-rates, /api/mortgage-check";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}