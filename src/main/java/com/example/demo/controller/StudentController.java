package com.example.demo.controller;

import com.example.demo.User.Student;
import com.example.demo.repository.Studentrespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



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
    public boolean updateStudents(@PathVariable int id) {
    try {

        Optional<Student> optionalStudent = repo.findById(id); // Get the Optional object
        if (optionalStudent.isPresent()) { // Check if the student exists
            Student student = optionalStudent.get(); // Retrieve the student object
            student.setName("poonam");
            // student.setPercentage(92); // Uncomment if needed
            repo.save(student); // Save the updated student back to the database
            return true;
        }
    }
        catch(Exception e){
         e.printStackTrace();
        }
        System.out.println("ERROR: An error occurred");
        return false;
    }



    //  @PutMapping("/student/update/{id}")
//  public boolean updateStudents(@PathVariable int id){
//
//      if(repo.findById(id)!=null){
//          Student student= repo.findById(id).get();
//          student.setName("poonam");
//
////   student.setPercentage(92);
//          repo.save(student);
//          return true;
//
//      }
//
//      return false;
//  }
  @DeleteMapping("/student/delete/{id}")
    public void removeStudent(@PathVariable int id){
    Student student=repo.findById(id).get();
    repo.delete(student);

  }

}
