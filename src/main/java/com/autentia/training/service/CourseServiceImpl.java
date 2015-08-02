package com.autentia.training.service;

import com.autentia.training.domain.Course;
import com.autentia.training.mapper.CourseMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseService")
class CourseServiceImpl implements CourseService {

    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    @Qualifier("courseMapper")
    private CourseMapper courseMapper;

    @Override
    public List<Course> listActiveCourses() {
        return courseMapper.listActiveCourses();
    }

    @Override
    public Course save(Course course) {
        log.debug("Saving the new course: ${course}");
        courseMapper.save(course);
        log.debug("The new course id is: ${course.id}");
        return course;
    }

    public CourseMapper getCourseMapper() {
        return courseMapper;
    }

    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }
}
