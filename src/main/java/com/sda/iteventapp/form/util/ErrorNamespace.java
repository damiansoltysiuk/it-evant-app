package com.sda.iteventapp.form.util;

public class ErrorNamespace {
    public static final Error EMAIL_INVALID = new Error("Email error", "The email is not correct.");
    public static final Error EMAIL_USED = new Error("Email error", "This email is being used.");

    public static final Error PASSWORD_LENGTH = new Error("Password error", "The length password must be greatest than 8 and less to 30 chars");
    public static final Error PASSWORD_IDENTITY = new Error("Password error", "The passwords must be identity.");

    public static final Error USERNAME_VALIDATE = new Error("Username error", "Field username can't be empty and max length is 50 chars.");
    public static final Error USERNAME_USED = new Error("Username error", "This username is being used");

    public static final Error LOGIN_VALIDATE = new Error("Logged error", "Invalid email or password.");
}