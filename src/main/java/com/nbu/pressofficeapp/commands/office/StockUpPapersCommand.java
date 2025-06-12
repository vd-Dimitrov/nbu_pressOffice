package com.nbu.pressofficeapp.commands.office;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.Paper;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.models.enums.PaperSize;
import com.nbu.pressofficeapp.models.enums.PaperType;
import com.nbu.pressofficeapp.utils.ParsingHelpers;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.math.BigDecimal;
import java.util.List;

public class StockUpPapersCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    private static final String INVALID_PAPER_TYPE = "%s is not a valid paper type!";
    private static final String INVALID_PAPER_SIZE = "%s is not a valid paper size!";

    public StockUpPapersCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String officeName = parameters.get(0);
        PaperType paperType = ParsingHelpers.tryParseEnum(parameters.get(1), PaperType.class, String.format(INVALID_PAPER_TYPE, parameters.get(1)));
        PaperSize paperSize = ParsingHelpers.tryParseEnum(parameters.get(2), PaperSize.class, String.format(INVALID_PAPER_SIZE, parameters.get(2)));
        long paperAmount = ParsingHelpers.tryParseLong(parameters.get(3));

        Paper paper = new Paper(paperType, paperSize);
        PressOffice soughtOffice = getPressOfficeRepository().findOfficeByName(officeName);
        BigDecimal paperPrice = soughtOffice.getBasePaperPrice().get(paperType);
        BigDecimal totalPaperExpense = paperPrice.multiply(BigDecimal.valueOf(paperAmount));


        soughtOffice.increasePaperCosts(totalPaperExpense);
        return soughtOffice.addPaper(paper, paperAmount);
    }
}
