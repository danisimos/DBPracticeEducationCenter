package ru.itis.models;

import java.util.List;

public class Teacher {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer experience;

    private List<Course> courses;

    public Teacher(Integer id) {
        this.id = id;
    }

    public Teacher(Integer id, String firstName, String lastName, Integer experience, List<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
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

    public Integer getExperience() {
        return experience;
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

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", experience=" + experience +
                ", courses=" + courses +
                '}';
    }
}
