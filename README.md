# Online Course Management System

## Overview

The Online Course Management System is a RESTful backend application designed to manage a digital learning environment.
It allows the administration of students, instructors, courses, lessons, enrollments, evaluations, certificates, and course categories.

This project was developed with an academic focus, aiming to demonstrate good practices in backend development, object-oriented modeling, and relational database design using the Spring ecosystem.

---

## Project Goals

- Apply object-oriented design and domain modeling
- Implement business rules beyond basic CRUD
- Practice relational database relationships, including composite keys
- Use Spring Boot and Spring Data JPA in a real-world-like scenario
- Document APIs using OpenAPI / Swagger

---

### Domain Model (High-Level)
The domain was designed to cover multiple real-world relationships:

- Students can enroll in multiple courses
- Courses are created and managed by instructors
- Courses contain multiple lessons
- Courses can belong to multiple categories
- Students can evaluate courses
- Each enrollment can generate a certificate after course completion

The system includes:
- 1:1, 1:N, and N:N relationships
- Composite primary keys where foreign keys are also primary keys
- Business processes such as course completion and certificate generation

---

## Skills & Concepts Applied
### Backend Development

- RESTful API design
- Layered architecture (Controller, Service, Repository)
- DTOs and entity mapping
- Validation and exception handling

### Spring Ecosystem

- Spring Boot 3
- Spring Data JPA / Hibernate
- Spring Security
- OpenAPI / Swagger Documentation

### Database & Persistence

- Relational modeling
- Composite keys
- H2 in-memory database
- Entity relationships and constraints

### Messaging

- ActiveMQ integration for asynchronous communication

### Software Engineering

- Clean code principles
- Separation of concerns
- Business rule implementation
- Academic-level system design

---

## Technologies Used

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- H2 Database
- ActiveMQ
- OpenAPI (Swagger)
- Lombok
- Maven

---

## Team Members

- Bruno Leonardo Silva
- Caique de Oliveira Castro
- Heitor Pereira de Lucena
- Mariana Vidal Vaz
- William Vieira de Sousa
