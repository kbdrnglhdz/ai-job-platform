# 📄 User Story Template: [Story ID] - [Short Description]

## 1. Description
**As a** [Type of User]
**I want** [Action/Functionality]
**So that** [Business Value/Benefit]

---

## 2. Functional Acceptance Criteria (Gherkin)

### Scenario 1: [Success Path]
- **Given** [Pre-conditions]
- **When** [Action]
- **Then** [Result]

### Scenario 2: [Alternative/Error Path]
- **Given** [Pre-conditions]
- **When** [Action]
- **Then** [Error/Validation Result]

---

## 3. Technical Refinement (Hexagonal)

### 🧱 Impacted Components
- **Domain Layer**: [Entities/Aggregates to create or modify]
- **Application Layer**: [Services/Use Cases/DTOs]
- **Infrastructure Layer**: [Persistence/Adapters/Config/External Clients]
- **Presentation Layer**: [REST Controllers/Endpoints]

### 🌐 API Specification
- **Endpoint**: `[METHOD] /api/v1/[path]`
- **Request Payload**:
  ```json
  {
    "field": "value"
  }
  ```
- **Response Payload**: Standard Success/Error Envelope.

### 💾 Data Persistence
- **Table(s)**: [Table name]
- **Changes**: [New columns/Constraints]
- **Flyway**: `V[VERSION]__[description].sql`

---

## 4. Security & Observability
- **Authorization**: [Roles needed: e.g., ADMIN, USER]
- **Logs**: [Specific events to log]
- **Metrics**: [Business metrics to track]

---

## 5. Definition of Ready (DoR) Checklist
- [ ] Business value confirmed.
- [ ] ACs are testable (TDD-ready).
- [ ] Technical impact fully mapped.
- [ ] No external blockers.
- [ ] Aligned with "English Only" rule.
