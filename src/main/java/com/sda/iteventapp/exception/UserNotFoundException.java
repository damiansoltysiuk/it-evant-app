package com.sda.iteventapp.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Comment: " + id + " not found");
    }
}