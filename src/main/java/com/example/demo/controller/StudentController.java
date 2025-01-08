package com.example.demo.controller;

import com.example.demo.User.Student;
import com.example.demo.repository.Studentrespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
  @Autowired
  Studentrespository repo;
//    get all the students

//    localhost:8080/students
@RequestMapping("/students")
  public List<Student> getAllStudents(){
     List<Student>student= repo.findAll();
    return student;
  }

  @GetMapping("/student/{id}")
 public Student getStudent(@PathVariable int id)
  {
 Student  student=repo.findById(id).get();
 return student;
  }

  @PostMapping("/student/add")
  @ResponseStatus (code= HttpStatus.CREATED)
  public boolean createStudent(@RequestBody Student student){
    repo.save(student);
    return true;
  }
  @PutMapping("/student/update/{id}")
  public Student updateStudents(@PathVariable int id){
   Student student= repo.findById(id).get();
   student.setName("poonam");

//   student.setPercentage(92);
   repo.save(student);
   return student;
  }
  @DeleteMapping("/student/delete/{id}")
    public void removeStudent(@PathVariable int id){
    Student student=repo.findById(id).get();
    repo.delete(student);

  }

}
