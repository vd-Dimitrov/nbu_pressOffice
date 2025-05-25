package com.nbu.pressofficeapp.commands.office;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.commands.contracts.Command;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.models.enums.PaperType;
import com.nbu.pressofficeapp.utils.ParsingHelpers;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateOfficeCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 8;
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
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        BigDecimal basePaperPriceNormal = ParsingHelpers.tryParseBigDecimal(parameters.get(1));
        BigDecimal basePaperPriceGlossy = ParsingHelpers.tryParseBigDecimal(parameters.get(2));
        BigDecimal basePaperPriceNewspaper = ParsingHelpers.tryParseBigDecimal(parameters.get(3));
        Map<PaperType, BigDecimal> basePaperPrice = new HashMap<>();
        basePaperPrice.put(PaperType.REGULAR, basePaperPriceNormal);
        basePaperPrice.put(PaperType.GLOSSY, basePaperPriceGlossy);
        basePaperPrice.put(PaperType.NEWSPAPER, basePaperPriceNewspaper);

        double priceIncreasePercent = ParsingHelpers.tryParseDouble(parameters.get(4));
        BigDecimal managerBonusThreshold = ParsingHelpers.tryParseBigDecimal(parameters.get(5));
        int paperDiscountAmount = ParsingHelpers.tryParseInteger(parameters.get(6));
        double paperDiscountPercent = ParsingHelpers.tryParseDouble(parameters.get(7));

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
