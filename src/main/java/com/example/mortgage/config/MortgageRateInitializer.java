package com.example.mortgage.config;

import com.example.mortgage.model.MortgageRate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class MortgageRateInitializer {

    @Bean
    public List<MortgageRate> mortgageRates() {
        return List.of(
                new MortgageRate(10, 3.2, LocalDateTime.now()),
                new MortgageRate(20, 3.8, LocalDateTime.now()),
                new MortgageRate(30, 4.2, LocalDateTime.now())
        );
    }
}