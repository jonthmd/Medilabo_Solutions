# Medilabo Solutions

Spring Boot application based on a microservices architecture enabling patients management with their associated notes
and their risk evaluation.

---

## Getting Started

These instructions allow you to retrieve and run the project
for development or testing.

### Prerequisites

Required software :

- Java 21.0.9
- Spring 4.0.6
- Maven 3.9.11
- MySQL 9.5.0
- MySQL Workbench 8.0.45 if necessary
- MongoDB 8.2.7
- MongoDB Compass 1.49.8 if necessary
- Docker Desktop 4.76.0

### Installing

1 - Install Java : https://www.oracle.com/java/technologies/downloads/#java21.

2 - Install Maven : https://maven.apache.org/install.html.

3 - Install MySQL : https://dev.mysql.com/downloads/mysql/.

4 - Install MySQL Workbench : https://dev.mysql.com/downloads/workbench/.

5 - Install MongoDB : https://www.mongodb.com/docs/manual/administration/install-community/

6 - Install MongoDB Compass : https://www.mongodb.com/fr-fr/products/tools/compass

7 - Install Docker Desktop : https://docs.docker.com/desktop/setup/install/mac-install/

8 - Clone the project on your local machine.

---

### Running the app

--

Use the following account to use the application :

Username/Password : user/user

--

On local :

1 - Run the Medilabo Solutions app via your IDE by running microservices in the following
orders : Eureka, Patient, Note, Risk, Gateway, Front.

2 - On your browser, go to the front.

OR 

With Docker :

1 - Start Docker.

2 - Open a terminal and go to the project folder.

3 - Enter the command "docker compose up --build" to build and run the app.

4 - On your browser, go to the front.

5 - Enter the command "docker compose down" to stop the app.

### Importing data

Using the initDatabase classes, the application will generate data when it is launched for the first time.
Otherwise, use file in docs folders, if necessary.

### Microservices

Eureka - http://localhost:8761

Gateway - http://localhost:8080

Patient - http://localhost:8081/swagger-ui/index.html#/ (Swagger access.)

Note - http://localhost:8082/swagger-ui/index.html#/ (Swagger access.)

Risk - http://localhost:8083/swagger-ui/index.html#/ (Swagger access.)

Front - http://localhost:8084

### Databases

MySQL for Patient.

MongoDB for Note.

### Testing the app

In your IDE, right-click the root folder and select “Run all tests” for the module you want to test.

OR

Enter the command "mvn clean test" on the module you want to test in the terminal.




