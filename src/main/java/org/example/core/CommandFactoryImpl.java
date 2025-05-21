package org.example.core;

import org.example.commands.contracts.Command;
import org.example.core.contracts.CommandFactory;

public class CommandFactoryImpl implements CommandFactory {
    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, PressOfficeRepositoryImpl pressOfficeRepository) {
        return null;
    }
}
