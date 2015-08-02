package com.autentia.training.domain;

import java.io.Serializable;

public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Boolean active;
    private String teacher;
    private String title;
    private Integer hours;
    private Integer level;

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", active=" + active + ", teacher='" + teacher + "\'" + ", title='" + title + "\'" + ", hours=" + hours + ", level=" + level + "}";
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}


