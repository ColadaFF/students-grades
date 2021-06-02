package co.com.ias.learning.students.infrastructure.adapters.in;

import co.com.ias.learning.students.application.errors.InputDataError;
import co.com.ias.learning.students.application.errors.StudentAlreadyExistsError;
import co.com.ias.learning.students.application.model.CreateStudentRequest;
import co.com.ias.learning.students.application.model.CreateStudentResponse;
import co.com.ias.learning.students.application.model.ListStudentsRequest;
import co.com.ias.learning.students.application.model.ListStudentsResponse;
import co.com.ias.learning.students.application.ports.in.CreateStudentUseCase;
import co.com.ias.learning.students.application.ports.in.ListStudentsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StudentsController {
    private final CreateStudentUseCase createStudentUseCase;
    private final ListStudentsUseCase listStudentsUseCase;

    @Autowired
    public StudentsController(CreateStudentUseCase createStudentUseCase, ListStudentsUseCase listStudentsUseCase) {
        this.createStudentUseCase = createStudentUseCase;
        this.listStudentsUseCase = listStudentsUseCase;
    }

    @RequestMapping(path = "/students", method = RequestMethod.GET)
    public ResponseEntity listStudentsHandler() {
        try {
            ListStudentsRequest request = new ListStudentsRequest();
            ListStudentsResponse listStudentsResponse = listStudentsUseCase.execute(request);

            return ResponseEntity.ok(listStudentsResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }


    @RequestMapping(path = "/students", method = RequestMethod.POST)
    public ResponseEntity createStudentHandler(
            @RequestBody CreateStudentRequest request
    ) {
        try {
            CreateStudentResponse response = createStudentUseCase.execute(request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        } catch (InputDataError | StudentAlreadyExistsError inputDataError) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            Map.of("message", inputDataError.getMessage())
                    );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}
