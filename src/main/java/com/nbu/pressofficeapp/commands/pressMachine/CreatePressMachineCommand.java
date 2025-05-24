package com.nbu.pressofficeapp.commands.pressMachine;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.PressMachine;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.util.List;

public class CreatePressMachineCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    private static final String MACHINE_ADDED_SUCCESSFULLY = "Machine with id %d added successfully to office %s!";

    public CreatePressMachineCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);


        return createPressMachine(parameters);
    }

    private String createPressMachine(List<String> parameters) {
        int paperCapacity = Integer.parseInt(parameters.get(0));
        int pagesPerMinute = Integer.parseInt(parameters.get(1));
        boolean printsColored = Boolean.parseBoolean(parameters.get(2));

        PressMachine newMachine =  new PressMachine(paperCapacity, pagesPerMinute, printsColored);
        PressOffice soughtOffice = getPressOfficeRepository().findOfficeByName(parameters.get(3));

        soughtOffice.addPressMachine(newMachine);

        return String.format(MACHINE_ADDED_SUCCESSFULLY, newMachine.getId(), soughtOffice.getName());
    }
}
