package com.gjdev.healthcare.exception;

public class InvalidDoctorStatusException extends RuntimeException {
    public InvalidDoctorStatusException(String message) {
        super(message);
    }
}
