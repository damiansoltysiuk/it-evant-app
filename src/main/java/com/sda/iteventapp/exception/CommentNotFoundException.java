package com.sda.iteventapp.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException() {
        super("Not found comments");
    }

    public CommentNotFoundException(Long id) {
        super("Comment: " + id + " not found");
    }
}