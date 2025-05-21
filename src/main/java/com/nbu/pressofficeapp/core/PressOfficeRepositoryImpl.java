package com.nbu.pressofficeapp.core;

import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.PressOffice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PressOfficeRepositoryImpl implements PressOfficeRepository {
    private final List<PressOffice> pressOffices;

    public PressOfficeRepositoryImpl() {
        this.pressOffices = new ArrayList<>();
    }

    @Override
    public List<PressOffice> getOffices() {
        return new ArrayList<>(this.pressOffices);
    }

    @Override
    public PressOffice createOffice(String name, BigDecimal basePaperPrice, double priceIncreasePercent,
                                    BigDecimal managerBonusThreshold, int paperDiscountAmount, double paperDiscountPercent){
        PressOffice newPressOffice = new PressOffice(name, basePaperPrice, priceIncreasePercent, managerBonusThreshold, paperDiscountAmount, paperDiscountPercent);
        pressOffices.add(newPressOffice);

        return newPressOffice;
    }

}
