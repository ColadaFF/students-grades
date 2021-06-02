package co.com.ias.learning.students.application.ports.in;

import co.com.ias.learning.students.application.commons.operation.ApplicationUseCase;
import co.com.ias.learning.students.application.model.CreateStudentRequest;
import co.com.ias.learning.students.application.model.CreateStudentResponse;

public interface CreateStudentUseCase extends ApplicationUseCase<CreateStudentRequest, CreateStudentResponse>  {
}
