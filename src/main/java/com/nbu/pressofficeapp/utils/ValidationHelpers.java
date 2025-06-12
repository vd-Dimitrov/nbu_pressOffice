package com.nbu.pressofficeapp.utils;

import com.nbu.pressofficeapp.exceptions.DuplicateEntityException;
import com.nbu.pressofficeapp.exceptions.InvalidValueException;
import com.nbu.pressofficeapp.models.Employee;
import com.nbu.pressofficeapp.models.Paper;
import com.nbu.pressofficeapp.models.PressMachine;
import com.nbu.pressofficeapp.models.enums.PaperType;
import com.nbu.pressofficeapp.models.enums.PaperSize;

import java.util.List;

public class ValidationHelpers {
    public static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d, Received: %d";
    public static final String INVALID_NUMBER_OF_ARGUMENTS_SECONDARY = "Invalid number of arguments. Expected: %d or %d, Received: %d";

    public static void validateArgumentsCount(List<String> list, int expectedArgumentsCount){
        if (list.size() < expectedArgumentsCount || list.size() > expectedArgumentsCount){
            throw new InvalidValueException(String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedArgumentsCount, list.size()));
        }
    }

    public static void validateArgumentsCount (List<String> list, int expectedArgumentsCount, int secondaryExpectedArgumentsCount){
        if (list.size() !=  expectedArgumentsCount || list.size() !=  secondaryExpectedArgumentsCount){
            throw new InvalidValueException(String.format(INVALID_NUMBER_OF_ARGUMENTS_SECONDARY, expectedArgumentsCount, secondaryExpectedArgumentsCount, list.size()));
        }
    }

    public static void validateIntRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validatePressMachineRequest(PressMachine pressMachine, Paper supportedPaper, boolean coloredPrint){
        if (!pressMachine.getSupportedPaper().equals(supportedPaper)){
            throw new InvalidValueException(supportedPaper.toString(), pressMachine.toString(), "unsupported paper");
        } else if (!pressMachine.isPrintsColored() && coloredPrint) {
            throw new InvalidValueException("This printer does not support colored printing");
        }
    }

    public static boolean validateUniqueEmployee(List<Employee> employees, Employee checkedEmployee){
        return employees.stream().anyMatch(e -> e.equals(checkedEmployee));
    }
}
