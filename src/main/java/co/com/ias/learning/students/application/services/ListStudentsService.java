package co.com.ias.learning.students.application.services;

import co.com.ias.learning.students.application.domain.Student;
import co.com.ias.learning.students.application.model.ListStudentsRequest;
import co.com.ias.learning.students.application.model.ListStudentsResponse;
import co.com.ias.learning.students.application.ports.in.ListStudentsUseCase;
import co.com.ias.learning.students.application.ports.out.StudentsRepository;

import java.util.Collection;

public class ListStudentsService implements ListStudentsUseCase {
    private final StudentsRepository repository;

    public ListStudentsService(StudentsRepository repository) {
        this.repository = repository;
    }

    @Override
    public ListStudentsResponse execute(ListStudentsRequest request) {
        Collection<Student> students = repository.listStudents(
                request.getLimit(),
                request.getSkip()
        );
        Integer total = repository.countStudents();
        return new ListStudentsResponse(students, total);
    }
}
