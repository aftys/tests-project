package gi.ehtp.tp_test.services;

import gi.ehtp.tp_test.Exception.BadRequestException;
import gi.ehtp.tp_test.Exception.StudentNotFoundException;
import gi.ehtp.tp_test.entities.Student;
import gi.ehtp.tp_test.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class StudentServiceUnitTest {
    @Mock
    private StudentRepository studentRepository;

    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void getAllStudents() {
        // GIVEN
        List<Student> students = new ArrayList<>();
        students.add(new Student("Mostafa", "mostafasissi@gmail.com"));
        students.add(new Student("John", "john@example.com"));
        when(studentRepository.findAll()).thenReturn(students);

        // WHEN
        List<Student> result = underTest.getAllStudents();

        // THEN
        assertEquals(students, result);
        verify(studentRepository).findAll();
    }

    @Test
    void addStudent() {
        // GIVEN
        Student student = new Student("Mostafa", "mostafasissi@gmail.com");
        when(studentRepository.selectExistsEmail(student.getEmail())).thenReturn(false);

        // WHEN
        underTest.addStudent(student);


        // THEN
        verify(studentRepository).save(student);
    }

    @Test
    void addStudentThrowsBadRequestException() {
        // GIVEN
        Student student = new Student("Mostafa", "mostafasissi@gmail.com");
        when(studentRepository.selectExistsEmail(student.getEmail())).thenReturn(true);

        // WHEN
        BadRequestException exception = assertThrows(BadRequestException.class, () -> underTest.addStudent(student));

        // THEN
        assertEquals("Email mostafasissi@gmail.com taken", exception.getMessage());
        verify(studentRepository, never()).save(student);
    }

    @Test
    void deleteStudent() {
        // GIVEN
        Long studentId = 1L;
        when(studentRepository.existsById(studentId)).thenReturn(true);

        // WHEN
        underTest.deleteStudent(studentId);

        // THEN
        verify(studentRepository).deleteById(studentId);
    }

    @Test
    void deleteStudentThrowsStudentNotFoundException() {
        // GIVEN
        Long studentId = 1L;
        when(studentRepository.existsById(studentId)).thenReturn(false);

        // WHEN
        StudentNotFoundException exception = assertThrows(StudentNotFoundException.class, () -> underTest.deleteStudent(studentId));

        // THEN
        assertEquals("Student with id 1 does not exists", exception.getMessage());
        verify(studentRepository, never()).deleteById(studentId);
    }
}