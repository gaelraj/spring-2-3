package hei.school.student.service;

import hei.school.student.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();

    public List<Student> addStudents(List<Student> newStudents) {
        for(Student newSTD : newStudents) {
            if(newStudents.isEmpty()) {
                throw new RuntimeException("No student found");
            }

            students.add(newSTD);
        }

        return students;
    }

    public String getAllStudentsName() {

        return students.stream()
                .map(e -> e.getFirstName() + " " + e.getLastName())
                .collect(Collectors.joining(", "));
    }
}
