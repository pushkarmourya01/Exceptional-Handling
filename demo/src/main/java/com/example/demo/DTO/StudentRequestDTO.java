package com.example.demo.DTO;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class StudentRequestDTO {

    @NotBlank(message = "Hey! Bhai bina name ke aage kaise ja rhe ho yeh bharo pahle")//blank -> value hai, par sirf spaces
   @Size(min=2,max=50, message = "MUST BE WITHIN 50 CHARACTER ")
    private String name;

   @NotNull(message = "Bhai Roll toh bharna hi hoga hehe!")//value hi nahi null means to protect
   @NotEmpty(message = "Areh Bhai Roll Number Bhar")
    private Integer roll;

   // protect from value hai, par khaali
   @Min(value = 18)
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }
}




