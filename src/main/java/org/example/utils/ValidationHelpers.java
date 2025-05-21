package org.example.utils;

import org.example.exceptions.InvalidValueException;

import java.util.List;

public class ValidationHelpers {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d, Received: %d";

    public static void validateArgumentsCount(List<String> list, int expectedArgumentsCount){
        if (list.size() < expectedArgumentsCount || list.size() > expectedArgumentsCount){
            throw new InvalidValueException(String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedArgumentsCount, list.size()));
        }
    }

    public static void validateIntRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(message);
        }
    }
}
