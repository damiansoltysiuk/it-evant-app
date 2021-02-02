package com.sda.iteventapp.exception;

public class EventSubjectNotFoundException extends RuntimeException {
    public EventSubjectNotFoundException(Long id) {
        super("Event subject: " + id + " not found");
    }

    public EventSubjectNotFoundException(String name) {
        super("Event subject: " + name + " not found");
    }
}
