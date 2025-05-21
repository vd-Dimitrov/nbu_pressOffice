package org.example.commands.office;

import org.example.commands.contracts.Command;
import org.example.core.contracts.PressOfficeRepository;
import org.example.models.PressOffice;
import org.example.utils.ValidationHelpers;

import java.math.BigDecimal;
import java.util.List;

public class CreateOfficeCommand implements Command {

    public static final String OFFICE_CREATED = "Office was created.";
    private final PressOfficeRepository pressOfficeRepository;

    public CreateOfficeCommand(PressOfficeRepository pressOfficeRepository){
        this.pressOfficeRepository = pressOfficeRepository;
    }
    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, 5);
        return "";
    }

    private String createOffice(List<String> parameters){
        BigDecimal basePaperPrice = BigDecimal.valueOf(Long.parseLong(parameters.get(0)));
        double priceIncreasePercent = Double.parseDouble(parameters.get(1));
        BigDecimal managerBonusThreshold = BigDecimal.valueOf(Long.parseLong(parameters.get(2)));
        int paperDiscountAmount = Integer.parseInt(parameters.get(3));
        double paperDiscountPercent = Double.parseDouble(parameters.get(4));

        PressOffice newOffice = pressOfficeRepository.createOffice(basePaperPrice, priceIncreasePercent, managerBonusThreshold, paperDiscountAmount, paperDiscountPercent);
        return OFFICE_CREATED;
    }
}
