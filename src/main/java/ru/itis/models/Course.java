package ru.itis.models;

import java.util.List;

public class Course {
    private Integer id;
    private String name;
    private String beginningDate;
    private String endingDate;
    private Teacher teacher;

    private List<Student> students;

    public Course(Integer id, String name, String beginningDate, String endingDate) {
        this.id = id;
        this.name = name;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
    }

    public Course(Integer id, String name, String beginningDate, String endingDate, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.teacher = teacher;
    }

    public Course(Integer id, String name, String beginningDate, String endingDate, Teacher teacher, List<Student> students) {
        this.id = id;
        this.name = name;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.teacher = teacher;
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBeginningDate() {
        return beginningDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeginningDate(String beginningDate) {
        this.beginningDate = beginningDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beginningDate='" + beginningDate + '\'' +
                ", endingDate='" + endingDate + '\'' +
                ", teacher=" + teacher +
                ", students=" + students +
                '}';
    }
}
