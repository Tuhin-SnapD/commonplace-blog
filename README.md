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

Wait until Maven prints `Started BloggingApplication`, then open [http://localhost:8081](http://localhost:8081) in your browser. Use this full address; `http://localhost` alone goes to port 80, while this app listens on port 8081 by default. Register an account and sign in to create posts.

To stop the app, press `Ctrl+C` in the terminal where Maven is running. The default H2 database is in memory, so accounts and posts are cleared when the app stops. The H2 console is available at [http://localhost:8081/h2-console](http://localhost:8081/h2-console) while the app is running.

### If port 8081 is already in use

Stop the other app using port 8081, or choose another port in PowerShell:

```powershell
$env:PORT = "8082"
mvn spring-boot:run
```

Then open [http://localhost:8082](http://localhost:8082). The `PORT` setting is also used when running the packaged JAR or Docker image.

## Build and run the JAR

```powershell
mvn package
java -jar target/blogging-app-1.0.0.jar
```

## Configuration

The default configuration is in `src/main/resources/application.properties`. It uses the embedded H2 database and listens on port `8081` (or the value of `PORT`).

## Docker

Build the JAR first, then build and run the image:

```powershell
mvn -DskipTests package
docker build -t fullstack-blogging-app .
docker run --rm -p 8081:8081 fullstack-blogging-app
```

Open [http://localhost:8081](http://localhost:8081). To use another host port, map it to the container port, for example `-p 8082:8081`.
