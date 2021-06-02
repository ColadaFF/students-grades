package co.com.ias.learning.students.application.model;

import co.com.ias.learning.students.application.commons.operation.ApplicationResponse;
import co.com.ias.learning.students.application.domain.Student;

import java.util.Collection;

public class ListStudentsResponse implements ApplicationResponse {
    private final Collection<Student> items;

    public ListStudentsResponse(Collection<Student> items) {
        this.items = items;
    }

    public Collection<Student> getItems() {
        return items;
    }
}
