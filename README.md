# StoreAPI
This project is a Spring Boot application for managing products and their categories. It provides endpoints for creating, retrieving, updating, and deleting products and categories. The project is built using Java, Spring Boot, Maven, and Docker. This app is based on a DevSuperior's project.

## Requirements
- Java 17
- Maven
- Docker

## Installation
1. Clone this repository:
```bash
git clone https://github.com/JoaoBarroso4/bootcamp-devsuperior.git
```
2. Navigate to the project's root directory.
3. Install the required dependencies
```bash
mvn install
```
4. Configure your database connection in `application.properties` file.
5. Run docker-compose to start the PostgreSQL container
```bash
docker-compose up -d
```
6. Run the application. Seeds will be automatically inserted into the database.
```bash
mvn spring-boot:run
```
The server will start running at `http://localhost:8080`.

## Endpoints

### Categories:
- `POST /categories/`: Create a new category.
- `GET /categories/{id}`: Retrieve a category by its ID.
- `GET /categories/`: Retrieve all categories.
- `PUT /categories/{id}`: Update a category by its ID.
- `DELETE /categories/{id}`: Delete a category by its ID.

### Products:
- `POST /products/`: Create a new product.
- `GET /products/{id}`: Retrieve a product by its ID.
- `GET /products/`: Retrieve all products.
- `PUT /products/{id}`: Update a product by its ID.
- `DELETE /products/{id}`: Delete a product by its ID.