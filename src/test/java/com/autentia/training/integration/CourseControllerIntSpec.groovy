package com.autentia.training.integration

import com.autentia.training.Application
import com.autentia.training.config.DataConfig
import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification
import spock.lang.Unroll

import javax.sql.DataSource

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = Application.class)
@WebAppConfiguration
@IntegrationTest
class CourseControllerIntSpec extends Specification {

    RESTClient client

    @Autowired
    DataSource dataSource

    def setup() {
        client = new RESTClient('http://localhost:8080/training-service/api/course/')
    }

    def cleanup() {
        DataConfig.initializeDB(dataSource)
    }

    def "test we can retrieve the preloaded courses in JSON array"() {
        when: "we request the list of courses"
        def response = client.get(path: 'list')

        then: "we get the courses in a JSON array that gets parsed into a list whose size is 44"
        response.status == 200
        response.contentType == MediaType.APPLICATION_JSON_VALUE
        response.data instanceof List
        response.data?.size() == 44
    }

    @Unroll
    def "test saving a course"() {
        given: "the current total number of courses"
        def response = client.get(path: 'list')
        def totalCourses = response.data.size()

        when: "we make a post request with a valid JSON payload"
        response = client.post(
                path: 'save',
                body: [
                        active : true,
                        teacher: 'Angel Ruiz',
                        title  : 'Using Spring Boot to develop webapps',
                        hours  : 8,
                        level  : 2
                ],
                requestContentType: MediaType.APPLICATION_JSON_VALUE
        )
        then: "the course is inserted into the DB and we get back the course data with the assigned id"
        response.status == 200
        response.contentType == MediaType.APPLICATION_JSON_VALUE
        response.data.id != null
        response.data.id instanceof Integer
        response.data.active == true
        response.data.teacher == 'Angel Ruiz'
        response.data.title == 'Using Spring Boot to develop webapps'
        response.data.hours == 8
        response.data.level == 2

        when: "if we get the all the active courses again"
        response = client.get(path: 'list')

        then: "the current total number of courses has been increased by 1"
        response.data?.size() == totalCourses + 1

        when: "we try to save and invalid JSON payload"
        response = client.post(
                path: 'save',
                body: [
                        active : true,
                ],
                requestContentType: MediaType.APPLICATION_JSON_VALUE
        )
        then: "we get an exception"
        thrown(Exception)
    }

}
