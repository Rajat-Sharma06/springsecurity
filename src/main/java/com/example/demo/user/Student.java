package com.example.demo.user;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollNo;

    @Column
    private String name;

    @Column
    private Float percentage;

    @Column
    private String branch;

    // Getters and Setters
    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    // toString Method
    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", percentage=" + percentage +
                ", branch='" + branch + '\'' +
                '}';
    }

    // Default Constructor
    public Student() {
    }

    // Parameterized Constructor
    public Student(String branch, Float percentage, String name) {
        this.branch = branch;
        this.percentage = percentage;
        this.name = name;
    }
}
