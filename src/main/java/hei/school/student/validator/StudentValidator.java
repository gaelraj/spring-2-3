package hei.school.student.validator;

import hei.school.student.entity.Student;
import hei.school.student.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentValidator {

    public void validate(List<Student> students) {

        if(students == null || students.isEmpty()) {
            throw new BadRequestException("You must provide at least one student object in the list.");
        }

        for (Student student : students) {
            if (student.getReference() == null || student.getReference().isBlank()) {
                throw new BadRequestException("Student reference cannot be null or empty");
            }
            if (student.getFirstName() == null || student.getFirstName().isBlank()) {
                throw new BadRequestException("Student first name cannot be null or empty");
            }
            if (student.getLastName() == null || student.getLastName().isBlank()) {
                throw new BadRequestException("Student last name cannot be null or empty");
            }
        }
    }
}
