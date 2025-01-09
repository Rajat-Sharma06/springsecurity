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

    @GetMapping
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createStudent(@RequestBody Student student) {
        repo.save(student);
        return true;
    }


    @PutMapping("/{id}")
    public boolean updateStudent(@RequestBody Student student,@PathVariable int id) {
        try {
            Optional<Student> optionalStudent = repo.findById(id);
            if (optionalStudent.isPresent()) {
                String studentName= student.getName();
                student = optionalStudent.get();
                if(studentName!=" ")
                   student.setName(studentName);
                else
                    student.setName(student.getName());
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

    @PatchMapping("/{id}")
    public Student updateStudentPatch(@PathVariable int id,@RequestBody Student students ) {
        String studentName=students.getName();
        Student student=repo.findById(id).get();
        student.setName(studentName);
        repo.save(student);
        return student;
    }

    @DeleteMapping("/{id}")
    public void removeStudent(@PathVariable int id) {
        repo.findById(id).ifPresent(repo::delete);
    }
}
