package co.com.ias.learning.students.controllers;

import co.com.ias.learning.students.errors.InputDataError;
import co.com.ias.learning.students.model.CreateStudentRequest;
import co.com.ias.learning.students.model.CreateStudentResponse;
import co.com.ias.learning.students.services.CreateStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentsController {
    private final CreateStudentService createStudentService;

    @Autowired
    public StudentsController(CreateStudentService createStudentService) {
        this.createStudentService = createStudentService;
    }


    @RequestMapping(path = "/students", method = RequestMethod.POST)
    public ResponseEntity<CreateStudentResponse> createStudentHandler(
            @RequestBody CreateStudentRequest request
    ) {
        try {
            CreateStudentResponse response =  createStudentService.execute(request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        } catch (InputDataError inputDataError) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}
