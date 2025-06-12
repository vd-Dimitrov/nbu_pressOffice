package com.nbu.pressofficeapp.models;

import com.nbu.pressofficeapp.exceptions.InvalidValueException;
import com.nbu.pressofficeapp.models.enums.PaperType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class PressOffice {
    public static final String PAPER_SUCCESSFULLY_MODIFIED = "Paper successfully modified, new total: %d";
    public static final String NOT_ENOUGH_PAPER = "Not enough paper.";
    public static Calendar calendar;

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
    private Map<YearMonth, BigDecimal> monthlyRevenue;

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
        monthlyPaperCosts.put(YearMonth.now(), BigDecimal.ZERO);
        monthlySalaryCosts = new HashMap<>();
        monthlySalaryCosts.put(YearMonth.now(), BigDecimal.ZERO);
        monthlyRevenue = new HashMap<>();
        monthlyRevenue.put(YearMonth.now(), BigDecimal.ZERO);

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
        } catch (InvalidValueException e){
            return NOT_ENOUGH_PAPER;
        }
    }

    public void sellArticle(long amount, Paper paper){
        BigDecimal pricePerPaper = basePaperPrice.get(paper.paperType);
        double priceSizeIncrease = paper.paperSize.sizeMod * priceIncreasePercent;
        pricePerPaper = pricePerPaper.multiply(BigDecimal.valueOf(priceSizeIncrease));
        BigDecimal totalOrderPrice = pricePerPaper.multiply(BigDecimal.valueOf(amount));
        BigDecimal totalValueThisMonth = monthlyPaperCosts.get(YearMonth.now()).add(totalOrderPrice);

        setMonthlyRevenue(YearMonth.now(), totalValueThisMonth);
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

    public BigDecimal getMonthlyRevenue(YearMonth date) {
        return monthlyRevenue.get(date);
    }

    public void setMonthlyRevenue(YearMonth date, BigDecimal monthlyRevenue) {
        this.monthlyRevenue.put(date, monthlyRevenue);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PressOffice that = (PressOffice) o;
        return Double.compare(priceIncreasePercent, that.priceIncreasePercent) == 0 && paperDiscountAmount == that.paperDiscountAmount && Double.compare(paperDiscountPercent, that.paperDiscountPercent) == 0 && Objects.equals(name, that.name) && Objects.equals(basePaperPrice, that.basePaperPrice) && Objects.equals(managerBonusThreshold, that.managerBonusThreshold) && Objects.equals(employeeList, that.employeeList) && Objects.equals(paperAmount, that.paperAmount) && Objects.equals(pressMachines, that.pressMachines) && Objects.equals(monthlyPaperCosts, that.monthlyPaperCosts) && Objects.equals(monthlySalaryCosts, that.monthlySalaryCosts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, basePaperPrice, priceIncreasePercent, managerBonusThreshold, employeeList, paperAmount, pressMachines, paperDiscountAmount, paperDiscountPercent, monthlyPaperCosts, monthlySalaryCosts);
    }

    @Override
    public String toString() {
        return "PressOffice{" +
                "name='" + name + '\'' + '\n' +
                "basePaperPrice=" + basePaperPrice + '\n' +
                "priceIncreasePercent=" + priceIncreasePercent + '\n' +
                "managerBonusThreshold=" + managerBonusThreshold + '\n' +
                "paperAmount=" + paperAmount + '\n' +
                "paperDiscountAmount=" + paperDiscountAmount + '\n' +
                "paperDiscountPercent=" + paperDiscountPercent + '\n' +
                "monthlyPaperCosts=" + monthlyPaperCosts + '\n' +
                "monthlySalaryCosts=" + monthlySalaryCosts +
                '}' + '\n' ;
    }
}
