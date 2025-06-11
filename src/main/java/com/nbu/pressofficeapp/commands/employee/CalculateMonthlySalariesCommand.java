package com.nbu.pressofficeapp.commands.employee;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;

import java.util.List;

public class CalculateMonthlySalariesCommand extends BaseCommand {
    public CalculateMonthlySalariesCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        return "";
    }
}
