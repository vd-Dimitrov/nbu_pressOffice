package com.nbu.pressofficeapp.commands.employee;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.exceptions.EntityNotFoundException;
import com.nbu.pressofficeapp.models.Employee;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.util.List;

public class FireEmployeeCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String EMPLOYEE_REMOVED_SUCCESSFULLY = "%s has been successfully removed from repository";
    public FireEmployeeCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String employeeName = parameters.get(0) + " " + parameters.get(1) + " " + parameters.get(2);
        throwIfEmployeeIsNotInOffice(employeeName);


        return fireEmployee(employeeName);
    }

    private String fireEmployee(String employeeName) {
        getPressOfficeRepository().fireEmployee(employeeName);

        return employeeName;
    }

    private void throwIfEmployeeIsNotInOffice(String employeeName) {
        if (getPressOfficeRepository()
                .getOffices()
                .stream()
                .map(PressOffice::getEmployeeList)
                .flatMap(List::stream)
                .filter(employee -> employee.getName().equals(employeeName))
                .findAny()
                .orElse(null) == null) {
            throw new EntityNotFoundException("Employee", "name", employeeName);
        }
    }
}
