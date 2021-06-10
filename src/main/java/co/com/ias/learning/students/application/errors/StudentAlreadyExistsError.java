package co.com.ias.learning.students.application.errors;

import co.com.ias.learning.commons.errors.ApplicationError;
import co.com.ias.learning.commons.errors.HttpStatusCode;
import co.com.ias.learning.students.application.domain.IdentificationNumber;

import java.util.Map;

public class StudentAlreadyExistsError extends ApplicationError {
    private final IdentificationNumber idNumber;

    public StudentAlreadyExistsError(IdentificationNumber idNumber) {
        this.idNumber = idNumber;
    }

    public IdentificationNumber getIdNumber() {
        return idNumber;
    }

    @Override
    public String getMessage() {
        return "The student with id number: " + idNumber.getValue() + " already exists.";
    }

    @Override
    public String errorCode() {
        return "STUDENT_ALREADY_EXISTS_ERROR";
    }

    @Override
    public HttpStatusCode httpStatusCode() {
        return HttpStatusCode.BAD_REQUEST;
    }

    @Override
    public Map<String, Object> metadata() {
        return Map.of(
                "idNumber", idNumber
        );
    }
}
