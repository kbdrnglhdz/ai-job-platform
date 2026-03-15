---
name: User Story Refinement
description: Process and standards to refine User Stories into technical implementation plans meeting DoR and architectural standards.
---

# Skill: User Story Refinement

This skill provides the process and standards to refine User Stories into technical implementation plans, ensuring they meet the **Definition of Ready (DoR)** and align with the project's architectural standards (DDD, Hexagonal, TDD).

## 🎯 Objective
Transform raw business requirements into structured, developer-ready tasks with clear technical impact and acceptance criteria.

## 🛠 Capabilities

### 1. Functional Refinement
- **Requirement Analysis**: Identify the "Who", "What", and "Why" of the story.
- **Acceptance Criteria (AC)**: Convert requirements into testable scenarios using Gherkin format (`Given-When-Then`).
- **Edge Case Identification**: Discover hidden requirements by analyzing boundary conditions and error states.

### 2. Technical Refinement (Hexagonal Alignment)
- **Domain Impact**: Map the story to Bounded Contexts, Aggregates, and Domain Services.
- **Port/Adapter Design**: Identify needed changes in Repository interfaces (Ports) and their implementations (Adapters).
- **API Definition**: Design REST DTOs and Endpoints following the project's pluralized and versioned standards.
- **Data Modeling**: Plan database changes and Flyway migrations.

### 3. Quality Control (DoR Check)
- **Dependency Audit**: Identify blockers or external service dependencies (e.g., Keycloak, third-party APIs).
- **Security & PII**: Identify sensitive data handling requirements.
- **Testability Audit**: Ensure the ACs can be implemented as TDD unit and integration tests.

## 📋 Standards for Refined Stories

All refined stories MUST contain:
1.  **Story Description**: `As a [user], I want [action], so that [value]`.
2.  **Acceptance Criteria**: Minimum 2-3 Gherkin scenarios covering positive/negative flows.
3.  **Technical Metadata**:
    -   **Impacted Layers**: Domain, Application, Infrastructure, Presentation.
    -   **API Spec**: Preview of endpoints and JSON payloads.
    -   **Persistence**: Impact on DB tables and Flyway versioning.
4.  **Security Impact**: Authentication/Authorization needs (RBAC).

## 🧪 Definition of Ready (DoR)
A story is "Ready" for development ONLY when:
- [ ] English Only requirement is met for all descriptions.
- [ ] Business value is clear and understood.
- [ ] Acceptance criteria are defined and testable.
- [ ] Technical design is aligned with Hexagonal Architecture.
- [ ] No external blockers for immediate implementation.
- [ ] Dependencies (DB, Auth, External APIs) are identified.

## 📚 Knowledge Links
- [Java Architect Rules](../../rules/java-architect.md)
- [DDD Standards](../architect-java/SKILL.md)
