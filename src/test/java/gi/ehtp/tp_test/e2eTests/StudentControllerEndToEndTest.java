package gi.ehtp.tp_test.e2eTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import gi.ehtp.tp_test.entities.Student;
import gi.ehtp.tp_test.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    StudentRepository repo;

    @Test
    void getAllStudents() throws Exception {
        mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void addStudent() throws Exception {
        Student student = new Student("John Doe" , "john@gmail.com");
        mockMvc.perform(post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(student)))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteStudent() throws Exception {
        Student student=new Student( Long.valueOf(1),"oussama","test@gmail");
        repo.save(student);
        mockMvc.perform(delete("/api/v1/students/1"))
                .andExpect(status().isNoContent());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
