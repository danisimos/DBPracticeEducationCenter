package ru.itis.models;

public class Lesson {
    private Integer id;
    private String name;
    private String date;
    private Course course;

    public Lesson(Integer id, String name, String date, Course course) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Course getCourse() {
        return course;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", course=" + course +
                '}';
    }
}
