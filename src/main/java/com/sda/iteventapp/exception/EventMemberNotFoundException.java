package com.sda.iteventapp.exception;

public class EventMemberNotFoundException extends RuntimeException {
    public EventMemberNotFoundException(Long id) {
        super("Event member: " + id + " not found");
    }

    public EventMemberNotFoundException() {
        super("Event member not found");
    }
}