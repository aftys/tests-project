package gi.ehtp.tp_test.controllers;

import gi.ehtp.tp_test.entities.Student;
import gi.ehtp.tp_test.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<Long>  deleteStudent(
            @PathVariable("studentId") Long studentId) {
           studentService.deleteStudent(studentId);
        return new ResponseEntity<>(studentId, HttpStatus.NO_CONTENT);
    }
}