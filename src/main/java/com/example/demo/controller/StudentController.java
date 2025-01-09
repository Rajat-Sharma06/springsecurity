package com.example.demo.controller;

import com.example.demo.user.Student;
import com.example.demo.repository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRespository repo;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping("/student/add")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createStudent(@RequestBody Student student) {
        repo.save(student);
        return true;
    }

    @PutMapping("/student/update/{id}")
    public boolean updateStudent(@PathVariable int id) {
        try {
            Optional<Student> optionalStudent = repo.findById(id);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                student.setName("poonam");
                // student.setPercentage(92); // Uncomment if needed
                repo.save(student);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ERROR: An error occurred");
        return false;
    }

    @DeleteMapping("/student/delete/{id}")
    public void removeStudent(@PathVariable int id) {
        repo.findById(id).ifPresent(repo::delete);
    }
}
