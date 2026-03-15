---
description: Automatically generate the hexagonal package structure for a new domain/bounded context.
---

1. Define the Context Name (e.g., `job`, `recruiter`, `auth`).
2. Create the following directory structure under `src/main/java/com/pcia/[context]/`:
   - `domain/model/`
   - `domain/repository/`
   - `application/service/`
   - `application/port/in/`
   - `application/port/out/`
   - `application/dto/`
   - `infrastructure/persistence/entity/`
   - `infrastructure/persistence/repository/`
   - `infrastructure/persistence/mapper/`
   - `infrastructure/adapter/`
   - `presentation/controller/`
   - `presentation/dto/`

3. Create the following directory structure under `src/test/java/com/pcia/[context]/`:
   - `domain/model/`
   - `application/service/`
   - `infrastructure/persistence/`
   - `presentation/controller/`

4. // turbo
   Run the following command to create the folders:
   ```bash
   mkdir -p src/main/java/com/pcia/[context]/{domain/{model,repository},application/{service,port/{in,out},dto},infrastructure/persistence/{entity,repository,mapper},infrastructure/adapter,presentation/{controller,dto}}
   mkdir -p src/test/java/com/pcia/[context]/{domain/model,application/service,infrastructure/persistence,presentation/controller}
   ```

5. Create a base `AggregateId` and the root `Aggregate` Entity in `domain/model/`.
6. Create the `Repository` interface in `domain/repository/`.
