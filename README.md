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

## Heroku notes
Unable to deploy due to mybatis error that I don't get locally

```
Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [com.autentia.training.mapper.CourseMapper] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
```

To access to console:
```
heroku run bash --app autentia-training-service
```
