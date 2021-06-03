package co.com.ias.learning.students.infrastructure.configuration;

import co.com.ias.learning.students.application.ports.in.CreateStudentUseCase;
import co.com.ias.learning.students.application.ports.in.ListStudentsUseCase;
import co.com.ias.learning.students.application.ports.out.StudentsRepository;
import co.com.ias.learning.students.application.services.CreateStudentService;
import co.com.ias.learning.students.application.services.ListStudentsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentsApplicationConfiguration {

    @Bean
    public CreateStudentUseCase createStudentServiceBean(
            StudentsRepository studentsRepository
    ) {
        return new CreateStudentService(studentsRepository);
    }

    @Bean
    public ListStudentsUseCase listStudentsUseCase(
            @Qualifier("sql") StudentsRepository repository
    ) {
        return new ListStudentsService(repository);
    }
}
