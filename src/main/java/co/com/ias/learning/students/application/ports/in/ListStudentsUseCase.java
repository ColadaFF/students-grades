package co.com.ias.learning.students.application.ports.in;

import co.com.ias.learning.students.application.commons.operation.ApplicationUseCase;
import co.com.ias.learning.students.application.model.ListStudentsRequest;
import co.com.ias.learning.students.application.model.ListStudentsResponse;

public interface ListStudentsUseCase extends ApplicationUseCase<ListStudentsRequest, ListStudentsResponse> {
}
