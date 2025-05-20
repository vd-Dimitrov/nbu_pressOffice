package org.example.models;

import org.example.models.enums.PaperType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PressOffice {
    private BigDecimal basePaperPrice;
    private double priceIncreasePercent;
    private BigDecimal managerBonusThreshold;
    private List<Employee> employeeList;
    private Map<PaperType, Long> paperAmount;
    private BigDecimal paperCosts;
    private BigDecimal salaryCosts;
}
