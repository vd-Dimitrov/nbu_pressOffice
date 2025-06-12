package com.nbu.pressofficeapp.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Employee implements Serializable {
    private final long id;
    private String name;
    private boolean isManager;
    private boolean hasBeenFired;
    private BigDecimal salary;
    private transient PressOffice assignedOffice;
    private static long idCounter;

    public Employee(String name, BigDecimal salary) {

        this.name = name;
        this.salary = salary;
        isManager = false;
        hasBeenFired = false;
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

    public boolean isHasBeenFired() {
        return hasBeenFired;
    }

    public void setHasBeenFired(boolean hasBeenFired) {
        this.hasBeenFired = hasBeenFired;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && isManager == employee.isManager && hasBeenFired == employee.hasBeenFired && Objects.equals(name, employee.name) && Objects.equals(salary, employee.salary) && Objects.equals(assignedOffice, employee.assignedOffice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isManager, hasBeenFired, salary, assignedOffice);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isManager=" + isManager +
                ", hasBeenFired=" + hasBeenFired +
                ", salary=" + salary +
                ", assignedOffice=" + assignedOffice.getName() +
                '}';
    }
}
