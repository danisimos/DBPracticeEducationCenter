package ru.itis.models;

import java.util.List;

public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer group;

    private List<Course> courses;

    public Student(Integer id, String firstName, String lastName, Integer group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
    }

    public Student(Integer id, String firstName, String lastName, Integer group, List<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.courses = courses;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getGroup() {
        return group;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", group=" + group +
                ", courses=" + courses +
                '}';
    }
}
