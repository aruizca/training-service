package com.autentia.training.mapper

import com.autentia.training.domain.Course
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select

interface CourseMapper {

    @Select("SELECT * from training.course where active=true")
    public List<Course> listActiveCourses()

    @Insert("INSERT INTO training.course (active,teacher,title,hours,level) VALUES (#{active}, #{teacher}, #{title}, #{hours}, #{level})")
    public boolean save(Course course)

}