package com.autentia.training.service

import com.autentia.training.domain.Course
import com.autentia.training.mapper.CourseMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("courseService")
class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper

    @Override
    List<Course> listActiveCourses() {
        return courseMapper.listActiveCourses()
    }

    @Override
    boolean save(Course course) {
        return courseMapper.save(course)
    }
}
