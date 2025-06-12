package com.nbu.pressofficeapp.commands.pressMachine;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.exceptions.InvalidValueException;
import com.nbu.pressofficeapp.models.Paper;
import com.nbu.pressofficeapp.models.PressMachine;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.models.enums.PaperType;
import com.nbu.pressofficeapp.models.enums.PaperSize;
import com.nbu.pressofficeapp.utils.ParsingHelpers;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.util.List;

public class PrintArticleCommand extends BaseCommand {

    public static final String INVALID_PARAMETER_TYPE = "Invalid parameter type!";
    public static final String FAILED_TO_PRINT_ARTICLE = "Couldn't print the articles. Reason: %s";

    public PrintArticleCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, 5);
        return "";
    }

    private String printArticles(List<String> parameters){
        try {
            long pressMachineId = ParsingHelpers.tryParseInteger(parameters.get(0));
            PressMachine pressMachine = getPressOfficeRepository().findPressMachineById(pressMachineId);
            PressOffice pressOffice = pressMachine.getCurrentLocation();
            String releaseType = parameters.get(1);
            long paperAmount = ParsingHelpers.tryParseInteger(parameters.get(2));
            PaperType paperType = ParsingHelpers.tryParseEnum(parameters.get(3), PaperType.class, INVALID_PARAMETER_TYPE);
            PaperSize paperSize = ParsingHelpers.tryParseEnum(parameters.get(4), PaperSize.class, INVALID_PARAMETER_TYPE);
            Paper paper = new Paper(paperType, paperSize);
            boolean coloredPrint = Boolean.parseBoolean(parameters.get(5));
            ValidationHelpers.validatePressMachineRequest(pressMachine, paper, coloredPrint);

            pressMachine.getCurrentLocation().removePaper(paper, paperAmount);
            pressMachine.setReleasesPrinted(releaseType, paperAmount);
            pressMachine.setCurrentPaperCount(pressMachine.getCurrentPaperCount() - paperAmount);


            return "";
        } catch (InvalidValueException | IllegalArgumentException e){
            return String.format(FAILED_TO_PRINT_ARTICLE, e.getMessage());
        }
    }
}
