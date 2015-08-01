package com.autentia.training.controller

import com.autentia.training.domain.Course
import com.autentia.training.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/course")
class CourseController {

    @Autowired
    CourseService courseService

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody List<Course> listActiveCourses() {
        return courseService.listActiveCourses()
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody Course save(@RequestBody Course course) {
        return courseService.save(course)
    }

}