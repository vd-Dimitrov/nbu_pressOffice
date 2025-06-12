package com.nbu.pressofficeapp.commands.employee;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.Employee;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializeEmployeesCommand extends BaseCommand {

    public static final String SERIALIZATION_FAILED = "Serialization failed: %s";
    public static final String EMPLOYEES_SUCCESSFULLY_SERIALIZED = "Employees successfully serialized";

    public SerializeEmployeesCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, 1);
        String filepath = "src/main/java/com/nbu/pressofficeapp/data/" + parameters.get(0) + ".ser";
        List<Employee> employees = getPressOfficeRepository().getEmployees();
        return serializeEmployees(employees, filepath);
    }

    private String serializeEmployees(List<Employee> employees, String filepath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(employees);
            return EMPLOYEES_SUCCESSFULLY_SERIALIZED;
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format(SERIALIZATION_FAILED, e.getMessage()));
        }
    }
}
