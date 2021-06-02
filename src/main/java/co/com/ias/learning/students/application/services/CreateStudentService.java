package co.com.ias.learning.students.application.services;

import co.com.ias.learning.students.application.commons.NonEmptyString;
import co.com.ias.learning.students.application.domain.IdentificationNumber;
import co.com.ias.learning.students.application.domain.IdentificationType;
import co.com.ias.learning.students.application.domain.Student;
import co.com.ias.learning.students.application.errors.InputDataError;
import co.com.ias.learning.students.application.errors.StudentAlreadyExistsError;
import co.com.ias.learning.students.application.model.CreateStudentRequest;
import co.com.ias.learning.students.application.model.CreateStudentResponse;
import co.com.ias.learning.students.application.ports.in.CreateStudentUseCase;
import co.com.ias.learning.students.application.ports.out.StudentsRepository;

import java.util.Optional;

public class CreateStudentService implements CreateStudentUseCase {
    private final StudentsRepository repository;

    public CreateStudentService(StudentsRepository repository) {
        this.repository = repository;
    }


    public CreateStudentResponse execute(CreateStudentRequest request) {
        Student student = validateInput(request);

        IdentificationNumber identificationNumber = student.getIdentificationNumber();
        Optional<Student> studentById = repository.getStudentById(identificationNumber);

        if (studentById.isPresent()) {
            throw new StudentAlreadyExistsError(identificationNumber);
        }

        repository.storeStudent(student);

        return new CreateStudentResponse(student);
    }


    private Student validateInput(CreateStudentRequest request) {
        try {
            NonEmptyString name = new NonEmptyString(request.getName());
            NonEmptyString lastName = new NonEmptyString(request.getLastName());
            IdentificationType identificationType = IdentificationType.valueOf(request.getIdType());
            IdentificationNumber identificationNumber = new IdentificationNumber(request.getIdNumber());

            return new Student(
                    name,
                    lastName,
                    identificationType,
                    identificationNumber
            );
        } catch (RuntimeException e) {
            throw new InputDataError(e.getMessage());
        }
    }
}
