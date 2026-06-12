# SmartHire Backend

## Overview

SmartHire Backend is a microservices-based job portal application developed using Spring Boot. It provides secure authentication, user  profile management, job posting, and application management functionalities through independent microservices.

## Microservices

### Eureka Server

* Service discovery and registration for all microservices.

### API Gateway

* Single entry point for client requests.
* Routes requests to appropriate microservices.

### Auth Service

* User registration and login.
* JWT token generation and validation.

### User Service

* Candidate profile management.
* Recruiter profile management.
* Resume upload functionality.

### Job Service

* Create, update, delete, and view job postings.

### Application Service

* Apply for jobs.
* View applications.
* Update application status.
* Recruiter application management.

## Technologies Used

* Java 17
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Cloud Gateway
* Eureka Server
* OpenFeign
* Spring Data JPA
* MySQL
* Maven

## Features

* Microservices Architecture
* Service Discovery using Eureka
* API Gateway Routing
* JWT-Based Authentication
* Role-Based Authorization
* Candidate Profile Management
* Resume Upload
* Job Management
* Inter-Service Communication using OpenFeign

## Project Structure


SmartHire-Backend
├── eureka-server
├── api-gateway
├── smarthire-auth-service
├── smarthire-user-service
├── smarthire-job-service
└── smarthire-application-service


## How to Run

1. Start Eureka Server
2. Start API Gateway
3. Start Auth Service
4. Start User Service
5. Start Job Service
6. Start Application Service

## Future Enhancements

* Email Notifications
* Interview Scheduling
* Advanced Job Search Filters
* Admin Dashboard
* Analytics and Reporting


 
