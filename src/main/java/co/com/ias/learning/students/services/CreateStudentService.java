package co.com.ias.learning.students.services;

import co.com.ias.learning.students.commons.NonEmptyString;
import co.com.ias.learning.students.domain.IdentificationNumber;
import co.com.ias.learning.students.domain.IdentificationType;
import co.com.ias.learning.students.domain.Student;
import co.com.ias.learning.students.errors.InputDataError;
import co.com.ias.learning.students.model.CreateStudentRequest;
import co.com.ias.learning.students.model.CreateStudentResponse;
import org.springframework.stereotype.Service;

@Service
public class CreateStudentService {


    public CreateStudentResponse execute(CreateStudentRequest request) {
        Student student = validateInput(request);

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
