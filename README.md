# Bell Curve Performance Appraisal - Backend

This is the backend for the Bell Curve Performance Appraisal system, built using Spring Boot. It provides API endpoints for handling employee data, performing Bell Curve analysis, and suggesting performance adjustments.

## Requirements
- Java 18 or higher
- Maven
- MySQL database

## Backend Setup
### Clone the repository:
```sh
git clone <repository-url>
cd <project-folder>
```

### Set up MySQL database:
1. Create a MySQL database (e.g., `assignment`).
2. Update `application.properties` with your database credentials.

### Install dependencies:
```sh
mvn clean install
```

### Configure `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/assignment
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Run the application:
```sh
mvn spring-boot:run
```
The backend will be available at `http://localhost:8080`.

## API Endpoints
- **GET** `/api/bell-curve/analyze` - Get the Bell Curve analysis results.
- **POST** `/api/bell-curve/add` - Add a new employee.
- **POST** `/api/bell-curve/add-all` - Add multiple employees.
- **GET** `/api/bell-curve/employees` - Get a list of all employees.

## Environment Variables
Set the following variables in `application.properties`:
```properties
spring.datasource.url=your_database_url
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password
```

## Technologies Used
- Spring Boot
- MySQL
- Hibernate
- REST API
- JAVA 18

## License
This project is open-source and free to use.

