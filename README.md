# Getting Started with ABCis Demo Spring Boot App

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/downloads/#java11)
- [Maven 3](https://maven.apache.org)
- [MySQL8](https://dev.mysql.com/downloads/mysql/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `gateway.GatewayServiceApplication` and `company.CompanyServiceApplication` classes from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

Gateway Service:
```shell
cd gateway-service
mvn spring-boot:run
```

Customer Service:
```shell
cd customer-service
mvn spring-boot:run
```

## Project Setup

The gateway service runs on port `6999`.

Required app env vars for gateway service: ```DB_NAME=abcis;PROFILE=;DB_PASSWORD=password;DB_USER=root```

The customer service runs on port `7000`.

Required app env vars for customer service: ```COMPANY_SERVICE_URL=http://localhost:7000/company-service```

Note: Please make sure that `abcis` database (MySQL8) exists before running the app.

The SQL script to create schema and tables is located in the root project folder.

## Sample API Requests

```
- [Fetch Company] GET http://localhost:6999/company-service/api/v1/companies/1
- [Fetch Employee] GET http://localhost:6999/company-service/api/v1/employees/1
- [Fetch Company Employees] GET http://localhost:6999/company-service/api/v1/companies/company/1/employees
```
