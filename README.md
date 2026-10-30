# Bakhtjan Apsatarov — Student API

**Author:** Bakhtjan Apsatarov
Project status: initial version

## Description
Spring Boot CRUD REST API for managing students. Data is stored in PostgreSQL.
The project is containerised with Docker / Docker Compose and built by a Jenkins pipeline.

## Endpoints
| Method | URL | Description |
|--------|-----|-------------|
| GET | /api/students | list all students |
| GET | /api/students/{id} | get one student |
| POST | /api/students | create a student |
| PUT | /api/students/{id} | update a student |
| DELETE | /api/students/{id} | delete a student |
| GET | /api/info | application name, student, version |

## Run
```bash
cp .env.example .env        # then set your own POSTGRES_PASSWORD
docker compose up -d --build
curl http://localhost:8081/api/info
```
