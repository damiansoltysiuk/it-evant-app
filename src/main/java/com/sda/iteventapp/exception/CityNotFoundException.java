package com.sda.iteventapp.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(Long id) {
        super("City: " + id + " not found");
    }

    public CityNotFoundException(String name) {
        super("City: " + name + " not found");
    }
}
