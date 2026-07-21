package com.example.demo.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String mesaage){
        super(mesaage);
    }
}
