package com.nbu.pressofficeapp.commands.pressMachine;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.Paper;
import com.nbu.pressofficeapp.models.PressMachine;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.models.enums.PaperType;
import com.nbu.pressofficeapp.models.enums.PaperSize;
import com.nbu.pressofficeapp.utils.ParsingHelpers;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.util.List;

public class CreatePressMachineCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    private static final String MACHINE_ADDED_SUCCESSFULLY = "Machine with id %d added successfully to office %s!";
    private static final String INVALID_PAPER_TYPE = "%s is not a valid paper type!";
    private static final String INVALID_PAPER_SIZE = "%s is not a valid paper size!";

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
        PaperType supportedPaperType = ParsingHelpers.tryParseEnum(parameters.get(2), PaperType.class, String.format(INVALID_PAPER_TYPE, parameters.get(2)) );
        PaperSize supportedPaperPaperSize = ParsingHelpers.tryParseEnum(parameters.get(3), PaperSize.class, String.format(INVALID_PAPER_SIZE, parameters.get(3)) );

        Paper supportedPaper = new Paper(supportedPaperType, supportedPaperPaperSize);
        boolean printsColored = Boolean.parseBoolean(parameters.get(4));

        PressOffice soughtOffice = getPressOfficeRepository().findOfficeByName(parameters.get(3));
        PressMachine newMachine =  new PressMachine(paperCapacity, pagesPerMinute, supportedPaper, printsColored, soughtOffice);

        soughtOffice.addPressMachine(newMachine);

        return String.format(MACHINE_ADDED_SUCCESSFULLY, newMachine.getId(), soughtOffice.getName());
    }
}
