# Bell Curve Performance Appraisal System

This project consists of a backend built with **Spring Boot** and a frontend built with **HTML, CSS, and JavaScript** for employee performance analysis using the Bell Curve method.

## Requirements

- **Backend**:
  - Java 18 or higher
  - Maven
  - MySQL database
- **Frontend**:
  - HTML, CSS, JavaScript
  - VS Code (Recommended)
  - Live Server extension (Optional)

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

### Run the backend:

```sh
mvn spring-boot:run
```

The backend will be available at `http://localhost:8080`.

## API Endpoints

- **GET** `/api/bell-curve/analyze` - Get the Bell Curve analysis results.
- **POST** `/api/bell-curve/add` - Add a new employee.
- **POST** `/api/bell-curve/add-all` - Add multiple employees.
- **GET** `/api/bell-curve/employees` - Get a list of all employees.

## Frontend Setup

A frontend folder named **`bell-curve-frontend`** contains the UI built using HTML, CSS, and JavaScript.

### Run the Frontend in VS Code

1. Open VS Code and navigate to the `bell-curve-frontend` folder.
2. If using **Live Server**:
   - Install the [Live Server](https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer) extension.
   - Right-click `index.html` → **Open with Live Server**.
   - The frontend will run at `http://127.0.0.1:5500`.


### Ensure Backend and Frontend Communication

- The backend must be running on `http://localhost:8080`.
- CORS should be enabled in the backend:
  ```java
  @CrossOrigin(origins = "http://127.0.0.1:5500")
  ```

## Technologies Used

- **Backend**: Spring Boot, MySQL, Hibernate, REST API, Java 18
- **Frontend**: HTML, CSS, JavaScript

## License

This project is open-source and free to use.

---

Now you can run the frontend and backend together for a fully functional Bell Curve Performance Appraisal system. 🚀

