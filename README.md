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

## How to Run the Project

Follow these steps to get the project up and running on your local machine.

### Prerequisites
* **Java 17** (JDK)
* **Maven 3.8+**
* **Docker & Docker Compose**

### 1. Start the Infrastructure
The project uses MySQL as the primary database. You can start it using Docker Compose:

```bash
docker compose up -d
```

This will start a MySQL 8.4 container with the following configuration:
* **Database**: `pcia_db`
* **Port**: `3306`
* **Username**: `root` (configured in `application.yml`)
* **Password**: `password`

### 2. Run the Application
You can start the Spring Boot application using Maven:

```bash
mvn spring-boot:run
```

Alternatively, you can build the JAR and run it:

```bash
mvn clean package
java -jar target/ai-job-platform-0.0.1-SNAPSHOT.jar
```

### 3. Verify the Installation
Once the application is running, you can verify it by:
* **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
* **Health Check**: [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health) (if actuator is enabled)

### API Documentation
We use OpenApi/Swagger to document our endpoints. You can explore and test the API directly from the browser.


## Testing
To run all tests and generate the coverage report:
```bash
mvn clean verify
```
The Jacoco report will be generated at `target/site/jacoco/index.html`.

## Database Migrations
The project uses **Flyway** for database schema management. Migrations are located in `src/main/resources/db/migration` and are automatically executed when the application starts.

## Environment Variables
The application is pre-configured to work with the provided `docker-compose.yml`. However, you can override settings using environment variables or a custom `application.yml`:

| Property | Default Value | Description |
|----------|---------------|-------------|
| `SPRING_DATASOURCE_URL` | `jdbc:mysql://localhost:3306/pcia_db` | Connection string |
| `SPRING_DATASOURCE_USERNAME` | `root` | DB Username |
| `SPRING_DATASOURCE_PASSWORD` | `password` | DB Password |

## Key Features
- **Curriculum Upload**: Supports PDF and DOCX files up to 5MB. Verified using Apache Tika.
- **Social Profile Linking**: Supports LinkedIn and GitHub profiles with URL validation.
- **Global Error Handling**: Standardized `ApiResponse` envelope for all endpoints.


## Workflows
We use custom agentic workflows to ensure quality and consistency:
- `/diagnose-work`: Performs a self-diagnostic of the session to extract insights and improve the agent's logic. Check `.agents/insights.md` for results.
- `/develop-backend`: Standard TDD flow for feature development.
