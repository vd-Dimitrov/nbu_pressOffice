package com.nbu.pressofficeapp.core.contracts;

import com.nbu.pressofficeapp.models.Employee;
import com.nbu.pressofficeapp.models.PressMachine;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.models.enums.PaperType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PressOfficeRepository {

     List<PressOffice> getOffices();
     PressOffice createOffice(String name, Map<PaperType, BigDecimal> basePaperPrice, double priceIncreasePercent,
                              BigDecimal managerBonusThreshold, int paperDiscountAmount, double paperDiscountPercent);
     Employee createEmployee(String name, BigDecimal salary);
     PressOffice findOfficeByName(String name);
     Employee findEmployeeById(long id);
     Employee findEmployeeByName(String name);
     void fireEmployee(String name);

     PressMachine findPressMachineById(long id);
}
