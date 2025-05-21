package com.nbu.pressofficeapp.core.contracts;

import com.nbu.pressofficeapp.models.PressOffice;

import java.math.BigDecimal;
import java.util.List;

public interface PressOfficeRepository {

     List<PressOffice> getOffices();
     PressOffice createOffice(String name, BigDecimal basePaperPrice, double priceIncreasePercent,
                                    BigDecimal managerBonusThreshold, int paperDiscountAmount, double paperDiscountPercent);
}
