# AI Job Platform - Candidate Module

Backend project for managing candidate data ingestion, including curriculum uploads and social profile linking.

## Architecture
This project follows **Domain-Driven Design (DDD)** and **Hexagonal Architecture** principles.

- `domain`: Core business logic and entities.
- `application`: Use cases and input/output ports.
- `infrastructure`: Persistence adapters, storage providers, and external integrations.
- `presentation`: REST controllers and API DTOs.

## Tech Stack
- **Java 17**
- **Spring Boot 3.4.3**
- **MySQL 8.4**
- **Maven**
- **Jacoco** (Code Coverage)
- **SpringDoc OpenAPI** (Swagger)

## Getting Started

### Prerequisites
- Docker & Docker Compose
- JDK 17
- Maven

### Running the Database
```bash
docker compose up -d
```

### Running the Application
```bash
mvn spring-boot:run
```

### API Documentation
Once the application is running, you can access the Swagger UI at:
`http://localhost:8080/swagger-ui/index.html`

## Testing
To run all tests and generate the coverage report:
```bash
mvn clean verify
```
The Jacoco report will be generated at `target/site/jacoco/index.html`.

## Key Features
- **Curriculum Upload**: Supports PDF and DOCX files up to 5MB. Verified using Apache Tika.
- **Social Profile Linking**: Supports LinkedIn and GitHub profiles with URL validation.
- **Global Error Handling**: Standardized `ApiResponse` envelope for all endpoints.

## Workflows
We use custom agentic workflows to ensure quality and consistency:
- `/diagnose-work`: Performs a self-diagnostic of the session to extract insights and improve the agent's logic. Check `.agents/insights.md` for results.
- `/develop-backend`: Standard TDD flow for feature development.
