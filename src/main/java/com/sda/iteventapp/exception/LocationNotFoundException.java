package com.sda.iteventapp.exception;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(Long id) {
        super("Location: " + id + " not found");
    }

    public LocationNotFoundException(String city) {
        super("Location in " + city + " not found");
    }

    public LocationNotFoundException() {
        super("Location not found");
    }
}
