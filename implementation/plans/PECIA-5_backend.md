# Backend Implementation Plan: PECIA-5 Candidate Data Ingestion

## 1. **Overview**
Implement the technical foundation for candidate onboarding by allowing CV (PDF/Word) uploads and linking social profiles (LinkedIn/GitHub). Following DDD and Hexagonal Architecture, this task establishes the `Candidate` aggregate and its interaction with external storage (S3) and relational persistence (MySQL).

## 2. **Architectural Context**
- **Layers Involved**: Domain, Application, Infrastructure, Presentation.
- **Referenced Components**:
  - **Domain**: `Candidate` (Aggregate Root), `Curriculum` (Value Object), `SocialLink` (Value Object), `CandidateRepository` (Port), `StorageService` (Port).
  - **Application**: `UploadCurriculumUseCase`, `LinkSocialProfileUseCase`.
  - **Infrastructure**: `JpaCandidateRepository` (Adapter), `S3StorageAdapter` (Adapter), Flyway Migrations, MapStruct Mappers.
  - **Presentation**: `CandidateController` (Adapter).

## 3. **Implementation Steps**

### **Step 0: Project Initialization & Feature Branch**
- **Action**: Initialize Spring Boot project structure and create the feature branch.
- **Branch**: `feature/PECIA-5-backend`.
- **Steps**:
  1. Generate project structure using Maven (Spring Boot 3.4.x, Java 17).
  2. Create standard hexagonal package structure: `com.pcia.candidate`.
  3. `git checkout -b feature/PECIA-5-backend`.

### **Step 1: Domain Model (Entities & VOs)**
- **Action**: Define the core business logic and models.
- **Path**: `src/main/java/com/pcia/candidate/domain/model/`
- **Details**:
  - `Candidate` Entity with `CandidateId`.
  - `Curriculum` Value Object (immutable, containing S3 keys and metadata).
  - `SocialLink` Value Object (immutable, URL and Type validation).
  - `CandidateRepository` Interface (Port).
  - `StorageProvider` Interface (Port).

### **Step 2: Database Migration & Persistence Implementation**
- **Action**: Configure MySQL schema and JPA adapters.
- **Path**:
  - Migration: `src/main/resources/db/migration/V1__create_candidate_tables.sql`
  - Adapter: `src/main/java/com/pcia/candidate/infrastructure/persistence/`
- **Tasks**:
  - Create table `candidates`, `curriculums`, and `social_links`.
  - Implement `CandidateJpaEntity` and `CandidateJpaRepository`.
  - Configure MapStruct mappers for Domain <-> JPA conversion.

### **Step 3: Application Services & Use Cases**
- **Action**: Orchestrate business logic and ports.
- **Path**: `src/main/java/com/pcia/candidate/application/service/`
- **Tasks**:
  - `UploadCurriculumService`: Validate file -> Upload to S3 (via Port) -> Update Candidate -> Persist.
  - `LinkSocialProfileService`: Validate URL -> Update Candidate -> Persist.

### **Step 4: Presentation Layer (Rest Endpoints)**
- **Action**: Expose versioned API.
- **Path**: `src/main/java/com/pcia/candidate/presentation/controller/`
- **Endpoints**:
  - `POST /api/v1/candidates/{id}/curriculums` (multipart/form-data).
  - `POST /api/v1/candidates/{id}/social-links`.
- **Standard**: Return standard JSON success/error envelopes.

### **Step 5: Documentation & Verification (Step N+1)**
- **Action**: Finalize documentation and verify quality.
- **Files**: Update `README.md`, Generate OpenAPI spec via SpringDoc.
- **Tasks**:
  - Verify 85% coverage.
  - Audit for N+1 queries in persistence.

## 4. **Checklist & Verification**

### **Testing Strategy**
- [ ] Unit Tests: `CandidateTest` (business invariants), `UploadCurriculumServiceTest`.
- [ ] Integration Tests: `CandidatePersistenceIT` using Testcontainers (MySQL).
- [ ] API Tests: `CandidateControllerIT` with `MockMvc`.
- [ ] Command: `./mvnw clean verify`.

### **Quality Assurance**
- [ ] Immuntable Value Objects for `Curriculum` and `SocialLink`.
- [ ] Domain is framework-agnostic.
- [ ] Coverage >= 85%.

## 5. **Technical Notes**
- **Security**: File size limit (5MB) enforced at the Controller and Service level. MIME type verification using `Tika`.
- **S3**: Local dev will use `LocalStack` via Testcontainers or a simple FileSystem implementation for the first iteration.
