package com.nbu.pressofficeapp.commands.employee;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.math.BigDecimal;
import java.util.List;

public class CreateEmployeeCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    public static final String PERSON_CREATED = "Person with a name: %s was created.";


    public CreateEmployeeCommand(PressOfficeRepository pressOfficeRepository){
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);


        return createEmployee(parameters);
    }

    private String createEmployee(List<String> parameters) {
        String name = parameters.get(0) + " " + parameters.get(1) + " " + parameters.get(2);


        BigDecimal salary = BigDecimal.valueOf(Double.parseDouble(parameters.get(3)));
        getPressOfficeRepository().createEmployee(name, salary);


        return String.format(PERSON_CREATED, name);

    }


}
