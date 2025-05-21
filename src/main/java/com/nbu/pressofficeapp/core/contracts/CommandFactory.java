package com.nbu.pressofficeapp.core.contracts;

import com.nbu.pressofficeapp.commands.contracts.Command;
import com.nbu.pressofficeapp.core.PressOfficeRepositoryImpl;

public interface CommandFactory {
    Command createCommandFromCommandName(String commandTypeAsString, PressOfficeRepositoryImpl pressOfficeRepository);
}
