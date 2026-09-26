package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
 public ResourceNotFoundException(String message){
     super(message); //eske constructor mein jo message ko super parents mein transfer kr dengey bss
 }

}
