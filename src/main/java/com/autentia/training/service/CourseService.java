package com.autentia.training.service;

import com.autentia.training.domain.Course;

import java.util.List;

public interface CourseService {

    /**
     * @return a list of all active {@link Course}s
     */
    List<Course> listActiveCourses();

    /**
     * @param course
     * @return success
     */
    Course save(Course course);

}