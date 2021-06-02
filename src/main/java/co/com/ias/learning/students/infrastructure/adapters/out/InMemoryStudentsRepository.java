package co.com.ias.learning.students.infrastructure.adapters.out;

import co.com.ias.learning.students.application.domain.IdentificationNumber;
import co.com.ias.learning.students.application.domain.Student;
import co.com.ias.learning.students.application.ports.out.StudentsRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryStudentsRepository implements StudentsRepository {
    private final Map<IdentificationNumber, Student> database = new HashMap<>();

    @Override
    public Optional<Student> getStudentById(IdentificationNumber identificationNumber) {
        Student student = database.get(identificationNumber);
        return Optional.ofNullable(student);
    }

    @Override
    public void storeStudent(Student student) {
        database.put(student.getIdentificationNumber(), student);
    }

    @Override
    public Collection<Student> listStudents() {
        return database.values();
    }
}
