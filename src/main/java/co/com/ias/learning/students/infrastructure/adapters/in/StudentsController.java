package co.com.ias.learning.students.infrastructure.adapters.in;

import co.com.ias.learning.students.application.model.CreateStudentRequest;
import co.com.ias.learning.students.application.model.ListStudentsRequest;
import co.com.ias.learning.students.application.ports.in.CreateStudentUseCase;
import co.com.ias.learning.students.application.ports.in.ListStudentsUseCase;
import co.com.ias.learning.students.infrastructure.commons.UseCaseHttpExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/students")
public class StudentsController {
    private final UseCaseHttpExecutor useCaseHttpExecutor;
    private final CreateStudentUseCase createStudentUseCase;
    private final ListStudentsUseCase listStudentsUseCase;

    @Autowired
    public StudentsController(UseCaseHttpExecutor useCaseHttpExecutor, CreateStudentUseCase createStudentUseCase, ListStudentsUseCase listStudentsUseCase) {
        this.useCaseHttpExecutor = useCaseHttpExecutor;
        this.createStudentUseCase = createStudentUseCase;
        this.listStudentsUseCase = listStudentsUseCase;
    }

    @GetMapping
    public ResponseEntity listStudentsHandler(
            @RequestParam(name = "limit", defaultValue = "10") String limit,
            @RequestParam(name = "skip", defaultValue = "0") String skip
    ) {
        Integer limitInt = Integer.parseInt(limit, 10);
        Integer skipInt = Integer.parseInt(skip, 10);
        return useCaseHttpExecutor.executeUseCase(
                listStudentsUseCase,
                new ListStudentsRequest(limitInt, skipInt)
        );
    }


    @PostMapping
    public ResponseEntity createStudentHandler(
            @RequestBody CreateStudentRequest request
    ) {
        return useCaseHttpExecutor.executeUseCase(
                createStudentUseCase,
                request
        );
    }

}
