package com.autentia.training.service

import com.autentia.training.domain.Course

interface CourseService {

    /**
     * @return a list of all active {@link Course}s
     */
    public List<Course> listActiveCourses()

    /**
     * @param course
     * @return success
     */
    public boolean save(Course course)

}