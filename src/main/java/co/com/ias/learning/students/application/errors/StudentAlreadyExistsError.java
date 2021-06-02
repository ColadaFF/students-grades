package co.com.ias.learning.students.application.errors;

import co.com.ias.learning.students.application.domain.IdentificationNumber;

public class StudentAlreadyExistsError extends RuntimeException {
    private final IdentificationNumber idNumber;

    public StudentAlreadyExistsError(IdentificationNumber idNumber) {
        super("The student with id number : " + idNumber.getValue() + " already exists.");
        this.idNumber = idNumber;
    }

    public IdentificationNumber getIdNumber() {
        return idNumber;
    }
}
