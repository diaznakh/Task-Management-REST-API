Task Management REST API

Java 17 + Spring Boot 3, JWT security, Spring Data JPA (MySQL/H2), Swagger/OpenAPI, Docker.

Getting started (from scratch)
1) Clone the repository
```bash
git clone <your-repo-url>
cd <your-cloned-folder>
# example
# git clone https://github.com/<you>/task-management-rest-api.git
# cd task-management-rest-api
```
2) Verify prerequisites
```bash
java -version   # should be 17.x
mvn -v          # Maven 3.9+
docker -v       # if using Docker option
docker compose version
```
3) Build
```bash
mvn -DskipTests package
```
4) Run (choose one of the options below: Docker Compose, local MySQL, or H2)

Prerequisites
- Java 17
- Maven 3.9+
- Docker + Docker Compose (for containerized run)

Option A: Run with Docker Compose (App + MySQL)
1) Build the app image (multi-stage Dockerfile will build the jar inside the image)
```bash
mvn -DskipTests package
docker compose up -d --build
```
2) Check logs (optional)
```bash
docker compose logs -f app
```
3) Open
- App: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui/index.html
4) Stop
```bash
docker compose down -v
```

Option B: Run against local MySQL (without Docker Compose)
1) Create DB and user
```bash
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS taskdb; \
CREATE USER IF NOT EXISTS 'tmuser'@'%' IDENTIFIED BY 'tmpass'; \
GRANT ALL PRIVILEGES ON taskdb.* TO 'tmuser'@'%'; FLUSH PRIVILEGES;"
```
2) Run the app
```bash
export DB_URL="jdbc:mysql://localhost:3306/taskdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
export DB_USERNAME="tmuser"
export DB_PASSWORD="tmpass"
export DB_DRIVER="com.mysql.cj.jdbc.Driver"
export JWT_SECRET="change-me"

mvn -DskipTests spring-boot:run
# or
mvn -DskipTests package && java -jar target/task-manager-0.0.1-SNAPSHOT.jar
```

Option C: Quick run with in-memory H2
```bash
mvn -DskipTests spring-boot:run
# or
mvn -DskipTests package && java -jar target/task-manager-0.0.1-SNAPSHOT.jar
```

Swagger/OpenAPI
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- Click "Authorize" and paste the JWT token (no "Bearer " prefix needed).

Auth quick test (curl)
1) Register
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"user1@example.com","password":"pass"}'
```
2) Login and copy the token
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user1@example.com","password":"pass"}'
```
3) Use token for protected routes
```bash
TOKEN="paste_token_here"

# Create a project (replace ownerId with the registered user's id)
curl -X POST http://localhost:8080/api/projects \
  -H "Authorization: Bearer $TOKEN" -H "Content-Type: application/json" \
  -d '{"name":"Project A","description":"Test","ownerId":1}'

# List projects by owner
curl -H "Authorization: Bearer $TOKEN" \
  http://localhost:8080/api/projects/owner/1

# Create a task (replace projectId with created project's id)
curl -X POST http://localhost:8080/api/tasks \
  -H "Authorization: Bearer $TOKEN" -H "Content-Type: application/json" \
  -d '{"title":"Task 1","description":"Do it","projectId":1}'

# Get tasks for a project
curl -H "Authorization: Bearer $TOKEN" \
  http://localhost:8080/api/tasks/project/1

# Update task status
curl -X PATCH "http://localhost:8080/api/tasks/1/status?status=IN_PROGRESS" \
  -H "Authorization: Bearer $TOKEN"
```

Environment variables
- DB_URL, DB_USERNAME, DB_PASSWORD, DB_DRIVER
- JWT_SECRET, JWT_EXPIRATION_MS

Notes
- Delete endpoints are restricted to ADMIN role. By default, newly registered users are USER. For testing ADMIN-only routes, update a user's role to ADMIN in the database.

# Commit on 2024-09-18 at 12:57
Updated: 2024-09-18

# Commit on 2024-09-20 at 10:20
Updated: 2024-09-20

# Commit on 2024-09-20 at 17:18
Updated: 2024-09-20

# Commit on 2024-09-21 at 11:15
Updated: 2024-09-21

# Commit on 2024-09-22 at 12:04
Updated: 2024-09-22

# Commit on 2024-09-23 at 17:01
Updated: 2024-09-23

# Commit on 2024-09-23 at 14:00
Updated: 2024-09-23

