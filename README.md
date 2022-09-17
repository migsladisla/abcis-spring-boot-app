# Getting Started with ABCis Demo Spring Boot App

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/downloads/#java11)
- [Maven 3](https://maven.apache.org)

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

The gateway service runs on port 6999
The customer service runs on port 7000

Note: please make sure that `abcis` database (MySQL8) exists before running the app.

## Sample API Request

```
- [Fetch Company] GET http://localhost:6999/company-service/api/v1/companies/1
- [Fetch Employee] GET http://localhost:6999/company-service/api/v1/employees/1
- [Fetch Company Employees] GET http://localhost:6999/company-service/api/v1/companies/company/1/employees
```
