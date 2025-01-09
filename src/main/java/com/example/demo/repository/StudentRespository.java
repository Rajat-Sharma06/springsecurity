package com.example.demo.repository;

import com.example.demo.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRespository extends JpaRepository<Student, Integer> {
}
