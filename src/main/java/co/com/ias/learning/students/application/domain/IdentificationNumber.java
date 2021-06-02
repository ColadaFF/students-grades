package co.com.ias.learning.students.application.domain;

import co.com.ias.learning.students.application.commons.StringUtils;
import co.com.ias.learning.students.application.commons.Validate;

import java.util.Objects;
import java.util.regex.Pattern;

public class IdentificationNumber {
    private final String value;

    private final Pattern pattern = Pattern.compile("^\\d{8,20}$");

    public IdentificationNumber(String value) {
        Validate.notNull(value, "IdentificationNumber can not be null");
        Validate.isTrue(StringUtils.nonBlank(value), "IdentificationNumber can not be empty");
        Validate.isTrue(pattern.matcher(value).matches(), "Invalid identification number: " + value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentificationNumber that = (IdentificationNumber) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "IdentificationNumber{" +
                "value='" + value + '\'' +
                '}';
    }
}
