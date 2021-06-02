package co.com.ias.learning.students.application.model;

import co.com.ias.learning.students.application.commons.operation.ApplicationResponse;
import co.com.ias.learning.students.application.domain.Student;

public class CreateStudentResponse implements ApplicationResponse {
    private final Student student;

    public CreateStudentResponse(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "CreateStudentResponse{" +
                "student=" + student +
                '}';
    }
}
