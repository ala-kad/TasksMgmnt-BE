# Spring Task Management Application

This document describes a Spring Boot application that provides a REST API for managing tasks.

## Running the Application

**Prerequisites:**

  * Java 17+ ([Download JDK](https://www.oracle.com/java/technologies/javase/downloads/))
  * Maven 4.0.0 ([Download Maven](https://maven.apache.org/download.cgi))
  * Spring Boot 3.4.1

**Steps:**

1. Clone this repository.
2. Open a terminal in the project directory.
3. Run `mvn clean install` to build the application.
4. 
5. Run `mvn spring-boot:run` to start the application.

**By default, the application will run on port 8080.** You can access the API endpoints at `http://localhost:8080/tasks`.

## Implemented APIs

The backend exposes the following REST API endpoints for managing tasks:

  * **GET /tasks:** Retrieves all tasks. Optionally filter by completion status and sort by creation date.
      * Query parameters:
          * `completed` (boolean): Filter tasks by completion status (true/false).
          * `sortBy` (string): Sort field (e.g., "creationDate", "name").
          * `sortOrder` (string): Sort order ("asc" or "desc").
  * **POST /tasks:** Creates a new task.
  * **PUT /tasks/{id}**: Updates an existing task.
  * **DELETE /tasks/{id}**: Deletes a task by ID.

**Request body for POST and PUT requests:**

The request body should be a JSON object representing the task data. The expected format is:

```json
{
  "name": "string",
}
