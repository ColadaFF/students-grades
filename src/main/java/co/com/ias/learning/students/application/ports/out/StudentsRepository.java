package co.com.ias.learning.students.application.ports.out;

import co.com.ias.learning.students.application.domain.IdentificationNumber;
import co.com.ias.learning.students.application.domain.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StudentsRepository {
    Optional<Student> getStudentById(IdentificationNumber identificationNumber);

    void storeStudent(Student student);

    Collection<Student> listStudents(int limit, int skip);
}
