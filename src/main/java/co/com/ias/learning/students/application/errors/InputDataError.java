package co.com.ias.learning.students.application.errors;

public class InputDataError extends RuntimeException {
    private final String message;

    public InputDataError(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
