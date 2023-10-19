package gi.ehtp.tp_test.services;

import gi.ehtp.tp_test.entities.Student;
import gi.ehtp.tp_test.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

public class StudentServiceUnitTest {
    @Mock private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void canAddStudent(){
        // GIVEN
        Student student = new Student("Mostafa" , "mostafasissi@gmail.com");
        // WHEN
        underTest.addStudent(student);
        // THEN
        verify(studentRepository).save(student);

    }
}
