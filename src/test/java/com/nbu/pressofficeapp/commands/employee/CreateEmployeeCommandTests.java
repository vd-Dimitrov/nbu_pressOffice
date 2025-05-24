package com.nbu.pressofficeapp.commands.employee;

import com.nbu.pressofficeapp.core.PressOfficeRepositoryImpl;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.exceptions.InvalidValueException;
import com.nbu.pressofficeapp.models.Employee;
import com.nbu.pressofficeapp.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateEmployeeCommandTests {
    PressOfficeRepository pressOfficeRepository;
    CreateEmployeeCommand createEmployeeCommand;

    @BeforeEach
    public void setup() {
        pressOfficeRepository = new PressOfficeRepositoryImpl();
        createEmployeeCommand = new CreateEmployeeCommand(pressOfficeRepository);
    }

    @Test
    public void should_ThrowException_When_InvalidParameterCount(){
        List<String> parameters = Collections.emptyList();
        Assertions.assertThrows(InvalidValueException.class, () -> createEmployeeCommand.execute(parameters));
    }

    @Test
    public void should_ExistInRepository_When_ValidParameters(){
        List<String> parameters = TestUtils.VALID_EMPLOYEE_CREATION_PARAMETERS;
        createEmployeeCommand.execute(parameters);


        Assertions.assertDoesNotThrow( () -> pressOfficeRepository.findEmployeeByName(TestUtils.VALID_EMPLOYEE_NAME));
    }
}
