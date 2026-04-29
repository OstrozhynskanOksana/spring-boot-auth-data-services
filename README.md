# Spring Boot Auth and Data Services

  A small microservice-based Spring Boot project with authentication, JWT security, PostgreSQL persistence, and an internal data transformation service.

  ## Services

  ### auth-api

  Runs on port 8080.

  Responsibilities:

  - User registration
  - User login
  - Password encoding with BCrypt
  - JWT generation and validation
  - Protected /api/process endpoint
  - Saves processing logs to PostgreSQL

  ### data-api

  Runs on port 8081.

  Responsibilities:

  - Internal text transformation endpoint
  - Accepts requests only with a valid internal token
  - Transforms input text to uppercase

  ### PostgreSQL

  Runs on port 5432.

  Used by auth-api for storing users and processing logs.
  

  ## Tech Stack

  - Java 21
  - Spring Boot 4
  - Spring Security
  - Spring Data JPA
  - PostgreSQL
  - H2 for tests
  - JWT 
  - Lombok
  - Docker Compose
  - Gradle
    

  ## API Endpoints

  ### Register User

  `POST /api/auth/register`

  Request body:
```
  {
    "email": "test@example.com",
    "password": "password123"
  }
```

  Response:
  
```
  {
    "token": "jwt-token"
  }
```

  ### Login User

 `POST /api/auth/login`

  Request body:
  
```
  {
    "email": "test@example.com",
    "password": "password123"
  }
```

  Response:
  
```
  {
    "token": "jwt-token"
  }
```

  ### Process Text

  `POST /api/process`

  Requires JWT authorization.

  Headers:

  Authorization: Bearer jwt-token

  Request body:

```
  {
    "text": "hello world"
  }
```

  Response:
  
```
  {
    "result": "HELLO WORLD"
  }
```

  ### Internal Data API Endpoint

  `POST /api/transform`

  Used internally by auth-api.
  
  Headers:

  X-Internal-Token: secret123

  Request body:
  
```
  {
    "text": "hello world"
  }
```

  Response:
  
```
  {
    "text": "HELLO WORLD"
  }
```

  ## Running with Docker Compose

  Before starting Docker Compose, build the application jars:
  
```
  cd auth-api
  ./gradlew bootJar

  cd ../data-api
  ./gradlew bootJar
```

  Then start all services from the project root:
  
```
  docker compose up --build
```

  Services will be available at:

  - Auth API: http://localhost:8080
  - Data API: http://localhost:8081
  - PostgreSQL: localhost:5432

  To stop the services:
  
```
  docker compose down
```

  To remove PostgreSQL data volume as well:

```
  docker compose down -v
```


  ## Environment Variables

  ### auth-api

  | Variable | Description |
  |---|---|
  | SPRING_DATASOURCE_URL | PostgreSQL JDBC URL |
  | SPRING_DATASOURCE_USERNAME | PostgreSQL username |
  | SPRING_DATASOURCE_PASSWORD | PostgreSQL password |
  | JWT_SECRET | Secret key for signing JWT tokens |
  | JWT_EXPIRATION | JWT expiration time in hours |
  | INTERNAL_TOKEN | Token used for internal service communication |
  | DATA_API_URL | URL of the data-api service |

  ### data-api

  | Variable | Description |
  |---|---|
  | INTERNAL_TOKEN | Token required for internal API requests |

