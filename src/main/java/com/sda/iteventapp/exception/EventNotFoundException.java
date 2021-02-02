package com.sda.iteventapp.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(Long eventId) {
        super("Event: " + eventId + " not found");
    }

    public EventNotFoundException(String data) {
        super("Event: " + data + " not found");
    }

    public EventNotFoundException() {
        super("Event not found");
    }
}