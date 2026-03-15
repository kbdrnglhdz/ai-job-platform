# Backend Implementation Plan: [TICKET-ID] [Name of the functionality]

## 1. **Overview**
- Brief description of the requirement and architectural principles (DDD, Clean Architecture, Spring Boot).

## 2. **Architectural Context**
- **Layers Involved**: (Domain, Application, Infrastructure, Presentation).
- **Referenced Components**: (Controllers, Services, Repositories, Entities, DTOs).

## 3. **Implementation Steps**

### **Step 0: Create Feature Branch**
- **Action**: Create and switch to a new branch following the conventions.
- **Naming**: `feature/[TICKET-ID]-backend`.
- **Steps**:
  1. `git checkout [develop|main]`
  2. `git pull origin [develop|main]`
  3. `git checkout -b feature/[TICKET-ID]-backend`

### **Step 1: Scaffolding & Domain Model**
- **Action**: Use `/scaffold-domain` to generate the structure.
- **Path**: `src/main/java/com/pcia/[context]/domain/model/`
- **Details**: Define Aggregate Root, Entities, and Value Objects.

### **Step 2: Database Migration & Persistence Implementation**
- **Action**: Create Flyway migration and JPA adapters.
- **Check**: Ensure `domain` has zero dependencies on `infrastructure`.

... (Steps 3-4 remain similar) ...

## 4. **Checklist & Verification**

### **Testing Strategy**
- [ ] Unit Tests: `should_[expectedBehavior]_when_[condition]`.
- [ ] Integration Tests: Using Testcontainers for MySQL (src/test/java/.../IT.java).
- [ ] **Architecture Test**: Run `HexagonalArchitectureTest` to ensure no layer violations.
- [ ] `./mvnw clean verify` execution.

### **Quality Assurance**
- [ ] No N+1 queries detected (Audit `@EntityGraph` or `JOIN FETCH`).
- [ ] Coverage >= 85% (Enforced by JaCoCo).
- [ ] ArchUnit compliance verified.
- [ ] Standard error handling implemented.

## 5. **Technical Notes**
- (Concurrency considerations, performance optimizations, security impacts).
