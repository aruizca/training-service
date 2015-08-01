package com.autentia.training.unit

import com.autentia.training.controller.CourseController
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.http.MediaType.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


class CourseControllerSpec extends Specification {

    def controller = new CourseController()

    def mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

    def 'Test the list of courses ia available'() {
        when:
        def response = mockMvc.perform(get('/api/course/list'))

        then:
        response.andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json("{\"key\":\"value\"}"))
    }
}
