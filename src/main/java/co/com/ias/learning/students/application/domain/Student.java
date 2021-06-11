package co.com.ias.learning.students.application.domain;

import co.com.ias.learning.commons.InputAttributeError;
import co.com.ias.learning.commons.NonEmptyString;
import co.com.ias.learning.commons.Validate;
import co.com.ias.learning.students.application.errors.InputDataError;
import io.vavr.control.Validation;

import java.util.List;

public class Student {
    private final NonEmptyString name;
    private final NonEmptyString lastName;
    private final IdentificationType identificationType;
    private final IdentificationNumber identificationNumber;

    public Student(NonEmptyString name, NonEmptyString lastName, IdentificationType identificationType, IdentificationNumber identificationNumber) {
        Validate.notNull(name, "Student name can not be null");
        Validate.notNull(lastName, "Student lastName can not be null");
        Validate.notNull(identificationType, "Student identificationType can not be null");
        Validate.notNull(identificationNumber, "Student identificationNumber can not be null");

        this.name = name;
        this.lastName = lastName;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
    }

    public static Validation<InputDataError, Student> parseStudent(
            String name,
            String lastName,
            String idType,
            String idNumber
    ) {
        var nameValidation = NonEmptyString.parse(
                name,
                "name"
        );

        var lastNameValidation = NonEmptyString.parse(
                lastName,
                "lastName"
        );

        var idTypeValidation = IdentificationType.parse(
                idType,
                "idType"
        );

        var idNumberValidation = IdentificationNumber.parse(
                idNumber,
                "idNumber"
        );
        return Validation.combine(
                nameValidation,
                lastNameValidation,
                idTypeValidation,
                idNumberValidation
        )
                .ap(Student::new)
                .mapError(inputAttributeErrors -> {
                    String message = "There was an error with the input student data.";
                    final List<InputAttributeError> errors = inputAttributeErrors.asJava();
                    return new InputDataError(message, errors);
                });
    }

    public NonEmptyString getName() {
        return name;
    }

    public NonEmptyString getLastName() {
        return lastName;
    }

    public IdentificationType getIdentificationType() {
        return identificationType;
    }

    public IdentificationNumber getIdentificationNumber() {
        return identificationNumber;
    }
}
