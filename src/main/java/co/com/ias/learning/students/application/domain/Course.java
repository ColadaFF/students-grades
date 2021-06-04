package co.com.ias.learning.students.application.domain;

import co.com.ias.learning.students.application.commons.NonEmptyString;
import co.com.ias.learning.students.application.commons.Validate;

import java.util.UUID;

public class Course {
    private final UUID id;
    private final NonEmptyString name;

    public Course(UUID id, NonEmptyString name) {
        Validate.notNull(id, "Course id can not be null");
        Validate.notNull(name, "Course name can not be null");
        this.id = id;
        this.name = name;
    }
}
