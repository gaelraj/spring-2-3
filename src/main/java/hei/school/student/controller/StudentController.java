package hei.school.student.controller;

import hei.school.student.entity.Student;
import hei.school.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello world";
    }

    @GetMapping("/welcome")
    public String welcome(@RequestParam(required = false) String name) {
        return "Welcome " + name;
    }

    @PostMapping("/students")
    public String addStudentList(@RequestBody List<Student> newStudentList) {

            List<Student> result = studentService.addStudents(newStudentList);
            return studentService.getAllStudentsName();
    }


}
