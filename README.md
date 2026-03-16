# Mortgage API
-Java 17 Spring Boot REST API for mortgage feasibility checks and interest rate retrieval.

## Features
- Retrieve current mortgage interest rates
- Perform mortgage feasibility checks
- Monthly mortgage cost calculation
- Validation of mortgage business rules
- Structured API error handling

## Overview

This project implements a **Java REST API** for checking mortgage feasibility and retrieving mortgage interest rates.
It follows **clean architecture principles** with clear separation of concerns:
Controller → Service → Domain → Utility
- **Controller:** Handles HTTP requests and responses
- **Service:** Orchestrates business logic
- **Domain:** Encapsulates business rules
- **Utility:** Performs mortgage calculations
- **Model:** Immutable DTOs using Java 17 records

## Technology Stack

- Java 17
- Spring Boot 3.x
- Maven
- JUnit 5
- Jakarta Validation

## API Endpoints

1.Root Landing Page
**GET /**

- Returns a friendly message about API availability.
- Example response:
  Mortgage API is running. Available endpoints: /api/interest-rates, /api/mortgage-check.

2.Health Check
**GET /api/health**

- Simple endpoint to verify the application is running.
- Example JSON response:

3.Get Mortgage Rates
**GET /api/interest-rates**

-Returns the list of current mortgage rates.

Example response:
[
{
"maturityPeriod": 10,
"interestRate": 3.2,
"lastUpdate": "2026-03-13T13:15:00"
},
{
"maturityPeriod": 20,
"interestRate": 3.8,
"lastUpdate": "2026-03-13T13:15:00"
}
]

4.Mortgage Feasibility Check
**POST /api/mortgage-check**

Request body (JSON):
{
"income": 50000,
"maturityPeriod": 20,
"loanValue": 180000,
"homeValue": 200000
}

Response body (JSON):
{
"feasible": true,
"monthlyCost": 1065.32
}

## Business Rules

Mortgage must not exceed 4× the income
Mortgage must not exceed the home value

These rules are implemented in the domain package for clarity and maintainability.

## Mortgage Calculation

Monthly mortgage payments are calculated using the standard amortization formula:
M = P × ( r(1+r)^n ) / ( (1+r)^n − 1 )

P = loan value
r = monthly interest rate (annualRate / 12 / 100)
n = number of monthly payments

This logic is implemented in the MortgageCalculator utility class

## Running the Application

-Build:- mvn clean install
-Run:- mvn spring-boot:run
-The server will start at http://localhost:8080

## Testing

-Run all tests:- mvn test
-Includes:- Unit tests for MortgageService and domain rules and Controller tests for REST endpoints

## Example Requests (Git Bash commands using Curl)

-Root landing page:- curl http://localhost:8080/api/
-Health check:- curl http://localhost:8080/api/health
-Get interest rates:- curl http://localhost:8080/api/interest-rates
-Post Mortgage feasibility check :- curl -X POST http://localhost:8080/api/mortgage-check \
-H "Content-Type: application/json" \
-d '{
"income": 50000,
"maturityPeriod": 20,
"loanValue": 180000,
"homeValue": 200000
}'

## Tradeoffs & Future Improvements

-Mortgage rates are in-memory for simplicity; production should use a database.
-Could add Swagger/OpenAPI documentation for easier testing.
-Could containerize with Docker for production deployment.
-Future improvements: dynamic rate updates, additional mortgage rules, observability.
-CI/CD pipeline: could be added to automate build, test, and deployment.

This shows you understand DevOps practices without spending extra time.

## Notes

- Implements **clean architecture** with separation of concerns
- Explicit **domain and utility separation**
- Fully tested with **unit and controller tests**
- Includes **friendly root endpoint and health check** for production readiness
- **Logging included** via Logback configuration
- **Ready for production deployment**
