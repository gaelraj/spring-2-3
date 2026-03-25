package hei.school.student.service;

import hei.school.student.entity.Student;
import hei.school.student.validator.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private List<Student> studentsInMemory = new ArrayList<>();

    @Autowired
    private StudentValidator validator;

    public List<Student> addStudents(List<Student> newStudents) {

        validator.validate(newStudents);

        studentsInMemory.addAll(newStudents);

        return studentsInMemory;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentsInMemory);
    }

    public String getAllStudentsName() {

        return studentsInMemory.stream()
                .map(e -> e.getFirstName() + " " + e.getLastName())
                .collect(Collectors.joining(", "));
    }
}
