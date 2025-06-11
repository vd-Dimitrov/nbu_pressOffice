package com.nbu.pressofficeapp.commands.pressMachine;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.enums.PaperType;
import com.nbu.pressofficeapp.models.enums.PaperSize;
import com.nbu.pressofficeapp.utils.ParsingHelpers;

import java.util.List;

public class PrintArticleCommand extends BaseCommand {

    public static final String INVALID_PARAMETER_TYPE = "Invalid parameter type!";

    public PrintArticleCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {

        return "";
    }

    private String printArticles(List<String> parameters){
        int pressMachineId = ParsingHelpers.tryParseInteger(parameters.get(0));
        int paperAmount = ParsingHelpers.tryParseInteger(parameters.get(1));
        PaperType paperType = ParsingHelpers.tryParseEnum(parameters.get(2), PaperType.class, INVALID_PARAMETER_TYPE);
        PaperSize paperSize = ParsingHelpers.tryParseEnum(parameters.get(3), PaperSize.class, INVALID_PARAMETER_TYPE);

        return "";
    }
}
