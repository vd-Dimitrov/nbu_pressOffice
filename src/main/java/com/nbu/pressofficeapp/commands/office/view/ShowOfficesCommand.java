package com.nbu.pressofficeapp.commands.office.view;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.utils.ParsingHelpers;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ShowOfficesCommand extends BaseCommand{
    private static final int EXPECTED_ARGUMENTS = 0;
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String NO_OFFICES_ERROR ="No offices in the system!";

    public ShowOfficesCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS);

        ParsingHelpers.checkIfCollectionEmpty(getPressOfficeRepository().getOffices(), NO_OFFICES_ERROR);

        String result = getPressOfficeRepository().getOffices()
                .stream()
                .map(PressOffice::toString)
                .collect(Collectors.joining(NEW_LINE_DELIMITER));

        return result;
    }

}
