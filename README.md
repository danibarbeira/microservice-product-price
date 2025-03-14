# microservice-product-price

## Introduction

This is a GitHub repository for a technical test for the company Inditex.

Inditex owns a database that stores the prices of each of their products for each of their brands.

The test consists in the implementation of a service that exposes a REST API, with one endpoint.


The endpoint will allow clients to query the current price of any product from any of the Inditex brands at a given date.


## Use Case

The price of a product at a given date and time is determined by the retail price and the tariff applied to the product within a given start and end date.

The API will expose and endpoint that accepts as parameters the date and time at which the client wants to know a products price, the product identifier and the brand identifier of which that product belongs to.

As a result, the API should return a JSON that includes the product identifier, the brand identifier, tha tariff applied, the date range where the price is valid and tha final retail price of the product.

## Tech Stack

* Java 21
* H2 Database Engine
* Maven
* Spring Boot
* Spring Web, Spring Data JPA, Spring HATEOAS
* Lombok

## Architecture

The service will be implemented as a microservice with an internal structure that follows the architectural pattern of ports and adapters, also known as the hexagonal architecture.

## Building and deployment

Java 21 and Apache Maven are required to build and deploy the project. Simply run the following command from the projects root directory to clean any previous compilation files and compile, text and package the project:

```mvn clean install```

The project includes an embedded tomcat web server so running the project is hassle-free. Once built and packaged, it can be run from the project root directory using the following command:

```java -jar target/product-price-service-0.1.0.jar```