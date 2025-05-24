package com.nbu.pressofficeapp.core;

import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.exceptions.EntityNotFoundException;
import com.nbu.pressofficeapp.models.Employee;
import com.nbu.pressofficeapp.models.PressOffice;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class PressOfficeRepositoryImpl implements PressOfficeRepository {
    private final List<PressOffice> pressOffices;
    private final List<Employee> employees;
    private final String OFFICE_NOT_FOUND = "Office with name %s not found.";

    public PressOfficeRepositoryImpl() {
        this.pressOffices = new ArrayList<>();
        this.employees = new ArrayList<>();
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

    @Override
    public Employee createEmployee(String name, BigDecimal salary) {
        Employee newEmployee = new Employee(name, salary);
        employees.add(newEmployee);

        return newEmployee;
    }

    @Override
    public PressOffice findOfficeByName(String name) {
        return pressOffices.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow( () -> new InvalidParameterException(String.format(OFFICE_NOT_FOUND, name)));
    }

    @Override
    public Employee findEmployeeById(long id) {
        return employees.stream()
                .filter( e -> e.getId() == id)
                .findFirst()
                .orElseThrow( () -> new EntityNotFoundException("Employee", id));
    }

    @Override
    public Employee findEmployeeByName(String name) {
        return employees.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow( () -> new EntityNotFoundException("Employee", "name", name));
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(this.employees);
    }
}
