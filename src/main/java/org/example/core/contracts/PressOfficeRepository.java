package org.example.core.contracts;

import org.example.models.PressOffice;

import java.math.BigDecimal;

public interface PressOfficeRepository {
     PressOffice createOffice(BigDecimal basePaperPrice, double priceIncreasePercent,
                                    BigDecimal managerBonusThreshold, int paperDiscountAmount, double paperDiscountPercent);
}
