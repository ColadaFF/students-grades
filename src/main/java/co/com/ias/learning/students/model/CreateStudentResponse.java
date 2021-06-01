package co.com.ias.learning.students.model;

import co.com.ias.learning.students.domain.Student;

public class CreateStudentResponse {
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
