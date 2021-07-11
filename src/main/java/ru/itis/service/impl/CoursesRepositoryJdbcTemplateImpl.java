package ru.itis.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.models.Course;
import ru.itis.models.Student;
import ru.itis.models.Teacher;
import ru.itis.service.CoursesRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CoursesRepositoryJdbcTemplateImpl implements CoursesRepository {
    //language=SQL
    private static final String SQL_INSERT = "insert into course (name, beginning_date, ending_date, teacher_id) values (?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update course set name = ?, beginning_date = ?, ending_date = ?, teacher_id = ? where id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_NAME = "select *, k.id as course_id from ((course c left join course_student cs on c.id = cs.course_id) k inner join student s on k.student_id = s.id) where name = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select *, k.id as course_id from ((course c left join course_student cs on c.id = cs.course_id) k inner join student s on k.student_id = s.id) where course_id = ?";

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Course> courseRowMapper = new RowMapper<Course>() {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = null;

            try {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String beginningDate = rs.getString("beginning_date");
                String endingDate = rs.getString("ending_date");
                Teacher teacher = new Teacher(rs.getInt("teacher_id"));

                course = new Course(id, name, beginningDate, endingDate, teacher, new ArrayList<>());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return course;
        }
    };

    private final RowMapper<Student> studentRowMapper = new RowMapper<Student>() {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student(rs.getInt("student_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getInt("study_group"));

            return student;
        }
    };

    private final ResultSetExtractor<List<Course>> courseListResultSetExtractor = new ResultSetExtractor<List<Course>>() {
        @Override
        public List<Course> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<Course> courses = new ArrayList<>();
            Set<Integer> integerSet = new HashSet<>();
            Course currentCourse = null;

            while(rs.next()) {
                if(!integerSet.contains(rs.getInt("course_id"))) {
                    currentCourse = courseRowMapper.mapRow(rs, rs.getRow());
                    courses.add(currentCourse);
                }

                if(rs.getObject("student_id") != null) {
                    Student student = studentRowMapper.mapRow(rs, rs.getRow());
                    currentCourse.getStudents().add(student);
                }

                integerSet.add(currentCourse.getId());
            }

            return courses;
        }
    };

    public CoursesRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return jdbcTemplate.query(SQL_SELECT_BY_ID, courseListResultSetExtractor, id).stream().findAny();
    }

    @Override
    public List<Course> findByName(String searchName) {
        return jdbcTemplate.query(SQL_SELECT_BY_NAME, courseListResultSetExtractor, searchName);
    }

    @Override
    public void save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});

            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getBeginningDate());
            preparedStatement.setString(3, course.getEndingDate());
            preparedStatement.setInt(4, course.getTeacher().getId());

            return preparedStatement;
        }, keyHolder);

        course.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void update(Course course) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID,
                course.getName(),
                course.getBeginningDate(),
                course.getEndingDate(),
                course.getTeacher().getId(),
                course.getId());
    }
}
