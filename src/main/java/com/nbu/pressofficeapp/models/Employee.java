package com.nbu.pressofficeapp.models;

import java.math.BigDecimal;

public class Employee {
    private final long id;
    private String name;
    private boolean isManager;
    private BigDecimal salary;
    private PressOffice assignedOffice;
    private static long idCounter;

    public Employee(String name, BigDecimal salary) {
        this.name = name;
        this.salary = salary;
        isManager = false;
        id = idCounter;
        idCounter++;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public PressOffice getAssignedOffice() {
        return assignedOffice;
    }

    public void setAssignedOffice(PressOffice assignedOffice) {
        this.assignedOffice = assignedOffice;
    }
}
