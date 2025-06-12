package com.nbu.pressofficeapp.commands.pressMachine;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.Paper;
import com.nbu.pressofficeapp.models.PressMachine;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.utils.ParsingHelpers;

import java.util.List;

public class LoadPressMachineCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String PRESS_MACHINE_LOADED_MESSAGE = "Press machine loaded. Current paper amount: %d";

    public LoadPressMachineCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        long pressMachineId = ParsingHelpers.tryParseLong(parameters.get(0));
        PressMachine pressMachine = getPressOfficeRepository().findPressMachineById(pressMachineId);
        PressOffice pressOffice = pressMachine.getCurrentLocation();
        Paper supportedPaper = pressMachine.getSupportedPaper();
        long paperAmount = ParsingHelpers.tryParseInteger(parameters.get(1));
        if (paperAmount > pressOffice.getPaperAmount().get(supportedPaper)){
            return "Not enough paper available";
        }
        else if (paperAmount + pressMachine.getCurrentPaperCount() > pressMachine.getPaperCapacity()){
            long loadedPaper = pressMachine.getPaperCapacity() - pressMachine.getCurrentPaperCount();
            pressMachine.setCurrentPaperCount(pressMachine.getPaperCapacity());
            pressOffice.removePaper(supportedPaper, loadedPaper);

            return String.format(PRESS_MACHINE_LOADED_MESSAGE, pressMachine.getCurrentPaperCount());
        }
        else {
            pressMachine.loadPaper(paperAmount);
        }
        return String.format(PRESS_MACHINE_LOADED_MESSAGE, pressMachine.getCurrentPaperCount());
    }
}
