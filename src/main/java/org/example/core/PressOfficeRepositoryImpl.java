package org.example.core;

import org.example.core.contracts.PressOfficeRepository;
import org.example.models.PressOffice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PressOfficeRepositoryImpl implements PressOfficeRepository {
    private final List<PressOffice> pressOffices;

    public PressOfficeRepositoryImpl() {
        this.pressOffices = new ArrayList<>();
    }

    @Override
    public PressOffice createOffice(BigDecimal basePaperPrice, double priceIncreasePercent,
                                    BigDecimal managerBonusThreshold, int paperDiscountAmount, double paperDiscountPercent){
        PressOffice newPressOffice = new PressOffice(basePaperPrice, priceIncreasePercent, managerBonusThreshold, paperDiscountAmount, paperDiscountPercent);
        pressOffices.add(newPressOffice);

        return newPressOffice;
    }

}
