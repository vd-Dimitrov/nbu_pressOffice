package org.example.core.contracts;

import org.example.commands.contracts.Command;
import org.example.core.PressOfficeRepositoryImpl;

public interface CommandFactory {
    Command createCommandFromCommandName(String commandTypeAsString, PressOfficeRepositoryImpl pressOfficeRepository);
}
