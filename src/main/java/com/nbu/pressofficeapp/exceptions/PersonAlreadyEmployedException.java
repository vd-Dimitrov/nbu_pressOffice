package com.nbu.pressofficeapp.exceptions;

public class PersonAlreadyEmployedException extends RuntimeException {
    public PersonAlreadyEmployedException(String message) {
        super(message);
    }
}
