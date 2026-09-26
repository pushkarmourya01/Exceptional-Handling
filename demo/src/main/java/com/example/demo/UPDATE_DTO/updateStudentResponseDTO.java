package com.example.demo.UPDATE_DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class updateStudentResponseDTO {
    private String name;
 private Long id;


 public Long getId(){
     return id;
 }

 public void setId(Long id){
     this.id=id;
 }

    private int roll;


    private int age;

    private LocalDate createdAt;
    private LocalTime updatedAt;

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }
}