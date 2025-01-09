package com.example.demo.contoller;

import com.example.demo.controller.StudentController;
import com.example.demo.repository.StudentRespository;
import com.example.demo.user.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentControllerTests {

    @Autowired
    private StudentController student;

    @Test
    public void testadd(){
        assertEquals(4,2+2);
    }
    @Test
    public void findByStudentRoll(){
         assertNotNull(student.getAllStudents());
//         assertTrue(student.createStudent(new Student()));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "3,2,4"
    })
    public void test(int a,int b,int expected){
      assertEquals(expected,a+b);
    }


}
