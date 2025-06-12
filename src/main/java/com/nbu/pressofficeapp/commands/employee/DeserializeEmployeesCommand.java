package com.nbu.pressofficeapp.commands.employee;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.Employee;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class DeserializeEmployeesCommand extends BaseCommand {

    public static final String FAILED_TO_DESERIALIZE_EMPLOYEES = "Failed to deserialize employees";
    public static final String SUCCESSFULLY_DESERIALIZED_EMPLOYEES = "Successfully deserialized employees";

    public DeserializeEmployeesCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, 1);
        String filepath = "src/main/java/com/nbu/pressofficeapp/data/" + parameters.get(0) + ".ser";
        return deserializeEmployees(filepath);
    }

    private String deserializeEmployees(String filepath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            List<Employee> employees = (List<Employee>) ois.readObject();
            for (Employee employee : employees){
                getPressOfficeRepository().addEmployee(employee);
            }
            return SUCCESSFULLY_DESERIALIZED_EMPLOYEES;
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(FAILED_TO_DESERIALIZE_EMPLOYEES + e.getMessage());
        }
    }
}
