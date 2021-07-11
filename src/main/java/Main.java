import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.models.Course;
import ru.itis.models.Teacher;
import ru.itis.service.CoursesRepository;
import ru.itis.service.LessonsRepository;
import ru.itis.service.impl.CoursesRepositoryJdbcTemplateImpl;
import ru.itis.service.impl.LessonsRepositoryJdbcTemplateImpl;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.pool-size")));

        DataSource dataSource = new HikariDataSource(config);
        CoursesRepository coursesRepository = new CoursesRepositoryJdbcTemplateImpl(dataSource);
        LessonsRepository lessonsRepository = new LessonsRepositoryJdbcTemplateImpl(dataSource);

        Course course = new Course(7, "История России", "19.11.2020", "04.01.2021", new Teacher(5, "Даниил", "Коротаев", 1, null), null);

        System.out.println(lessonsRepository.findById(2));

    }
}
