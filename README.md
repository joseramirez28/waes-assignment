# waes-assignment

Microservice for WAES Interview Assigment "Scalable Web"

This project can be tested by only starting the microservice, is using some of the latest tools like Spring Boot 2.0, Swagger and MongoDB(Embedded), thinking about scalability this project can be updated by configuration to upgrade or change dependencies or capabilities in less time

Usage
======
* Run WaesRunner class to Start the SpringBoot Service
* Call "left" endpoint with `http://localhost:<port>/v1/diff/<id>/left` i.e `http://localhost:8088/v1/diff/42/left`
* Call "right" endpoint with `http://localhost:<port>/v1/diff/<id>/right` i.e `http://localhost:8088/v1/diff/42/right`
* Call "diff" endpoint with `http://localhost:<port>/v1/diff/<id>` i.e `http://localhost:8088/v1/diff/42`

Microservice Basic Configuration
======

* Change port: update property port inside `application.yml` (default : 8088)
* This Microservice has configured an embedded MongoDB for portability and Plug and Play style.
*

Integration Tests
======
* Integration tests included to test all the endpoints

Swagger
======
For Endpoint documentation and testing in Browser access http://localhost:<port>/swagger-ui.html