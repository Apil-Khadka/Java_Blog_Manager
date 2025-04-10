# Blog Manager API

The **Blog Manager API** is a Spring Boot application that provides RESTful endpoints for managing users and posts in a blogging platform.

## Prerequisites

- Java 17+
- Maven
- PostgreSQL

## Configuration

Update the `src/main/resources/application.properties` file with your database credentials.

## API Endpoints

### User API

Base URL: `/api/v1/user`

| Method   | Endpoint         | Description                  | Request Body         |
|----------|------------------|------------------------------|----------------------|
| `GET`    | `/api/v1/user`   | Get all users                | N/A                  |
| `GET`    | `/api/v1/user/{id}` | Get a user by ID            | N/A                  |
| `POST`   | `/api/v1/user`   | Create a new user            | JSON representation of a `User` object |
| `PUT`    | `/api/v1/user/{id}` | Update an existing user by ID | JSON representation of a `User` object |
| `DELETE` | `/api/v1/user/{id}` | Delete a user by ID         | N/A                  |

### Post API

Base URL: `/api/v1/posts`

| Method   | Endpoint           | Description                  | Request Body         |
|----------|--------------------|------------------------------|----------------------|
| `GET`    | `/api/v1/posts`    | Get all posts                | N/A                  |
| `GET`    | `/api/v1/posts/{id}` | Get a post by ID            | N/A                  |
| `POST`   | `/api/v1/posts`    | Create a new post            | JSON representation of a `Post` object |
| `PUT`    | `/api/v1/posts/{id}` | Update an existing post by ID | JSON representation of a `Post` object |
| `DELETE` | `/api/v1/posts/{id}` | Delete a post by ID         | N/A                  |

## Models

### User

```json
{
  "username": "johndoe",
  "email": "john.doe@example.com",
   "password": "password123"
}
```

### Post

```json
{
  "id": 1,
  "title": "My First Blog Post",
  "content": "This is the content of the blog post.",
  "user_id": 1
}
```

## Running the Application

1. Clone the repository.
2. Build the project using Maven:
   ```bash
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

## License

This project is licensed under the MIT License.