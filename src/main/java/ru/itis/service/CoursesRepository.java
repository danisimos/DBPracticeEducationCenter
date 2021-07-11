package ru.itis.service;

import ru.itis.models.Course;

import java.util.List;
import java.util.Optional;

public interface CoursesRepository {
    Optional<Course> findById(Integer id);
    List<Course> findByName(String searchName);
    void save(Course course);
    void update(Course course);
}
