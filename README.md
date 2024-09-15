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

# Commit on 2024-09-23 at 11:58
Updated: 2024-09-23

# Commit on 2024-09-25 at 14:24
Updated: 2024-09-25

# Commit on 2024-09-26 at 10:02
Updated: 2024-09-26

# Commit on 2024-09-27 at 15:49
Updated: 2024-09-27

# Commit on 2024-10-06 at 10:19
Updated: 2024-10-06

# Commit on 2024-10-07 at 11:14
Updated: 2024-10-07

# Commit on 2024-10-07 at 18:52
Updated: 2024-10-07

# Commit on 2024-10-09 at 11:29
Updated: 2024-10-09

# Commit on 2024-10-10 at 15:15
Updated: 2024-10-10

# Commit on 2024-10-14 at 11:30
Updated: 2024-10-14

# Commit on 2024-10-14 at 18:28
Updated: 2024-10-14

# Commit on 2024-10-15 at 14:06
Updated: 2024-10-15

# Commit on 2024-10-15 at 11:05
Updated: 2024-10-15

# Commit on 2024-10-15 at 09:03
Updated: 2024-10-15

# Commit on 2024-10-16 at 13:00
Updated: 2024-10-16

# Commit on 2024-10-19 at 15:06
Updated: 2024-10-19

# Commit on 2024-11-03 at 16:08
Updated: 2024-11-03

# Commit on 2024-11-06 at 18:14
Updated: 2024-11-06

# Commit on 2024-11-07 at 12:52
Updated: 2024-11-07

# Commit on 2024-11-09 at 16:29
Updated: 2024-11-09

# Commit on 2024-11-18 at 16:55
Updated: 2024-11-18

# Commit on 2024-11-18 at 11:12
Updated: 2024-11-18

# Commit on 2024-11-19 at 14:49
Updated: 2024-11-19

# Commit on 2024-11-20 at 09:46
Updated: 2024-11-20

# Commit on 2024-11-20 at 14:55
Updated: 2024-11-20

# Commit on 2024-11-22 at 16:09
Updated: 2024-11-22

# Commit on 2024-11-22 at 14:07
Updated: 2024-11-22

# Commit on 2024-11-22 at 11:58
Updated: 2024-11-22

# Commit on 2024-11-23 at 15:54
Updated: 2024-11-23

# Commit on 2024-11-24 at 17:26
Updated: 2024-11-24

# Commit on 2024-11-26 at 10:44
Updated: 2024-11-26

# Commit on 2024-11-26 at 14:41
Updated: 2024-11-26

# Commit on 2024-11-29 at 11:44
Updated: 2024-11-29

# Commit on 2024-11-29 at 17:11
Updated: 2024-11-29

# Commit on 2024-11-30 at 10:37
Updated: 2024-11-30

# Commit on 2024-12-01 at 10:06
Updated: 2024-12-01

# Commit on 2024-12-02 at 13:50
Updated: 2024-12-02

# Commit on 2024-12-03 at 18:39
Updated: 2024-12-03

# Commit on 2024-12-03 at 13:56
Updated: 2024-12-03

# Commit on 2024-12-03 at 11:05
Updated: 2024-12-03

# Commit on 2024-12-04 at 14:31
Updated: 2024-12-04

# Commit on 2024-12-04 at 10:59
Updated: 2024-12-04

# Commit on 2024-12-06 at 13:25
Updated: 2024-12-06

# Commit on 2024-12-06 at 10:23
Updated: 2024-12-06

# Commit on 2024-12-07 at 12:29
Updated: 2024-12-07

# Commit on 2024-12-07 at 17:26
Updated: 2024-12-07

# Commit on 2024-12-07 at 13:04
Updated: 2024-12-07

# Commit on 2024-12-09 at 15:27
Updated: 2024-12-09

# Commit on 2024-12-10 at 10:13
Updated: 2024-12-10

# Commit on 2024-12-13 at 12:19
Updated: 2024-12-13

# Commit on 2024-12-13 at 09:17
Updated: 2024-12-13

# Commit on 2024-12-14 at 12:43
Updated: 2024-12-14

# Commit on 2024-12-15 at 10:55
Updated: 2024-12-15

# Commit on 2024-12-15 at 17:54
Updated: 2024-12-15

# Commit on 2024-12-15 at 10:20
Updated: 2024-12-15

# Commit on 2024-12-16 at 15:27
Updated: 2024-12-16

