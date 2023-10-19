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
        Student student2 = new Student("otisse","otisse@gmail.com");
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        // WHEN
        List<Student> listStudent = studentService.getAllStudents();
        // THEN
        //assertThat(listStudent).hasSize(2);
        assertThat(listStudent).contains(student1, student2);
    }

    @Test
    void shouldAddStudentToDatabase() {
        // GIVEN
        Student student = new Student("Said el arris" , "saidElAriss@gmail.com");
        // WHEN
        studentService.addStudent(student);
        // THEN
        // Retrieve the student entity from the database.
        Student savedStudent = studentRepository.findById(student.getId()).get();
        // Verify that the student entity was saved correctly.
        assertEquals("Said el arris", student.getName());
    }

    @Test
    void shouldThrowBadRequestExceptionWhenEmailTaken() {
        // Create a Student object with a duplicate email address.
        Student student1 = new Student("John Doe", "john.doe@example.com");
        Student student2 = new Student("Jane Doe", "john.doe@example.com");

        // Save the first Student object to the database.
        studentRepository.save(student1);

        // Call the addStudent() method with the second Student object.
        assertThrows(BadRequestException.class, () -> studentService.addStudent(student2));
    }

}