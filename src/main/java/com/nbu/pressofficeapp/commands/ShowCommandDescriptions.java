package com.nbu.pressofficeapp.commands;

import com.nbu.pressofficeapp.commands.enums.CommandType;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;

import java.util.List;

public class ShowCommandDescriptions extends BaseCommand{

    public ShowCommandDescriptions(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        StringBuilder stringBuilder = new StringBuilder();
        CommandType.stream()
                .forEach(c -> stringBuilder.append(c.commandDescription).append("\n"));

        return stringBuilder.toString();
    }
}
