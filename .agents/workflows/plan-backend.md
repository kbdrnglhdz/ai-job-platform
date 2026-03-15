---
description: Plan the implementation of a backend ticket following DDD, Hexagonal architecture, and project-specific performance standards.
---

# Workflow: Plan Backend Implementation

1. **Information Gathering**:
   - Read the Jira ticket [TICKET-ID].
   - Ask clarifying questions if there is any ambiguity.

2. **Codebase Exploration**:
   - Locate relevant domain entities, services, and repositories.
   - Identify affected layers (Presentation, Application, Domain, Infrastructure).
   - **Performance Check**: Identify potential N+1 queries or long transactions.

3. **Generate Implementation Plan**:
   - Create a file at `implementation/plans/[TICKET-ID]_backend.md`.
   - Use the template defined in `.agents/templates/backend-plan.md`.
   - **Ensure the plan includes**:
     - **API Spec**: Standard JSON response format and plural versioned URLs.
     - **Persistence**: Flyway migration naming (`V<N>__desc.sql`) and mapping between Domain/JPA entities.
     - **Performance**: Use of `@EntityGraph` or projections for efficiency.
     - **Step 0**: Explicit branch creation instructions.
     - **Step N+1**: Mandatory documentation update step.
     - **Testing**: Define tests with `should_..._when_...` naming convention.

4. **Review with User**:
   - Present the plan to the user for approval.
   - Explain the "Why" behind architectural decisions.