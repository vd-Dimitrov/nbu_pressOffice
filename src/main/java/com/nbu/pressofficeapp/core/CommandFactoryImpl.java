package com.nbu.pressofficeapp.core;

import com.nbu.pressofficeapp.commands.contracts.Command;
import com.nbu.pressofficeapp.commands.enums.CommandType;
import com.nbu.pressofficeapp.commands.office.CreateOfficeCommand;
import com.nbu.pressofficeapp.commands.office.view.ShowOfficesCommand;
import com.nbu.pressofficeapp.commands.employee.CreateEmployeeCommand;
import com.nbu.pressofficeapp.commands.printer.CreatePressMachine;
import com.nbu.pressofficeapp.core.contracts.CommandFactory;
import com.nbu.pressofficeapp.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, PressOfficeRepositoryImpl pressOfficeRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class, String.format(INVALID_COMMAND, commandTypeAsString));

        switch (commandType) {
            case CREATEOFFICE:
                return new CreateOfficeCommand(pressOfficeRepository);
            case CREATEPERSON:
                return new CreateEmployeeCommand(pressOfficeRepository);
            case SHOWOFFICES:
                return new ShowOfficesCommand(pressOfficeRepository);
            case CREATEPRESSMACHINE:
                return new CreatePressMachine(pressOfficeRepository);
            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
        }
    }
}
