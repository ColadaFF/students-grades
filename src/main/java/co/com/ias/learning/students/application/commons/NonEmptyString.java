package co.com.ias.learning.students.application.commons;

import static co.com.ias.learning.students.application.commons.StringUtils.nonBlank;

public class NonEmptyString {
    private final String value;

    public NonEmptyString(String value) {
        // validation logic
        Validate.notNull(value, "NonEmptyString can not be null");
        Validate.isTrue(nonBlank(value), "NonEmptyString can not be empty");
        this.value = value;
    }




    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "NonEmptyString{" +
                "value='" + value + '\'' +
                '}';
    }
}
