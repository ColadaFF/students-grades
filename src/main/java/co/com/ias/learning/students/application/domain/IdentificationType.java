package co.com.ias.learning.students.application.domain;

import co.com.ias.learning.commons.InputAttributeError;
import io.vavr.control.Validation;

import java.util.Objects;

public enum IdentificationType {
    CC,
    CE,
    RC,
    TI;

    public static Validation<InputAttributeError, IdentificationType> parse(
            String value,
            String attributeName
    ) {
        Objects.requireNonNull(attributeName);

        try {
            final IdentificationType identificationType = IdentificationType.valueOf(value);
            return Validation.valid(identificationType);
        } catch (RuntimeException e) {
            return Validation.invalid(new InputAttributeError(attributeName, e.getMessage()));
        }
    }
}
