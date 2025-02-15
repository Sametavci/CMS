# Cinema Management System (CMS)

## Introduction
This project is a **Cinema Management System (CMS)** developed as part of the **Backend Systems Module**. It is a **group software development project** where we demonstrate our ability to build a **backend system** using a structured software architecture. The system includes an **API**, a **database**, and follows the **Hexagonal Architecture** to ensure clean and modular code.

## Technologies Used
- **Programming Language:** Java (Spring Boot)
- **Software Architecture:** Hexagonal Architecture
- **API:** RESTful API
- **Database:** PostgreSQL
- **Persistence Framework:** Hibernate (JPA)
- **Build Tool:** Maven
- **Testing Frameworks:** JUnit, Mockito, Testcontainers
- **Containerization:** Docker

## Project Structure
The project follows **Hexagonal Architecture** with the following key components:

### **-Domain Component (Business Logic)**
### **-API Component (Controllers & Services)**
### **-Persistence Component (Repositories & Database)**

<pre>
├───.idea
│   ├───dataSources
│   └───shelf
│       ├──
│       └──
├───.mvn
│   └───wrapper
├───src
│   ├───main
│   │   ├───java
│   │   │   └───backend
│   │   │       ├───application
│   │   │       │   └───services
│   │   │       ├───domain
│   │   │       │   ├───controllers
│   │   │       │   ├───models
│   │   │       │   └───ports
│   │   │       │       └───repositorys
│   │   │       └───infrastructure
│   │   │           ├───api
│   │   │           │   ├───dto
│   │   │           │   └───mapper
│   │   │           └───persistence
│   │   │               ├───adapter
│   │   │               ├───entities
│   │   │               ├───mapper
│   │   │               └───repositorys
│   │   └───resources
│   │       ├───archetype-resources
│   │       │   └───src
│   │       │       ├───main
│   │       │       │   └───java
│   │       │       └───test
│   │       │           └───java
│   │       └───META-INF
│   │           └───maven
│   └───test
│       └───java
│           └───backend
│               ├───integrationTests
│               └───serviceTests
└───target
    ├───classes
    │   ├───archetype-resources
    │   │   └───src
    │   │       ├───main
    │   │       │   └───java
    │   │       └───test
    │   │           └───java
    │   ├───backend
    │   │   ├───application
    │   │   │   └───services
    │   │   ├───domain
    │   │   │   ├───controllers
    │   │   │   ├───models
    │   │   │   └───ports
    │   │   │       └───repositorys
    │   │   └───infrastructure
    │   │       ├───api
    │   │       │   ├───dto
    │   │       │   └───mapper
    │   │       └───persistence
    │   │           ├───adapter
    │   │           ├───entities
    │   │           ├───mapper
    │   │           └───repositorys
    │   └───META-INF
    │       └───maven
    ├───generated-sources
    │   └───annotations
    ├───generated-test-sources
    │   └───test-annotations
    ├───maven-archiver
    ├───maven-status
    │   └───maven-compiler-plugin
    │       ├───compile
    │       │   └───default-compile
    │       └───testCompile
    │           └───default-testCompile
    ├───surefire-reports
    └───test-classes
        └───backend
            ├───integrationTests
            └───serviceTests

</pre>


## API Features
- **CRUD Operations** for managing movies, halls, sessions, and seats.
- **Filtering & Paging** for retrieving data efficiently.
- **Hypermedia Principle (HATEOAS)** for RESTful API compliance.
- **Caching Strategy** to optimize performance.
- **Authentication & Authorization** (if necessary for the project).

## Setting Up & Running the Project
### **Clone the Repository**
```sh
git clone https://github.com/Sametavci/CMS.git
cd CMS
```

### **Build the Project**
```sh
mvn clean install
```

### **Start the Application**
#### **Using Maven**
```sh
mvn spring-boot:run
```

## Running Tests
### **Unit Tests**
```sh
mvn test
```

### **Integration Tests**
```sh
mvn verify
```

## Deployment & Docker Support
The system is designed to run inside a **Docker container**. To start the system via Docker:
1. Ensure Docker is installed.
2. Run the following command:
```sh
docker-compose up --build
```
This will start the backend and the PostgreSQL database.

#### **If Docker Does Not Run Up Please Try:**
```sh
docker run -d --name database --network cms_network -p 5433:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=cms postgres:15
docker run --network cms_network -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:postgresql://database:5432/cms -e SPRING_DATASOURCE_USERNAME=admin -e SPRING_DATASOURCE_PASSWORD=admin cms_app
```

## Deliverables
1. **Git Repository:**
   - URL: [https://github.com/Sametavci/CMS](https://github.com/Sametavci/CMS)
2. **Screencast Video:** https://cloud.thws.de/s/XxwRw5zTeej45HA
3. **Contributions:**
   - Cihan CAN, 5123010
   - Umut AVCI, 5123064
   - Samet AVCI, 5123065
   - Mumtaz ERDOGAN 5123036
