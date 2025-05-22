package com.nbu.pressofficeapp.commands;

import com.nbu.pressofficeapp.commands.contracts.Command;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;

import java.util.List;

public abstract class BaseCommand implements Command {
    private final PressOfficeRepository pressOfficeRepository;

    public BaseCommand(PressOfficeRepository pressOfficeRepository){
        this.pressOfficeRepository = pressOfficeRepository;
    }

    public PressOfficeRepository getPressOfficeRepository(){
        return pressOfficeRepository;
    }

    @Override
    public String execute(List<String> parameters){
        return executeCommand(parameters);
    }

    protected abstract String executeCommand(List<String> parameters);
}
