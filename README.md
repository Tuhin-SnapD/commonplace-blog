# Fullstack Blogging App

A Spring Boot blogging app with account registration and login, and pages to create and view posts. It uses Thymeleaf for the web UI, Spring Security for authentication, Spring Data JPA for persistence, and an in-memory H2 database by default.

## Requirements

- Java 17 or newer
- Maven 3.6 or newer

## Run locally

From the project directory, run:

```powershell
mvn spring-boot:run
```

Open [http://localhost:8081](http://localhost:8081), register an account, and sign in. The default H2 database is in memory, so accounts and posts are cleared when the app stops. The H2 console is available at [http://localhost:8081/h2-console](http://localhost:8081/h2-console) while the app is running.

To choose another port, set the `PORT` environment variable before starting the app.

## Build and run the JAR

```powershell
mvn package
java -jar target/blogging-app-1.0.0.jar
```

## Configuration

The default configuration is in `src/main/resources/application.properties`. It uses the embedded H2 database and listens on port `8081` (or the value of `PORT`).
