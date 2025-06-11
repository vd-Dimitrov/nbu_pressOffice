package com.nbu.pressofficeapp.models;

import com.nbu.pressofficeapp.models.enums.PaperType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PressOffice {
    public static final String PAPER_SUCCESSFULLY_MODIFIED = "Paper successfully modified, new total: %d";
    public static final String NOT_ENOUGH_PAPER = "Not enough paper.";

    private String name;
    private Map<PaperType, BigDecimal> basePaperPrice;
    private double priceIncreasePercent;
    private BigDecimal managerBonusThreshold;
    private List<Employee> employeeList;
    private Map<Paper, Long> paperAmount;
    private List<PressMachine> pressMachines;
    private int paperDiscountAmount;
    private double paperDiscountPercent;
    private Map<YearMonth, BigDecimal> monthlyPaperCosts;
    private Map<YearMonth, BigDecimal> monthlySalaryCosts;

    public PressOffice(String name, Map<PaperType, BigDecimal> basePaperPrice, double priceIncreasePercent, BigDecimal managerBonusThreshold, int paperDiscountAmount, double paperDiscountPercent) {
        this.name = name;
        this.basePaperPrice = basePaperPrice;
        this.priceIncreasePercent = priceIncreasePercent;
        this.managerBonusThreshold = managerBonusThreshold;
        this.employeeList = new ArrayList<>();
        this.paperAmount = new HashMap<>();
        this.pressMachines = new ArrayList<>();
        this.paperDiscountAmount = paperDiscountAmount;
        this.paperDiscountPercent = paperDiscountPercent;
        monthlyPaperCosts = new HashMap<>();
        monthlySalaryCosts = new HashMap<>();
    }

    public PressOffice() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<PaperType, BigDecimal> getBasePaperPrice() {
        return basePaperPrice;
    }

    public void setBasePaperPrice(PaperType paper, BigDecimal basePaperPrice) {
        this.basePaperPrice.put(paper, basePaperPrice);
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

    public void addMember(Employee employee) {
        employeeList.add(employee);
    }

    public void removeMember(Employee employee){
        employeeList.remove(employee);
    }

    public Map<Paper, Long> getPaperAmount() {
        return paperAmount;
    }
    public String addPaper(Paper paper, long inputAmount){
        if (paperAmount.containsKey(paper)){
            paperAmount.compute(paper, (k, oldAmount) -> (oldAmount == null) ? inputAmount : inputAmount + oldAmount);
        } else {
            paperAmount.putIfAbsent(paper, inputAmount);
        }
        return String.format(PAPER_SUCCESSFULLY_MODIFIED, paperAmount.get(paper));
    }

    public String removePaper(Paper paper, long inputAmount){
        try {
            if (paperAmount.containsKey(paper)) {
                long oldAmount = paperAmount.get(paper);
                if (oldAmount - inputAmount < 0) {
                    return NOT_ENOUGH_PAPER;
                }
                long newAmount = oldAmount - inputAmount;
                paperAmount.put(paper, newAmount);
                return String.format(PAPER_SUCCESSFULLY_MODIFIED, newAmount);
            } else {
                return "No such paper type!";
            }
        } catch (NullPointerException e){
            return NOT_ENOUGH_PAPER;
        }
    }

    public List<PressMachine> getPressMachines() {
        return pressMachines;
    }

    public void addPressMachine(PressMachine pressMachine) {
        pressMachines.add(pressMachine);
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

    public Map<YearMonth, BigDecimal> getMonthlyPaperCosts() {
        return monthlyPaperCosts;
    }

    public void setMonthlyPaperCosts(YearMonth date, BigDecimal monthlyPaperCosts) {
        this.monthlyPaperCosts.put(date, monthlyPaperCosts);
    }

    public void increasePaperCosts(BigDecimal paperCosts){
        setMonthlyPaperCosts(YearMonth.now(), monthlyPaperCosts.get(YearMonth.now()).add(paperCosts));
    }

    public Map<YearMonth, BigDecimal> getMonthlySalaryCosts() {
        return monthlySalaryCosts;
    }

    public void setMonthlySalaryCosts(YearMonth date, BigDecimal monthlySalaryCosts) {
        this.monthlySalaryCosts.put(date, monthlySalaryCosts);
    }
}
