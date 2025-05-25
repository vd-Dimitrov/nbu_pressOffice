package com.nbu.pressofficeapp.commands.office.view;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

public class ShowOfficeFinanceCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String FINANCE_INFORMATION_MESSAGE = "Office %s costs are %s";
    private static final DecimalFormat df = new DecimalFormat("#,###.00");
    public ShowOfficeFinanceCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        PressOffice pressOffice = getPressOfficeRepository().findOfficeByName(parameters.get(0));
        BigDecimal paperCosts = pressOffice.getPaperCosts();
        BigDecimal salaryCosts = pressOffice.getSalaryCosts();


        BigDecimal totalCosts = salaryCosts.add(paperCosts);

        return String.format(FINANCE_INFORMATION_MESSAGE, pressOffice.getName(), df.format(totalCosts));
    }
}
