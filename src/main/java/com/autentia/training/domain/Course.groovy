package com.autentia.training.domain

class Course implements Serializable {

    static final long serialVersionUID = 1L
    Long id
    Boolean active
    String teacher
    String title
    Integer hours
    Integer level

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", active=" + active +
                ", teacher='" + teacher + '\'' +
                ", title='" + title + '\'' +
                ", hours=" + hours +
                ", level=" + level +
                '}';
    }
}
