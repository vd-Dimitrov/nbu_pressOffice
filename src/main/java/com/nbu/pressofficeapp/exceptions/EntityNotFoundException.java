package com.nbu.pressofficeapp.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String type, long id) {
        this(type, "id", String.valueOf(id));
    }

    public EntityNotFoundException(String type, String attribute, String value) {
        super(String.format("%s with %s %s not found", type, attribute, value));
    }}
