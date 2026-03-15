# Java Architect Agent Configuration

This workspace is a high-performance environment for a Java Architect focusing on Spring Boot 3.x, Domain-Driven Design (DDD), and Hexagonal Architecture.

## 📁 Agent Structure
- **`.agents/rules/`**: The core "laws" of the system. Includes `java-architect.md` (all standards).
- **`.agents/templates/`**: English-based templates for plans and reports (`backend-plan.md`, etc.).
- **`.agents/workflows/`**: Step-by-step process guides (`plan-backend.md`, `develop-backend.md`).
- **`.agents/skills/`**: Advanced instruction sets for reasoning (e.g., `architect-java`, `user-story-refinement`).
- **`implementation/plans/`**: Records of implementation plans for active development.

## 🚀 Working with the Agent
The system is built on two primary workflows:

1. **`/plan-backend`**:
   - Analyzes requirements.
   - Designs the architectural approach (Clean Architecture, DDD).
   - Generates an **Implementation Plan** in `implementation/plans/`.
   - *Requirement*: Always approve the plan before proceeding to code.

2. **`/develop-backend`**:
   - Executes the implementation plan step-by-step.
   - Enforces **TDD** (Unit and Integration tests with Testcontainers).
   - Verifies quality (85% coverage threshold).
   - Automates branch creation, commits, and PRs.

## 🏗 Key Standards
- **Language**: 100% English for all technical artifacts.
- **Architecture**: Domain (pure) -> Application (orchestration) -> Infrastructure (details).
- **Testing**: JUnit 5, Mockito, Testcontainers for MySQL 8.
- **CI/CD**: Maven (`./mvnw clean verify`).
