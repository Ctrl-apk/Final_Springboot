# Food Delivery Backend API

A Spring Boot–based backend system for a food delivery application supporting restaurant management, orders, authentication, pagination, filtering, and centralized exception handling.

## Tech Stack
- Java 8
- Spring Boot
- Spring Data MongoDB
- Maven
- Swagger / OpenAPI 3
- MongoDB

## Features Implemented

### Authentication & User Management
- Restaurant registration and login
- Password update, phone update, address update

### Restaurant Management
- Add / remove dishes from menu
- Update restaurant information
- View restaurant menu
- Search restaurants
- Paginated restaurant listing
- Filtering restaurants by city and cuisine tag

### Order Management
- View active orders for restaurants
- View order history
- Fetch customer and restaurant orders
- Order status handling

### Pagination & Sorting
- Pagination and sorting supported on restaurant listing APIs
- Pageable responses with metadata (page number, size, total pages)

### API Documentation
- Swagger UI available for testing all endpoints

### Exception Handling
- Centralized global exception handling using `@ControllerAdvice`
- Consistent API error responses

> Note: The project aims to provide functional endpoints rather than full production hardening. If something is partial, it is listed above.

## API Documentation

Swagger UI is available at:

http://localhost:8080/swagger-ui.html

All APIs can be tested directly from the Swagger interface.

## How to Run

### Prerequisites
- Java 8
- Maven
- MongoDB running locally

### Steps
1. Clone the repository
2. Navigate to the project directory
3. Run the application:

```bash
mvn spring-boot:run
```

Open Swagger UI at:

http://localhost:8080/swagger-ui.html

## Project Structure

- `controller/`     → REST API controllers
- `service/`        → Business logic
- `repository/`     → MongoDB repositories
- `model/`          → Entity classes
- `dto/`            → Request & Response DTOs
- `exception/`      → Custom exceptions & global handler

---

