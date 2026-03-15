---
description: Perform a self-diagnostic of work performance and extract insights to improve agents, skills, and code.
---

# Workflow: Diagnose Insights and Improvements

This workflow is used to reflect on the work done in a session or sprint, identifying patterns, mistakes, and opportunities for systemic improvement.

1. **Retrospective Review**:
   - Analyze the conversation history (`conversation_summaries`).
   - Identify critical failure points (e.g., tool errors, logic mistakes, forgotten requirements).
   - Identify "Aha!" moments where a specific configuration or pattern resolved a recurring issue.

2. **Codebase Diagnostic**:
// turbo
   - Run architectural verification: `mvn test -Dtest=HexagonalArchitectureTest`.
   - Review the latest `pom.xml` changes for new dependencies or quality tools.
   - Audit the `.agents/skills` and `.agents/rules` to see if they reflect current best practices found during the session.

3. **Extract Insights**:
   - List at least 3 specific "Lessons Learned" (e.g., "Use @MockitoBean instead of @MockBean for Spring Boot 3.4+ ITs").
   - Identify "Pain Points" that slowed down the development.

4. **Update Knowledge Base**:
   - If a recurring error was found, update the relevant `.agents/rules` file.
   - If a new architectural pattern was used, update the relevant `.agents/skills` (e.g., Architect Java skill).
   - If a workflow was inefficient, propose modifications to the `.md` file in `.agents/workflows`.

5. **Document Diagnostic**:
   - Create or update an `insights.md` file in the `.agents/` directory summarizing the findings.
   - Present the summary to the user with actionable next steps for the project’s technical debt or agent configuration.
