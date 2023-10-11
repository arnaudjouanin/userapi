# User API - Spring Boot Application

## Overview
This is a simple Spring Boot application that provides a RESTful API for managing user data.
It includes endpoints for user registration and fetching user details.

## Table of Contents
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
    - [Running the Application](#running-the-application)
    - [API Endpoints](#api-endpoints)

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven (for build management)
- Postman (for testing APIs)

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven (for building the application)

### Installation
1. Clone this repository:
   ```shell
   git clone https://github.com/.........../user-api.git

2. Build the application using Maven:
   ```shell
   mvn clean install
   
## Usage

### Running the Application
3. Execute the following command to start the Spring Boot application:
    ```shell
    mvn spring-boot:run

### API Endpoints
The Tomcat server port is by default: 8080

The application exposes the following API endpoints:
- POST /api/user/register: Register a new user.
- GET  /api/user/{username}: Retrieve user details by username.

