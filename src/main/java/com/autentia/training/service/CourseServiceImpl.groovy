package com.autentia.training.service

import com.autentia.training.domain.Course
import com.autentia.training.mapper.CourseMapper
import groovy.util.logging.Log4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("courseService")
@Log4j
class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper

    @Override
    List<Course> listActiveCourses() {
        log.debug('Retrieving a list of all active courses')
        return courseMapper.listActiveCourses()
    }

    @Override
    Course save(Course course) {
        log.debug("Saving the new course: ${course}")
        courseMapper.save(course)
        log.debug("The new course id is: ${course.id}")
        return course
    }
}
