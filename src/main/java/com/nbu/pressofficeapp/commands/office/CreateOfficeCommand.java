package com.nbu.pressofficeapp.commands.office;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.commands.contracts.Command;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.math.BigDecimal;
import java.util.List;

public class CreateOfficeCommand extends BaseCommand {

    public static final String OFFICE_CREATED = "Office with name %s was created.";
    public static final String OFFICE_ALREADY_EXISTS = "Office with name %s already exists.";

    public CreateOfficeCommand(PressOfficeRepository pressOfficeRepository){
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, 6);

        return createOffice(parameters);    }

    private String createOffice(List<String> parameters){
        String name = parameters.get(0);
        BigDecimal basePaperPrice = BigDecimal.valueOf(Long.parseLong(parameters.get(1)));
        double priceIncreasePercent = Double.parseDouble(parameters.get(2));
        BigDecimal managerBonusThreshold = BigDecimal.valueOf(Long.parseLong(parameters.get(3)));
        int paperDiscountAmount = Integer.parseInt(parameters.get(4));
        double paperDiscountPercent = Double.parseDouble(parameters.get(5));

        PressOffice newOffice = getPressOfficeRepository().createOffice(name, basePaperPrice, priceIncreasePercent, managerBonusThreshold, paperDiscountAmount, paperDiscountPercent);
        return String.format(OFFICE_CREATED, name);
    }

    public void throwsIfOfficeExists(String officeName){
        boolean doesExist = getPressOfficeRepository().getOffices().stream()
                .anyMatch(office -> office.getName().equals(officeName));
        if (doesExist){
            throw new IllegalArgumentException(String.format(OFFICE_ALREADY_EXISTS, officeName));
        }
    }
}
