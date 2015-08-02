Training Service ![Travis CI build](https://travis-ci.org/aruizca/training-service.svg?branch=master)
=====

## Technologies used
 * Spring Boot 1.2.5
 * Spring 4.1.7
 * Java 1.7.0_75
 * Groovy 2.3.11
 * Spock 1.0 for unit and integrated tests
 * MyBatis 3.3.0
 * H2 1.4.187
 * Maven 3.3.3

## To run packaged artifact
```
java -Dserver.port=8080 -jar target/training-service-0.1-SNAPSHOT.jar
```

## Heroku info
Service deployed at: http://autentia-training-service.herokuapp.com

Endpoints available:
* List courses (GET)
```
/training-service/api/course/list
```


* Save new course (POST)
```
/training-service/api/course/list
```

Accepts JSON payload. Eg:
 ```
 {
   "active": true,
   "teacher": "Angel Ruiz",
   "title": "Curso TDD",
   "hours": 15,
 }
 ```
 
