# Blog Manager API

The **Blog Manager API** is a Spring Boot application that provides RESTful endpoints for managing users and posts in a
blogging platform.

## Prerequisites

- Java 17+
- Maven
- PostgreSQL

## Configuration

Update the `src/main/resources/application.properties` file with your database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## API Documentation

### Swagger/OpenAPI

The API documentation is available through Swagger UI:

- **URL**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

This interactive documentation allows you to explore and test all available endpoints directly from your browser.

## API Endpoints

### User API

Base URL: `/api/v1/user`

| Method   | Endpoint                | Description                   | Request Body                           |
|----------|-------------------------|-------------------------------|----------------------------------------|
| `GET`    | `/api/v1/user`          | Get all users                 | N/A                                    |
| `GET`    | `/api/v1/user/{id}`     | Get a user by ID              | N/A                                    |
| `POST`   | `/api/v1/user/register` | Register a new user           | JSON representation of a `User` object |
| `POST`   | `/api/v1/user/login`    | Login a user                  | JSON representation of a `User` object |
| `PUT`    | `/api/v1/user/{id}`     | Update an existing user by ID | JSON representation of a `User` object |
| `DELETE` | `/api/v1/user/{id}`     | Delete a user by ID           | N/A                                    |

### Post API

Base URL: `/api/v1/posts`

| Method   | Endpoint                  | Description                      | Request Body                           |
|----------|---------------------------|----------------------------------|----------------------------------------|
| `GET`    | `/api/v1/posts`           | Get all posts                    | N/A                                    |
| `GET`    | `/api/v1/posts/{id}`      | Get a post by ID                 | N/A                                    |
| `GET`    | `/api/v1/posts/user/{id}` | Get all posts by a specific user | N/A                                    |
| `POST`   | `/api/v1/posts`           | Create a new post                | JSON representation of a `Post` object |
| `PUT`    | `/api/v1/posts/{id}`      | Update an existing post by ID    | JSON representation of a `Post` object |
| `DELETE` | `/api/v1/posts/{id}`      | Delete a post by ID              | N/A                                    |

## Models

### User

```json
{
  "username": "johndoe",
  "password": "password123"
}
```

### Post

```json
{
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
4. Access the API at [http://localhost:8080](http://localhost:8080)
5. View the API documentation
   at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Development

### Testing

Run the tests using Maven:

```bash
./mvnw test
```

### Building for Production

Create an optimized build:

```bash
./mvnw clean package -P production
```

## License

This project is licensed under the MIT License.
