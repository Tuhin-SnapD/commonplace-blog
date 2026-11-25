# Commonplace

**A quiet place for stories worth keeping.** Commonplace is a Spring Boot blogging application with a responsive Thymeleaf interface. Visitors can browse stories, create an account, sign in, and publish posts.

> **Deployment status:** the repository is ready for local development and container builds. Its current defaults are not suitable for a public production deployment: the database is in memory, the H2 console is enabled, and Hibernate manages the schema automatically. See [Production readiness](#production-readiness) before deploying to a public environment.

## Screenshots

### Home

![Commonplace home page](docs/screenshots/home.jpg)

### Create an account

![Create an account form](docs/screenshots/register.jpg)

### Log in

![Log in form](docs/screenshots/login.jpg)

## Features

- Public home page with the latest published stories.
- Account registration and form-based login.
- Authenticated story publishing.
- BCrypt password hashing and Spring Security request protection.
- Responsive layouts for the home, registration, login, and writing pages.
- H2 database for a zero-setup local run.

## Technology

- Java 17
- Spring Boot 3.3.5
- Spring MVC, Spring Security, Spring Data JPA
- Thymeleaf
- H2
- Maven
- Docker

## Run locally

### Prerequisites

- JDK 17 or newer
- Maven 3.6 or newer

From the repository root, start the application:

```powershell
mvn spring-boot:run
```

Wait for `Started BloggingApplication`, then open [http://localhost:8080](http://localhost:8080). Register an account to publish a story. The app listens on port `8080` by default; set `PORT` to use a different port.

Stop the app with `Ctrl+C` in the terminal running Maven. The default H2 database is in memory, so accounts and stories are removed when the application stops.

If port 8080 is already occupied, identify its listener in PowerShell:

```powershell
Get-NetTCPConnection -LocalPort 8080 -State Listen |
  Select-Object LocalAddress, LocalPort, OwningProcess
```

Check the listed process before stopping it. Alternatively, set a different `PORT` for the app.

## Build

Package the executable JAR:

```powershell
mvn clean package
```

Run the JAR:

```powershell
java -jar target/blogging-app-1.0.0.jar
```

No automated test sources are currently included in the repository.

## Docker

Build the JAR first, then build and run the container:

```powershell
mvn -DskipTests package
docker build -t commonplace:local .
docker run --rm -p 8080:8080 commonplace:local
```

Open [http://localhost:8080](http://localhost:8080). To use another host port, change the left side of the mapping (for example, `-p 8082:8080`). To change the application port inside the container, set `PORT` and map the same container port:

```powershell
docker run --rm -e PORT=8082 -p 8082:8082 commonplace:local
```

This container uses the same in-memory database defaults as a local run. Container restarts discard account and post data.

## Application routes

| Route | Access | Purpose |
| --- | --- | --- |
| `GET /` | Public | Browse published stories |
| `GET /register` | Public | Open account registration |
| `POST /register` | Public | Validate and create an account |
| `GET /login` | Public | Open the login form |
| `POST /login` | Public | Authenticate a user |
| `GET /add` | Signed-in users | Open the story editor |
| `POST /add` | Signed-in users | Publish a story |

## Configuration

The defaults are in [`application.properties`](src/main/resources/application.properties). The only explicitly parameterized deployment setting is:

| Variable | Default | Description |
| --- | --- | --- |
| `PORT` | `8080` | HTTP port for the embedded server |

Spring Boot can override datasource properties through its standard environment-variable binding, but this application currently includes only the H2 JDBC driver. A production database such as PostgreSQL requires adding its driver and configuring a durable database connection before it can be used.

## Production readiness

Do not expose the current default configuration to the public internet. Before production deployment, address these items in application configuration and code:

1. **Use durable storage.** Add a supported production database and externalize the connection URL and credentials. Configure backups and test restore procedures. H2 memory data disappears when the process exits.
2. **Manage schema changes.** Replace `spring.jpa.hibernate.ddl-auto=update` with a controlled migration workflow, such as Flyway or Liquibase, and validate the schema at startup.
3. **Disable the H2 console.** Set `spring.h2.console.enabled=false` outside local development and remove its public security exception and CSRF exclusion.
4. **Set production web settings.** Enable Thymeleaf template caching, configure HTTPS at the ingress or reverse proxy, and review secure session-cookie and forwarded-header settings for the hosting platform.
5. **Add operational checks.** Configure a health endpoint and container health check, centralize logs and alerts, and document backup, restore, and rollback procedures.
6. **Complete release verification.** Add automated tests for registration, authentication, publishing, validation, and database migrations, then run them in CI before deployment.

The README describes the current implementation and does not claim these production controls are already present.

## Project structure

```text
src/main/java/               Spring MVC controllers, services, repositories, entities, security
src/main/resources/
  templates/                 Thymeleaf pages
  static/css/                Application styles
  application.properties     Local application defaults
docs/screenshots/            README UI screenshots
```

## License

No license is currently specified. Add a `LICENSE` file before redistributing or reusing this project.
