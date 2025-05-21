package org.example.exceptions;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String value, String type, String reason) {
        super(String.format("%s is not valid for %s. Reason: %s",value, type, reason ));
    }
}
