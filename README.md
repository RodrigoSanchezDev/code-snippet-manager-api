# Snippet API

**Snippet API** is a RESTful microservice built with Spring Boot that allows developers to create, read, update, delete, and search code snippets with metadata such as language, tags, and descriptions.

---

## Screenshots

### Create Snippet Success
This screenshot shows a successful creation of a code snippet via Postman.

![Create Snippet Success](images/create_snippet_success.png)

### Tests Passing
Unit and integration tests running successfully.

![Tests Passing](images/tests_passing.png)

---

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Actuator](#actuator)
- [License](#license)

---

## Features

- CRUD operations for code snippets  
- Search by language or tag  
- Input validation with `javax.validation`  
- In-memory H2 database for quick startup  
- Automatic API documentation with Springdoc OpenAPI (Swagger UI)  
- Monitoring endpoints via Spring Boot Actuator  

---

## Prerequisites

- Java 21  
- Maven 3.8+  

---

## Installation

1. Clone the repository:  
   ```bash
   git clone https://github.com/sanchezdev/code-snippet-manager-api.git
   cd code-snippet-manager-api
   ```

2. Build the project:  
   ```bash
   mvn clean package
   ```

---

## Configuration

Configuration is managed in `src/main/resources/application.properties`. Key settings include:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
management.endpoints.web.exposure.include=health,info,env
info.app.name=Snippet API
info.app.version=0.0.1-SNAPSHOT
```

---

## Running the Application

Start the service with:

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## API Endpoints

| Method | Path                                  | Description                       |
| ------ | ------------------------------------- | --------------------------------- |
| POST   | `/api/snippets`                       | Create a new snippet              |
| GET    | `/api/snippets`                       | List all snippets                 |
| GET    | `/api/snippets/{id}`                  | Get snippet by ID                 |
| PUT    | `/api/snippets/{id}`                  | Update snippet by ID              |
| DELETE | `/api/snippets/{id}`                  | Delete snippet by ID              |
| GET    | `/api/snippets/search?language=Java`  | Search snippets by language       |
| GET    | `/api/snippets/search?tag=java`       | Search snippets by tag            |

---

## Testing

Run unit and integration tests with:

```bash
mvn clean test
```

---

## Actuator

Health, info, and env endpoints:

- **Health**: `GET /actuator/health`  
- **Info**: `GET /actuator/info`  
- **Env**: `GET /actuator/env`

---

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

---

Created by **Rodrigo Sanchez** — [sanchezdev.com](https://sanchezdev.com)
