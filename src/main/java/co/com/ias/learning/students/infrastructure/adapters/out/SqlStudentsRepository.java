package co.com.ias.learning.students.infrastructure.adapters.out;

import co.com.ias.learning.students.application.commons.NonEmptyString;
import co.com.ias.learning.students.application.domain.IdentificationNumber;
import co.com.ias.learning.students.application.domain.IdentificationType;
import co.com.ias.learning.students.application.domain.Student;
import co.com.ias.learning.students.application.ports.out.StudentsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Repository("sql")
public class SqlStudentsRepository implements StudentsRepository {
    private final JdbcTemplate jdbcTemplate;

    public SqlStudentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Student> studentRowMapper = (rs, rowNum) -> fromResultSet(rs);

    @Override
    public Optional<Student> getStudentById(IdentificationNumber identificationNumber) {
        final String sql = "SELECT * FROM STUDENTS WHERE ID_NUMBER = ?";
        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, identificationNumber.getValue());
        };
        final ResultSetExtractor<Optional<Student>> resultSetExtractor = rs -> {
            if (rs.next()) {
                final Student student = fromResultSet(rs);
                return Optional.of(student);
            } else {
                return Optional.empty();
            }
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, resultSetExtractor);
    }

    @Override
    public void storeStudent(Student student) {
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO STUDENTS (ID_NUMBER, ID_TYPE, NAME, LAST_NAME) VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, student.getIdentificationNumber().getValue());
            preparedStatement.setString(2, student.getIdentificationType().name());
            preparedStatement.setString(3, student.getName().getValue());
            preparedStatement.setString(4, student.getLastName().getValue());

            return preparedStatement;
        });
    }

    @Override
    public Collection<Student> listStudents(int limit, int skip) {
        final String sql = "SELECT * FROM STUDENTS LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, studentRowMapper, limit, skip);
    }


    private static Student fromResultSet(ResultSet rs) throws SQLException {
        return new Student(
                new NonEmptyString(rs.getString("NAME")),
                new NonEmptyString(rs.getString("LAST_NAME")),
                IdentificationType.valueOf(rs.getString("ID_TYPE")),
                new IdentificationNumber(rs.getString("ID_NUMBER"))

        );
    }

}