# Commit on 2024-12-17 at 09:05
Updated: 2024-12-17

# Commit on 2024-12-19 at 12:31
Updated: 2024-12-19

# Commit on 2024-12-19 at 10:30
Updated: 2024-12-19

# Commit on 2024-12-19 at 17:28
Updated: 2024-12-19

# Commit on 2024-12-21 at 12:54
Updated: 2024-12-21

# Commit on 2024-12-21 at 09:53
Updated: 2024-12-21

# Commit on 2024-12-29 at 12:11
Updated: 2024-12-29

# Commit on 2024-12-29 at 15:19
Updated: 2024-12-29

# Commit on 2024-12-30 at 10:26
Updated: 2024-12-30

# Commit on 2024-12-31 at 12:32
Updated: 2024-12-31

# Commit on 2024-12-31 at 16:18
Updated: 2024-12-31

# Commit on 2025-01-01 at 11:26
Updated: 2025-01-01

# Commit on 2025-01-03 at 14:52
Updated: 2025-01-03

# Commit on 2025-01-03 at 11:01
Updated: 2025-01-03

# Commit on 2025-01-04 at 15:47
Updated: 2025-01-04

# Commit on 2025-01-04 at 13:46
Updated: 2025-01-04

# Commit on 2025-01-19 at 13:20
Updated: 2025-01-19

# Commit on 2025-01-19 at 11:19
Updated: 2025-01-19

# Commit on 2025-01-20 at 15:26
Updated: 2025-01-20

# Commit on 2025-01-21 at 15:40
Updated: 2025-01-21

# Commit on 2025-01-21 at 13:49
Updated: 2025-01-21

# Commit on 2025-01-23 at 15:55
Updated: 2025-01-23

# Commit on 2025-01-24 at 18:02
Updated: 2025-01-24

# Commit on 2025-01-25 at 12:18
Updated: 2025-01-25

# Commit on 2025-01-26 at 12:06
Updated: 2025-01-26

# Commit on 2025-01-29 at 14:12
Updated: 2025-01-29

# Commit on 2025-01-30 at 09:19
Updated: 2025-01-30

# Commit on 2025-01-31 at 13:06
Updated: 2025-01-31

# Commit on 2025-02-02 at 18:05
Updated: 2025-02-02

# Commit on 2025-02-02 at 14:51
Updated: 2025-02-02

# Commit on 2025-02-04 at 14:57
Updated: 2025-02-04

# Commit on 2025-02-06 at 17:34
Updated: 2025-02-06

# Commit on 2025-02-06 at 13:20
Updated: 2025-02-06

# Commit on 2025-02-08 at 15:07
Updated: 2025-02-08

# Commit on 2025-02-08 at 18:23
Updated: 2025-02-08

# Commit on 2025-02-08 at 15:21
Updated: 2025-02-08

# Commit on 2025-02-10 at 16:20
Updated: 2025-02-10

# Commit on 2025-02-10 at 13:18
Updated: 2025-02-10

# Commit on 2025-02-10 at 10:17
Updated: 2025-02-10

# Commit on 2025-02-12 at 12:12
Updated: 2025-02-12

# Commit on 2025-02-12 at 09:10
Updated: 2025-02-12

# Commit on 2025-02-13 at 16:48
Updated: 2025-02-13

# Commit on 2025-02-13 at 13:06
Updated: 2025-02-13

# Commit on 2025-02-13 at 17:02
Updated: 2025-02-13

# Commit on 2025-02-16 at 12:32
Updated: 2025-02-16

# Commit on 2025-02-16 at 17:40
Updated: 2025-02-16

# Commit on 2025-02-17 at 11:37
Updated: 2025-02-17

# Commit on 2025-02-17 at 18:35
Updated: 2025-02-17

# Commit on 2025-02-18 at 13:32
Updated: 2025-02-18

# Commit on 2025-02-20 at 16:08
Updated: 2025-02-20

# Commit on 2025-02-22 at 16:14
Updated: 2025-02-22

# Commit on 2025-02-22 at 14:42
Updated: 2025-02-22

# Commit on 2025-02-24 at 11:11
Updated: 2025-02-24

# Commit on 2024-09-15 at 13:13
Updated: 2024-09-15

# Commit on 2024-09-15 at 10:11
Updated: 2024-09-15

# Commit on 2024-09-15 at 17:09
Updated: 2024-09-15

