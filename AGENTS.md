# AGENTS — AI coding agent instructions

Purpose: provide concise, actionable guidance for AI coding agents working in this repo.

Quick commands
- **Build backend:** Run the Maven wrapper from the service directory.

```bash
cd my-dailiy-timesheet-svc
./mvnw clean package
```

- **Run backend (dev):**

```bash
cd my-dailiy-timesheet-svc
./mvnw spring-boot:run
```

- **Run tests:**

```bash
cd my-dailiy-timesheet-svc
./mvnw test
```

Project notes (concise)
- **Backend:** `my-dailiy-timesheet-svc` — Spring Boot service. Key files:
  - `my-dailiy-timesheet-svc/pom.xml` (build config, Java 21)
  - `my-dailiy-timesheet-svc/src/main/java/com/goota/timesheet/MyDailyTimesheetSvcApplication.java` (main class)
  - `my-dailiy-timesheet-svc/src/main/resources/` (app properties, db migrations)
- **Frontend:** `my-daily-timesheet-web` — currently empty placeholder. Check this folder before making frontend changes.

Agent behavior and conventions
- **Use the Maven wrapper** (`./mvnw`) for builds/tests to respect project environment.
- **Run tests locally** before proposing patches that change code or CI.
- **Link, don't embed:** prefer referencing existing docs in this repo (e.g., `my-dailiy-timesheet-svc/HELP.md`).
- **Preserve style:** keep small, focused edits; do not reformat unrelated files.
- **PRs:** create focused PRs with a short description and a test demonstrating the change when possible.

Where to look first
- `my-dailiy-timesheet-svc/HELP.md` — local developer notes (if present).
- `pom.xml` — build settings and Java version (21).

If unsure
- Ask a short clarifying question rather than guessing runtime or environment specifics.
