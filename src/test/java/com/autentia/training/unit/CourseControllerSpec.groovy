package com.autentia.training.unit

import com.autentia.training.controller.CourseController
import com.autentia.training.domain.Course
import com.autentia.training.service.CourseService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.http.MediaType.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * This tests are not needed as they are basically testing the Spring MVC works as intended
 * I just wanted to show how to do unit tests on Controllers with Spock
 * IF I has some sort of input validation, that would be something worth while testing
 */
class CourseControllerSpec extends Specification {

    CourseService courseService = Mock()

    def controller = new CourseController(courseService: courseService)

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

    def 'Test the /list endpoint'() {
        given: "mocked service"
        Course course = new Course(
                id: 1,
                active: true,
                teacher: "Roberto Canales",
                title: "Curso TDD",
                hours: 15,
                level: 1
        )
        courseService.listActiveCourses() >> [course]

        when: "when we request the mock list through get"
        def response = mockMvc.perform(get('/api/course/list'))

        then: "we get a valid JSON response"
        response.andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(content().json("[{\"id\":1,\"active\":true,\"teacher\":\"Roberto Canales\",\"title\":\"Curso TDD\",\"hours\":15,\"level\":1}]"))

        when: "we try to request the list using POST verb"
        response = mockMvc.perform(post('/api/course/list'))

        then: "server denies"
        response.andExpect(status().is4xxClientError())
    }
}
