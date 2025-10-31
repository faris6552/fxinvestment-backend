# Copilot / AI agent instructions — FxInvestment Backend

Summary: This is a small Spring Boot backend (Java 21, Maven) that stores performance records in MySQL using Spring Data JPA. The service exposes a simple REST API under `/api/performance` and persists entities to the `performance` table.

Key files & locations
- `pom.xml` — project deps and Java version (Java 21). Uses Spring Boot, JPA, WebMVC, MySQL connector.
- `src/main/java/com/fxinvestment/backend/BackendApplication.java` — Spring Boot entry point (@SpringBootApplication).
- `src/main/resources/application.properties` — runtime config (DB URL, credentials, JPA settings, server.port, upload limits).
- `src/main/java/com/fxinvestment/backend/controller/PerformanceController.java` — REST endpoints (GET/POST/PUT/DELETE, plus GET `/fxid/{fxid}`).
- `src/main/java/com/fxinvestment/backend/model/PerformanceRecord.java` — JPA entity mapped to `performance` table.
- `src/main/java/com/fxinvestment/backend/repository/PerformanceRepository.java` — Spring Data JPA repo; includes `findByFxid(String)`.

Big-picture architecture (what an agent should know)
- Single Spring Boot app exposing a REST API and using Spring Data JPA to talk to MySQL. There is currently no separate service layer: controllers inject repositories directly.
- Data flow example: HTTP request -> `PerformanceController` -> `PerformanceRepository` -> MySQL (table `performance`).
- DB schema is driven by Hibernate (`spring.jpa.hibernate.ddl-auto=update`) — schema updates happen at runtime by Hibernate.

Concrete examples agents can use
- Endpoints:
  - GET /api/performance -> returns all PerformanceRecord entries
  - GET /api/performance/{id} -> single record
  - POST /api/performance -> create record (JSON body matching `PerformanceRecord` fields)
  - PUT /api/performance/{id} -> update
  - DELETE /api/performance/{id} -> delete
  - GET /api/performance/fxid/{fxid} -> find by fxid (uses `PerformanceRepository.findByFxid`)
- Entity fields (from `PerformanceRecord`): id, fxid, week (Integer), results (Double), datetime (LocalDateTime), comments, filePath.

Build / run / test (Windows + PowerShell)
- Build (skip tests): `.\mvnw.cmd -DskipTests=true package`
- Run app: `.\mvnw.cmd spring-boot:run` (or run from IDE). The repo contains `mvnw` and `mvnw.cmd` wrappers — prefer those.
- Run tests: `.\mvnw.cmd test`
- Run with remote debugger port 5005 (Maven spawn args):
  `.\mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"`

Project-specific conventions & patterns
- Package layout: `controller`, `model`, `repository` under `com.fxinvestment.backend`. Add new features following this same package structure.
- No `service` package is present; controllers call repositories directly. If adding business logic, create `service` package and annotate classes with `@Service` and call those from controllers.
- Configuration is file-based in `application.properties`. Credentials are currently in that file (for local/dev). Look there for DB URL and credentials before making env assumptions.
- SQL logging is enabled (`spring.jpa.show-sql=true`) — helpful for debugging DB queries.

Integration points & external dependencies
- MySQL database: configured in `application.properties` (URL: `jdbc:mysql://localhost:3306/FxInvestment`). Ensure MySQL is running locally or adjust properties / profiles.
- JPA + Hibernate (via `spring-boot-starter-data-jpa`); automatic schema update via `ddl-auto=update`.
- Devtools is present (runtime optional) for hot-reload behaviour during development.

What AI agents should do (concrete, non-aspirational)
- When adding a new entity: create the entity class under `model`, add a repository interface under `repository` (extend `JpaRepository<T, ID>`), and expose endpoints in a controller under `controller`.
- For DB schema-sensitive changes, be aware Hibernate will auto-apply DDL changes locally; for production use a migration tool (none present in repo).
- For tests: tests live under `src/test/java` and the project includes `spring-boot-starter-test`.

Files to check first when debugging
- `application.properties` — DB, port, upload limits, and JPA debug options.
- Controller and repository classes to trace API -> DB flow.
- `pom.xml` for dependency versions and plugins (Spring Boot plugin).

If anything here is unclear or you want these instructions expanded (examples for adding a Service layer, sample unit tests, or CI steps), tell me which area to expand and I will iterate.

---
Paths referenced above are relative to the repository root.
