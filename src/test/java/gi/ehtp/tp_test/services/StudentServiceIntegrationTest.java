package gi.ehtp.tp_test.services;

import gi.ehtp.tp_test.Exception.BadRequestException;
import gi.ehtp.tp_test.entities.Student;
import gi.ehtp.tp_test.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class StudentServiceIntegrationTest {

    @Autowired
    StudentService studentService ;
    @Autowired
    StudentRepository studentRepository;
    @Test
    void shouldReturnAllStudents() {
        // GIVEN
        Student student1 = new Student("Anouar","anouar@gmail.com");
        Student student2 = new Student("mostafa","mostafa@gmail.com");
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        // WHEN
        List<Student> listStudent = studentService.getAllStudents();
        // THEN

        assertThat(listStudent).contains(student1, student2);
    }

    @Test
    void shouldAddStudentToDatabase() {
        // GIVEN
        Student student = new Student("said" , "said@gmail.com");
        // WHEN
        studentService.addStudent(student);
        // THEN
        Student savedStudent = studentRepository.findById(student.getId()).get();
        assertEquals("said", student.getName());
    }

    @Test
    void shouldThrowBadRequestExceptionWhenEmailTaken() {
        Student student1 = new Student("mostafa", "mostafa.doe@example.com");
        Student student2 = new Student("mostapha", "mostafa.doe@example.com");

        studentRepository.save(student1);

        assertThrows(BadRequestException.class, () -> studentService.addStudent(student2));
    }

    @Test
    void deleteStudent() {
        Student student = new Student("said", "said@gmail.com");
        studentRepository.save(student);
        Long studentId = student.getId();
        studentService.deleteStudent(studentId);
        assertFalse(studentRepository.existsById(studentId));
    }

}