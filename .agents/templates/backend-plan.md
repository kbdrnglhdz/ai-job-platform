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

### **Step 1: Domain & Model**
- **Action**: Implement/Update Domain Entities and Value Objects.
- **Rules**: Inmutable VOs, identity-based Entities, business logic inside.
- **Path**: `src/main/java/com/example/project/domain/model/`

### **Step 2: Database Migration & Persistence**
- **Action**: Create Flyway migration and JPA entities.
- **Path**: `src/main/resources/db/migration/V<N>__desc.sql`
- **Action**: Implement MapStruct if needed for mapping Domain <-> Persistence.

### **Step 3: Application Layer & Use Cases**
- **Action**: Implement Application Service (`@Transactional`).
- **Action**: Implement DTOs and Mappers.
- **Path**: `src/main/java/com/example/project/application/service/`

### **Step 4: Presentation Layer (API)**
- **Action**: Implement Controller and Global Advice.
- **Standard**: JSON envelope and versioned/plural URLs.

### **Step 5: Documentation Update (Step N+1)**
- **Action**: Update technical documentation.
- **Files**: `docs/`, `implementation/plans/`, `README.md`, `pom.xml` (for new dependencies).

## 4. **Checklist & Verification**

### **Testing Strategy**
- [ ] Unit Tests: `should_[expectedBehavior]_when_[condition]`.
- [ ] Integration Tests: Using Testcontainers for MySQL (src/test/java/.../IT.java).
- [ ] `./mvnw clean verify` execution.

### **Quality Assurance**
- [ ] No N+1 queries detected.
- [ ] Coverage >= 85%.
- [ ] Standard error handling implemented.

## 5. **Technical Notes**
- (Concurrency considerations, performance optimizations, security impacts).
