package com.nbu.pressofficeapp.commands.employee.view;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.Employee;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.util.List;

public class ViewEmployeesCommand extends BaseCommand {
    public ViewEmployeesCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, 0);
        return showEmployees(getPressOfficeRepository().getEmployees());
    }

    private String showEmployees(List<Employee> employeeList){
        StringBuilder stringBuilder = new StringBuilder();
        for (Employee employee : employeeList){
            stringBuilder.append(employee.toString());
        }
        return stringBuilder.toString();

    }


}
