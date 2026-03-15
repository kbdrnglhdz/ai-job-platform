# Project Insights & Lessons Learned - PECIA-5

## Retrospective Review
The development of the Candidate Data Ingestion feature highlighted several technical nuances in Spring Boot 3.4 and Hexagonal Architecture.

### Failure Points & Solutions
1. **Mocking in Integration Tests**:
   - *Problem*: `CandidatePersistenceIT` failed due to missing `StorageProvider`.
   - *Insight*: Even in `@SpringBootTest` with slice or full context, external ports (Interfaces) without a default implementation must be mocked using `@MockitoBean`.
2. **Controller Exception Handling**:
   - *Problem*: `shouldReturnBadRequestWhenSocialLinkInvalid` was returning 200.
   - *Insight*: The service was returning a value instead of throwing an exception because the mock was not configured to `doThrow()`. Always use `doThrow()` for validating `GlobalExceptionHandler` behavior.

### Aha! Moments
- **Spring Boot 3.4 Migration**: Discovery that `@MockBean` is deprecated and `@MockitoBean` (from `spring-boot-test-snapshots` or direct support) is the new standard for Mockito integration in Spring.
- **Tika Verification**: Using `tika-core` adds a significant layer of security by verifying the actual file header (magic numbers) instead of trusting the client-provided `Content-Type`.

## Lessons Learned (Diagnostic)
1. **Test Dependencies**: Maintain a standard "Test Base" or Shared Mocking strategy for Ports.
2. **Hexagonal Boundaries**: ArchUnit (added by the User) is essential to prevent `Domain` from leaking into `Infrastructure` or vice versa.
3. **MIME Integrity**: Security-first approach for file uploads should always include server-side binary verification.

## Actionable Next Steps
- [ ] Migrate all remaining `@MockBean` to `@MockitoBean` across the codebase.
- [ ] Implement a `FileSystemStorageProvider` for non-cloud development environments.
- [ ] Expand ArchUnit rules to check for proper naming conventions (`*UseCase`, `*Port`, `*Adapter`).
