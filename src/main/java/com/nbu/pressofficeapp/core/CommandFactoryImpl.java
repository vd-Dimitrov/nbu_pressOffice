package com.nbu.pressofficeapp.core;

import com.nbu.pressofficeapp.commands.ShowCommandDescriptions;
import com.nbu.pressofficeapp.commands.contracts.Command;
import com.nbu.pressofficeapp.commands.employee.FireEmployeeCommand;
import com.nbu.pressofficeapp.commands.enums.CommandType;
import com.nbu.pressofficeapp.commands.office.CreateOfficeCommand;
import com.nbu.pressofficeapp.commands.office.LoadPressOfficeCommand;
import com.nbu.pressofficeapp.commands.office.StockUpPapersCommand;
import com.nbu.pressofficeapp.commands.office.view.ShowOfficeFinanceCommand;
import com.nbu.pressofficeapp.commands.office.view.ShowOfficesCommand;
import com.nbu.pressofficeapp.commands.employee.AssignEmployeeCommand;
import com.nbu.pressofficeapp.commands.employee.CreateEmployeeCommand;
import com.nbu.pressofficeapp.commands.pressMachine.CreatePressMachineCommand;
import com.nbu.pressofficeapp.commands.pressMachine.LoadPressMachineCommand;
import com.nbu.pressofficeapp.core.contracts.CommandFactory;
import com.nbu.pressofficeapp.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, PressOfficeRepositoryImpl pressOfficeRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class, String.format(INVALID_COMMAND, commandTypeAsString));

        switch (commandType) {
            case SHOWCOMMANDDESCRIPTION:
                return new ShowCommandDescriptions(pressOfficeRepository);
            case CREATEOFFICE:
                return new CreateOfficeCommand(pressOfficeRepository);
            case CREATEPERSON:
                return new CreateEmployeeCommand(pressOfficeRepository);
            case SHOWOFFICES:
                return new ShowOfficesCommand(pressOfficeRepository);
            case CREATEPRESSMACHINE:
                return new CreatePressMachineCommand(pressOfficeRepository);
            case ASSIGNEMPLOYEETOOFFICE:
                return new AssignEmployeeCommand(pressOfficeRepository);
            case FIREEMPLOYEE:
                return new FireEmployeeCommand(pressOfficeRepository);
            case STOCKUPPAPERS:
                return new StockUpPapersCommand(pressOfficeRepository);
            case LOADPRESSMACHINE:
                return new LoadPressMachineCommand(pressOfficeRepository);
            case SHOWOFFICEFINANCE:
                return new ShowOfficeFinanceCommand(pressOfficeRepository);
            case LOADPRESSOFFICE:
                return new LoadPressOfficeCommand(pressOfficeRepository);
            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandTypeAsString));
        }
    }
}
