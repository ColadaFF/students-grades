package co.com.ias.learning.students.application.services;

import co.com.ias.learning.commons.InputAttributeError;
import co.com.ias.learning.commons.NonEmptyString;
import co.com.ias.learning.students.application.domain.IdentificationNumber;
import co.com.ias.learning.students.application.domain.IdentificationType;
import co.com.ias.learning.students.application.domain.Student;
import co.com.ias.learning.students.application.errors.InputDataError;
import co.com.ias.learning.students.application.errors.StudentAlreadyExistsError;
import co.com.ias.learning.students.application.model.CreateStudentRequest;
import co.com.ias.learning.students.application.model.CreateStudentResponse;
import co.com.ias.learning.students.application.ports.in.CreateStudentUseCase;
import co.com.ias.learning.students.application.ports.out.StudentsRepository;
import io.vavr.control.Validation;

import java.util.List;
import java.util.Optional;

public class CreateStudentService implements CreateStudentUseCase {
    private final StudentsRepository repository;

    public CreateStudentService(StudentsRepository repository) {
        this.repository = repository;
    }


    public CreateStudentResponse execute(CreateStudentRequest request) {
        Validation<InputDataError, Student> validation = Student.parseStudent(
                request.getName(),
                request.getLastName(),
                request.getIdType(),
                request.getIdNumber()
        );

        if(validation.isInvalid()) {
            throw validation.getError();
        }

        final Student student = validation.get();

        IdentificationNumber identificationNumber = student.getIdentificationNumber();
        Optional<Student> studentById = repository.getStudentById(identificationNumber);

        if (studentById.isPresent()) {
            throw new StudentAlreadyExistsError(identificationNumber);
        }

        repository.storeStudent(student);

        return new CreateStudentResponse(student);
    }


}
