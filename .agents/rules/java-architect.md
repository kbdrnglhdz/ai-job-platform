# Java Architect Rules (Comprehensive)

This document defines the overarching standards for all Java backend development in this workspace, consolidating DDD, Hexagonal Architecture, and Spring Boot 3.x best practices.

## 1. Fundamental Principles
1. **English Only**: All technical artifacts (code, comments, commits, docs, tickets, and logs) MUST be in English.
2. **Atomic Tasks**: Break major features into small, testable tasks (< 100 modified lines per change if possible).
3. **Test-Driven Development (TDD)**: Write a failing test first, make it pass, then refactor.
4. **Explicit Typing**: Leverage Java's type system (Generics, `final` keywords, avoid `Object` casting).
5. **Separation of Concerns**: Keep business logic away from frameworks and persistence details.

## 2. Technology Stack (LTS)
- **Java 17+**, **Spring Boot 3.x**, **Maven**.
- **Persistence**: **MySQL 8.x**, **Flyway** (`src/main/resources/db/migration`).
- **Observability**: **Micrometer Tracing**, **Actuator**, **Prometheus**.
- **Identity**: **Spring Security OAuth2/OIDC** (Keycloak for local dev).
- **Testing**: **JUnit 5**, **Mockito**, **Testcontainers** (MySQL, Keycloak).
- **Utilities**: **Lombok**, **MapStruct**.

## 3. Architecture: DDD & Hexagonal
Maintain a clean separation between layers:
1. **Domain Layer**: The core. No dependencies on frameworks.
   - `model`: Entities (identity-based) and Value Objects (attribute-based, immutable). Use `Objects.requireNonNull` for mandatory fields.
   - `repository`: Interfaces for domain persistence (ports).
   - `service`: Pure business logic coordinating domain models or multiple aggregates.
   - `event`: Domain events to notify changes (e.g., `CandidatoCreado`).
   - `factory`: (Optional) For creating complex aggregates.
2. **Application Layer**: Use cases and orchestration.
   - `service`: Application services that implement use cases and manage transactions (`@Transactional`).
   - `dto`: Request and response objects defined for the outside world (plural nouns for collections).
   - `mapper`: MapStruct interfaces (Domain <-> DTO).
3. **Infrastructure Layer**: Implementation details.
   - `persistence`: JPA implementations, `@Entity` classes (separate from Domain), Spring Data Repositories.
   - `config`: Spring beans, logging, security setup.
   - `client`: External API clients (e.g., Feign, RestTemplate).
4. **Presentation Layer**: The entry points.
   - `controller`: REST endpoints using versioned, plural nouns (`/api/v1/candidatos`).
   - `advice`: Global exception handlers mapping to standard error envelopes.

## 4. Coding & Quality Standards
- **Naming Conventions**:
  - `PascalCase` for Classes (`CustomerService`).
  - `verboEnCamelCase` for Methods (`calculateTotalPrice`).
  - `camelCase` for Variables (`orderList`).
  - `UPPER_SNAKE_CASE` for Constants (`MAX_POOL_SIZE`).
  - Tests: `should_[expectedBehavior]_when_[condition]`.
- **SOLID Principles**:
  - **Single Responsibility (SRP)**: Small classes, one reason to change.
  - **Dependency Inversion (DIP)**: Always depend on abstractions (interfaces), not implementations.
- **Persistence Best Practices**:
  - **Separate Domain from JPA**: Never use `@Entity` in the domain package. Map between them in the infra layer using MapStruct.
  - **Flyway Rules**: Never edit an already applied migration. Use `V<VERSION>__<description>.sql`.
- **Performance Guidelines**:
  - **Avoid N+1 queries**: Use `JOIN FETCH` or `@EntityGraph`.
  - **Read-Only Optimization**: Use projections (interfaces or DTOs) for read-only operations.
  - **Short Transactions**: Place `@Transactional` in the Application layer, keep it light. Avoid costy operations (like HTTP calls) inside transactions.
  - **Concurrency**: Use `@Version` for optimistic locking where appropriate.
- **Auditability**: All entities must implement audit fields (`created_at`, `updated_by`, etc.). Use Spring Data Envers for sensitive data.

## 5. Security & Observability (Enterprise Grade)
- **PII Protection**: Mask sensitive data in logs and DTOs. Never log passwords or secrets.
- **Distributed Tracing**: Every request must carry a `traceId`. Use structured JSON logging for production readiness.
- **IAM**: Depend on OAuth2/OIDC providers. Enforce RBAC using `@PreAuthorize` in the Application layer.
- **Zero Trust**: Validate every input at Presentation AND Domain layers.
- **Health & Metrics**: Expose Actuator endpoints and Prometheus metrics.

## 6. REST API Design
- **Standard Envelope**:
  ```json
  {
    "success": true,
    "data": { ... },
    "message": "Operation successful"
  }
  ```
  - **Error (4xx/5xx)**:
  ```json
  {
    "success": false,
    "error": {
      "code": "ERROR_CODE",
      "message": "Readable description",
      "details": []
    }
  }
  ```
- **Versioning**: Prefix all endpoints with `/api/v1/`.

## 6. Development Workflow
1. **Branching**: `feature/[ID]-description-backend` based on `develop` or `main`.
2. **Commit Messages**: `type(scope): description` (e.g., `feat(orders): add discount calculation`).
3. **Verification**: Run `mvn clean verify` before any push to ensure quality and coverage.
4. **Quality Threshold**: Minimum **85%** code coverage (Jacoco).
5. **Architectural Decisions**: Create an **ADR** (Architecture Decision Record) for any significant design change in `docs/adr/`.
6. **Documentation**: Step N+1 is always mandatory. Update `ai-specs`, README, and OpenAPI specs.

## 7. Collaboration & Mentorship (Co-Authorship)
This agent operates as a **Co-Author and Technical Mentor**. The goal is not just to output code, but to provide the user with the "Know-How".

1.  **Explain the "Why"**: Rationale based on DDD or SOLID.
2.  **Options & Trade-offs**: Present paths with pros/cons.
3.  **Interactive Planning**: Pause to confirm critical design decisions.
4.  **Audit for Learning**: Signal code smells and architectural violations.
5.  **Self-Documenting Plans**: Plans in `implementation/plans/` must be highly detailed.
6.  **Continuous Knowledge Transfer**: Context about patterns (Strategy, Factory, etc.).

