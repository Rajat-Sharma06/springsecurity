package com.example.demo.repository;

import com.example.demo.User.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Studentrespository extends JpaRepository<Student,Integer> {


}
