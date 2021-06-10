package co.com.ias.learning.students.application.model;

import co.com.ias.learning.commons.operation.ApplicationResponse;
import co.com.ias.learning.students.application.domain.Student;

import java.util.Collection;

public class ListStudentsResponse implements ApplicationResponse {
    private final Collection<Student> items;
    private final Integer total;

    public ListStudentsResponse(Collection<Student> items, Integer total) {
        this.items = items;
        this.total = total;
    }

    public Collection<Student> getItems() {
        return items;
    }

    public Integer getTotal() {
        return total;
    }
}
