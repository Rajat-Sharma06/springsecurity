package com.example.demo.controller;

import com.example.demo.user.Student;
import com.example.demo.repository.StudentRespository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRespository repo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllStudents() throws Exception {
        Student student1 = new Student("CSE", 85.5f, "John Doe");
        Student student2 = new Student("ECE", 92.3f, "Jane Doe");

        when(repo.findAll()).thenReturn(Arrays.asList(student1, student2));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].branch").value("ECE"));
    }

    @Test
    void testGetStudentById() throws Exception {
        Student student = new Student("CSE", 85.5f, "John Doe");
        student.setRollNo(1);

        when(repo.findById(1)).thenReturn(Optional.of(student));

        mockMvc.perform(get("/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.branch").value("CSE"));
    }

    @Test
    void testCreateStudent() throws Exception {
        Student student = new Student("CSE", 85.5f, "John Doe");

        when(repo.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateStudent() throws Exception {
        Student existingStudent = new Student("ECE", 78.0f, "Jane Doe");
        existingStudent.setRollNo(1);

        Student updatedStudent = new Student("CSE", 90.0f, "John Doe");
        updatedStudent.setRollNo(1);

        when(repo.findById(1)).thenReturn(Optional.of(existingStudent));
        when(repo.save(any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(put("/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedStudent)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testUpdateStudentPatch() throws Exception {
        Student existingStudent = new Student("ECE", 78.0f, "Wingston Churchil");
        existingStudent.setRollNo(1);

        // Arrange
        Student updatedStudent = new Student("Electrical", 90.0f, "Wingston Churchil");
        updatedStudent.setRollNo(1);

        String studentJson = "{\"name\":\"Wingston Churchil\"}";

        when(repo.findById(1)).thenReturn(Optional.of(existingStudent));
        when(repo.save(any(Student.class))).thenReturn(updatedStudent);

        // Act & Assert
        mockMvc.perform(patch("/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Wingston Churchil"));
        verify(repo, times(1)).save(any(Student.class));
    }

    @Test
    void testRemoveStudent() throws Exception {
        Student student = new Student("CSE", 85.5f, "John Doe");
        student.setRollNo(1);

        when(repo.findById(1)).thenReturn(Optional.of(student));
        Mockito.doNothing().when(repo).delete(student);

        mockMvc.perform(delete("/{id}", 1))
                .andExpect(status().isOk());
    }
}
