---
name: Java Architect
description: Advanced capabilities to design, plan, and audit Java backend systems using Spring Boot, DDD, and Hexagonal Architecture.
---

# Skill: Java Architect

This skill provides advanced capabilities to design, plan, and audit Java backend systems using Spring Boot, DDD, and Hexagonal Architecture.

## Capabilities

### 1. Architectural Design & DDD
- **Entity vs VO**: Identify when to use an Entity (identity-based) vs a Value Object (attribute-based).
- **Aggregate Roots**: Design clusters of objects that maintain consistency through a single root.
- **Hexagonal Layers**: Ensure `domain` has zero dependencies on `infrastructure`.
- **SOLID Auditor**: Detect violations like "Fat Classes" (SRP) or "Concrete Dependencies" (DIP).

### 2. Implementation Planning & API Design
- **API Spec**: Design REST endpoints following plural naming and versioning (`/api/v1/`).
- **Response Envelopes**: Enforce standard success/error JSON wrappers.
- **Persistence Planning**: Plan SQL migrations and separate JPA entities from Domain entities.

### 3. Performance & Quality Control
- **Anti N+1**: Proactively suggest `JOIN FETCH` or `@EntityGraph`.
- **Query Projections**: Minimize memory usage by reading only required fields.
- **TDD Guidance**: Enforce test naming conventions (`should_..._when_...`) and IT setup with Testcontainers.
- **Coverage Check**: Verify JaCoCo reports for the 85% threshold.

### 4. Mentorship & Knowledge Transfer
- **Decision Design**: Explain "Why" for all architecture/design choices.
- **Traceability**: Build `ai-specs/changes/` as step-by-step technical guides.
- **Pattern Learning**: Explain the design patterns applied (e.g., Factory, Strategy, Decorator).

## 📚 Knowledge Base
All architectural and coding standards are consolidated in:
- [Java Architect Rules](../../rules/java-architect.md)
- [Knowledge Base](./resources/)

## 🛠 Operation Standards
- **Build Tool**: Use `mvn` for all operations.
- **Logic Placement**: Ensure 100% of business logic remains in the `domain` layer.
- **Task Recording**: Create or update plans in `implementation/plans/` before making code changes.
