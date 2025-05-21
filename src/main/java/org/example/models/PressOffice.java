package org.example.models;

import org.example.models.enums.PaperType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PressOffice {
    private BigDecimal basePaperPrice;
    private double priceIncreasePercent;
    private BigDecimal managerBonusThreshold;
    private List<Employee> employeeList;
    private Map<PaperType, Long> paperAmount;
    private List<PressMachine> pressMachines;
    private int paperDiscountAmount;
    private double paperDiscountPercent;
    private BigDecimal paperCosts;
    private BigDecimal salaryCosts;

    public PressOffice(BigDecimal basePaperPrice, double priceIncreasePercent, BigDecimal managerBonusThreshold, int paperDiscountAmount, double paperDiscountPercent) {
        this.basePaperPrice = basePaperPrice;
        this.priceIncreasePercent = priceIncreasePercent;
        this.managerBonusThreshold = managerBonusThreshold;
        this.employeeList = new ArrayList<>();
        this.paperAmount = new HashMap<>();
        this.pressMachines = new ArrayList<>();
        this.paperDiscountAmount = paperDiscountAmount;
        this.paperDiscountPercent = paperDiscountPercent;
        paperCosts = BigDecimal.ZERO;
        salaryCosts = BigDecimal.ZERO;
    }

    public PressOffice() {
    }

    public BigDecimal getBasePaperPrice() {
        return basePaperPrice;
    }

    public void setBasePaperPrice(BigDecimal basePaperPrice) {
        this.basePaperPrice = basePaperPrice;
    }

    public double getPriceIncreasePercent() {
        return priceIncreasePercent;
    }

    public void setPriceIncreasePercent(double priceIncreasePercent) {
        this.priceIncreasePercent = priceIncreasePercent;
    }

    public BigDecimal getManagerBonusThreshold() {
        return managerBonusThreshold;
    }

    public void setManagerBonusThreshold(BigDecimal managerBonusThreshold) {
        this.managerBonusThreshold = managerBonusThreshold;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Map<PaperType, Long> getPaperAmount() {
        return paperAmount;
    }

    public void setPaperAmount(Map<PaperType, Long> paperAmount) {
        this.paperAmount = paperAmount;
    }

    public List<PressMachine> getPressMachines() {
        return pressMachines;
    }

    public void setPressMachines(List<PressMachine> pressMachines) {
        this.pressMachines = pressMachines;
    }

    public int getPaperDiscountAmount() {
        return paperDiscountAmount;
    }

    public void setPaperDiscountAmount(int paperDiscountAmount) {
        this.paperDiscountAmount = paperDiscountAmount;
    }

    public double getPaperDiscountPercent() {
        return paperDiscountPercent;
    }

    public void setPaperDiscountPercent(double paperDiscountPercent) {
        this.paperDiscountPercent = paperDiscountPercent;
    }

    public BigDecimal getPaperCosts() {
        return paperCosts;
    }

    public void setPaperCosts(BigDecimal paperCosts) {
        this.paperCosts = paperCosts;
    }

    public BigDecimal getSalaryCosts() {
        return salaryCosts;
    }

    public void setSalaryCosts(BigDecimal salaryCosts) {
        this.salaryCosts = salaryCosts;
    }
}
