---
description: Refine a User Story to meet the Definition of Ready (DoR) and prepare for technical planning.
---

# 🛠 Workflow: Refine User Story

Follow these steps to transform a raw requirement into a technical, "Ready" User Story.

## 1. Context Gathering
- **Read** the current Product Backlog or User Story description.
- **Identify** the main actor and the business value.
- **Skill Reference**: Use `user-story-refinement` skill.

## 2. Functional Refinement
- **Define** 2-3 Acceptance Criteria using Gherkin format.
- **Ask** the user for clarification if any edge case is ambiguous.
- **Validate** that the language is English Only.

## 3. Technical Impact Analysis
- **Analyze** the Hexagonal layers:
  - What **Entities** or **Value Objects** are updated?
  - What **Domain Services** are needed?
  - What **API Endpoints** are created/modified?
  - What **Database** changes are required?
- **Check** for external dependencies (Auth, third-party APIs).

## 4. Documentation
- **Create** a new refinement document using `.agents/skills/user-story-refinement/resources/user-story-template.md`.
- **Save** it in a `docs/refinement/` or `user-stories/` folder as requested by the user.

## 5. DoR Validation
- **Perform** a checklist audit:
  - [ ] English Only technical artifacts.
  - [ ] Testable Acceptance Criteria.
  - [ ] Clear Hexagonal Impact.
- **Confirm** with the user: "Is this story Ready for development?"

---
**Next Step**: Once refined, use `/plan-backend` to create the detailed implementation steps.
