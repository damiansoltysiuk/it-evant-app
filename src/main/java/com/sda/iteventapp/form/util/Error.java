package com.sda.iteventapp.form.util;

public class Error {
    private String header;
    private String description;

    public Error(String header, String description) {
        this.header = header;
        this.description = description;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }
}
