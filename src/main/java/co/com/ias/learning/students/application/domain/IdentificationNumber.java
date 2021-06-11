package co.com.ias.learning.students.application.domain;

import co.com.ias.learning.commons.InputAttributeError;
import co.com.ias.learning.commons.Validate;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.regex.Pattern;

public class IdentificationNumber {
    private final String value;

    private static final Pattern pattern = Pattern.compile("^\\d{8,20}$");
    private static final String NULL_MESSAGE = "IdentificationNumber can not be null";
    private static final String EMPTY_MESSAGE = "IdentificationNumber can not be empty";
    private static final String INVALID_PATTERN_MESSAGE = "Invalid identification number";

    private IdentificationNumber(String value) {
        Validate.notNull(value, NULL_MESSAGE);
        Validate.isTrue(StringUtils.isNotBlank(value), EMPTY_MESSAGE);
        Validate.isTrue(pattern.matcher(value).matches(), INVALID_PATTERN_MESSAGE);
        this.value = value;
    }

    public static Validation<InputAttributeError, IdentificationNumber> parse(
            String unsafeValue,
            String attributeName
    ) {
        Objects.requireNonNull(attributeName);

        if(unsafeValue == null) {
            return Validation.invalid(new InputAttributeError(attributeName, NULL_MESSAGE));
        }
        if(StringUtils.isBlank(unsafeValue)) {
            return Validation.invalid(new InputAttributeError(attributeName, EMPTY_MESSAGE));
        }
        if(!pattern.matcher(unsafeValue).matches()) {
            return Validation.invalid(new InputAttributeError(attributeName, INVALID_PATTERN_MESSAGE));
        }

        return Validation.valid(new IdentificationNumber(unsafeValue));
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
