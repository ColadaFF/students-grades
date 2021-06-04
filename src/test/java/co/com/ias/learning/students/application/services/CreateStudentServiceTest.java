package co.com.ias.learning.students.application.services;

import co.com.ias.learning.students.application.commons.NonEmptyString;
import co.com.ias.learning.students.application.domain.IdentificationNumber;
import co.com.ias.learning.students.application.domain.IdentificationType;
import co.com.ias.learning.students.application.domain.Student;
import co.com.ias.learning.students.application.errors.StudentAlreadyExistsError;
import co.com.ias.learning.students.application.model.CreateStudentRequest;
import co.com.ias.learning.students.application.model.CreateStudentResponse;
import co.com.ias.learning.students.application.ports.out.StudentsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateStudentServiceTest {

    @Test
    void ifStudentsDoesNotExistsItGetsCreated() {
        // arrange
        StudentsRepository repository = Mockito.mock(StudentsRepository.class);
        Mockito.when(repository.getStudentById(ArgumentMatchers.any(IdentificationNumber.class)))
                .thenReturn(Optional.empty());


        CreateStudentService createStudentService = new CreateStudentService(repository);
        final String idNumber = "12345678";
        CreateStudentRequest request = new CreateStudentRequest(
                "name",
                "lastName",
                idNumber,
                "CC"
        );

        // act
        CreateStudentResponse response = createStudentService.execute(request);

        // assert
        assertAll(
                () -> assertDoesNotThrow(() -> createStudentService.execute(request)),
                () -> assertEquals(
                        response.getStudent().getIdentificationNumber().getValue(),
                        idNumber
                ),
                () -> Mockito.verify(repository, Mockito.times(2))
                        .getStudentById(ArgumentMatchers.any(IdentificationNumber.class))
        );
    }


    @Test
    void ifStudentExistsItThrowsAnException() {
        Student student = new Student(
                new NonEmptyString("name"),
                new NonEmptyString("lastName"),
                IdentificationType.CC,
                new IdentificationNumber("12345678")
        );

        StudentsRepository repository = Mockito.mock(StudentsRepository.class);
        Mockito.when(repository.getStudentById(ArgumentMatchers.any(IdentificationNumber.class)))
                .thenReturn(Optional.of(student));


        CreateStudentService createStudentService = new CreateStudentService(repository);
        CreateStudentRequest request = new CreateStudentRequest(
                student.getName().getValue(),
                student.getLastName().getValue(),
                student.getIdentificationNumber().getValue(),
                student.getIdentificationType().name()
        );

        assertAll(
                () -> assertThrows(StudentAlreadyExistsError.class, () -> createStudentService.execute(request)),
                () -> Mockito.verify(repository, Mockito.times(0))
                        .storeStudent(ArgumentMatchers.any(Student.class))
        );

    }

}