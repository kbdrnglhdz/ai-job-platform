---
description: Execute the development of a backend ticket using TDD and Git Flow.
---

# Workflow: Develop Backend Feature

1. **Setup Branch (Step 0)**:
// turbo
   - Switch to the base branch (`main` or `develop`).
// turbo
   - Pull the latest changes: `git pull origin [base-branch]`.
// turbo
   - Create the feature branch: `git checkout -b feature/[TICKET-ID]-backend`.

1. **Implement by Steps**:
   - Follow the implementation plan at `implementation/plans/[TICKET-ID]_backend.md`.
   - For each step:
     - **TDD Cycle**:
       1. Write a failing test in `src/test/java`.
       2. Implement the minimum code in `src/main/java`.
       3. Verify the test passes.
       4. Refactor keeping tests green.

3. **Verify Quality**:
// turbo
   - Run all tests: `mvn clean test`.
// turbo
   - Run full verification (integration tests, coverage, linter): `mvn clean verify`.
   - **Coverage**: Ensure it meets the 85%+ threshold.

4. **Commit and Push**:
   - Stage changes: `git add .`.
   - Commit using standard format: `type(scope): description`.
// turbo
   - Push to remote: `git push -u origin feature/[TICKET-ID]-backend`.

5. **Create Pull Request**:
// turbo
   - Use `gh pr create` with a clear description and link to the Jira ticket.

6. **Finalize (Step N+1)**:
   - Update documentation (OpenAPI, Data Model, README).
   - Once merged, perform cleanup.