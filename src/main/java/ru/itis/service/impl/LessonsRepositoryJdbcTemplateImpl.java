package ru.itis.service.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.models.Course;
import ru.itis.models.Lesson;
import ru.itis.service.LessonsRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LessonsRepositoryJdbcTemplateImpl implements LessonsRepository {
    //language=SQL
    private static final String SQL_INSERT = "insert into lesson (name, date, course_id) values (?, ?, ?)";

    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update lesson set name = ?, date = ?, course_id = ? where id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_NAME = "select *, l.name as lesson_name, l.id as lesson_id, c.name as course_name from (lesson l left join course c on l.course_id = c.id) where l.name = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select *, l.name as lesson_name, l.id as lesson_id, c.name as course_name from (lesson l left join course c on l.course_id = c.id) where l.id = ?";

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Lesson> lessonRowMapper = new RowMapper<Lesson>() {
        @Override
        public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
            Integer lessonId = rs.getInt("lesson_id");
            String lessonName = rs.getString("lesson_name");
            String lessonDate = rs.getString("date");
            Integer courseId = rs.getInt("course_id");
            String courseName = rs.getString("course_name");
            String beginningDate = rs.getString("beginning_date");
            String endingDate = rs.getString("ending_date");
            Course course = new Course(courseId, courseName, beginningDate, endingDate);

            return new Lesson(lessonId, lessonName, lessonDate, course);
        }
    };

    public LessonsRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Lesson> findById(Integer id) {
        return jdbcTemplate.query(SQL_SELECT_BY_ID, lessonRowMapper, id).stream().findAny();
    }

    @Override
    public List<Lesson> findByName(String searchName) {
        return jdbcTemplate.query(SQL_SELECT_BY_NAME, lessonRowMapper, searchName);
    }

    @Override
    public void save(Lesson lesson) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});

            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, lesson.getDate());
            preparedStatement.setInt(3, lesson.getCourse().getId());

            return preparedStatement;
        }, keyHolder);

        lesson.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void update(Lesson lesson) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID,
                lesson.getName(),
                lesson.getDate(),
                lesson.getCourse().getId(),
                lesson.getId());
    }
}
