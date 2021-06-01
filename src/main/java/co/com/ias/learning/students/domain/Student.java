package co.com.ias.learning.students.domain;

import co.com.ias.learning.students.commons.NonEmptyString;
import co.com.ias.learning.students.commons.Validate;

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
