package hei.school.student.controller;

import hei.school.student.entity.Student;
import hei.school.student.exception.BadRequestException;
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

    @PostMapping("/students")
    public ResponseEntity<?> createStudents(@RequestBody List<Student> newStudentList) {

            try {
                List<Student> allStudents = studentService.addStudents(newStudentList);
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(allStudents);
            } catch (BadRequestException e) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .header("Content-Type", "text/plain")
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

             if (!headerAcceptType.equals("text/plain") && !headerAcceptType.equals("application/json")) {
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
                     .body(studentService.getAllStudents());

         }catch (Exception e) {
             return ResponseEntity
                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body("Error : " + e.getMessage());
         }
    }

}
