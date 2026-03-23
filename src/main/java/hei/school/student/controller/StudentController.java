package hei.school.student.controller;

import hei.school.student.entity.Student;
import hei.school.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name) {
        if (name == null || name.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Name parameter is required");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Welcome " + name);
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudentList(@RequestBody List<Student> newStudentList) {

            try {
                List<Student> result = studentService.addStudents(newStudentList);
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(result);
            } catch (Exception e) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error : " + e.getMessage());
            }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept", required = false) String headerAcceptType) {
         try {
             if (headerAcceptType == null || headerAcceptType.equals("*/*")) {
                 return ResponseEntity
                         .status(HttpStatus.BAD_REQUEST)
                         .body("Accept header is required");
             }

             if (!headerAcceptType.equals("text/plain") && !headerAcceptType.equals("applicaiton/json")) {
                 return ResponseEntity
                         .status(HttpStatus.NOT_IMPLEMENTED)
                         .body("Accept type not supported : " + headerAcceptType);
             }

             if (headerAcceptType.equals("text/plain")) {
                 return ResponseEntity
                         .status(HttpStatus.OK)
                         .body(studentService.getAllStudentsName());
             }

             return ResponseEntity
                     .status(HttpStatus.OK)
                     .body(studentService.getAllStudentsName());

         }catch (Exception e) {
             return ResponseEntity
                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body("Error : " + e.getMessage());
         }
    }

}
