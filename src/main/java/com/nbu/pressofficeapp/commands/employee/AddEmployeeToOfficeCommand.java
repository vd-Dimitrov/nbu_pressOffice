package com.nbu.pressofficeapp.commands.person;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.exceptions.PersonAlreadyEmployedException;
import com.nbu.pressofficeapp.models.Employee;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.util.List;

public class AddEmployeeToOfficeCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String PERSON_IS_ALREADY_A_MEMBER_OF_OFFICE = "The person is already a member of an office!";
    public static final String EMPLOYEE_SUCCESSFULLY_ADDED = "%s was successfully added to office %s";

    public AddEmployeeToOfficeCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,  EXPECTED_NUMBER_OF_ARGUMENTS);

        long employeeId = Long.parseLong(parameters.get(0));
        String officeName =  parameters.get(1);

        throwIfEmployeeIsMember(employeeId, officeName);


        return addPersonToOffice(employeeId, officeName);
    }

    private void throwIfEmployeeIsMember(long employeeId, String officeName) {
            if (getPressOfficeRepository().findOfficeByName(officeName).getEmployeeList()
                    .stream()
                    .anyMatch(employee -> employee.getId() == employeeId)){
                throw new PersonAlreadyEmployedException(PERSON_IS_ALREADY_A_MEMBER_OF_OFFICE);
            }
    }

    private String addPersonToOffice(long employeeId, String officeName) {
        Employee newEmployee = getPressOfficeRepository().findEmployeeById(employeeId);

        PressOffice soughtOffice = getPressOfficeRepository().findOfficeByName(officeName);
        soughtOffice.addMember(newEmployee);

        return String.format(EMPLOYEE_SUCCESSFULLY_ADDED, newEmployee.getName(), officeName);
    }
}
