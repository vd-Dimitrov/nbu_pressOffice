package com.nbu.pressofficeapp.utils;

import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.models.enums.PaperType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class TestUtils {
    public static  final String VALID_OFFICE_NAME = "TestOffice";
    public static  final BigDecimal VALID_PAPER_PRICE = BigDecimal.ONE;
    public static final Map<PaperType, BigDecimal> VALID_BASE_PAPER_PRICES = Map.of(PaperType.REGULAR, VALID_PAPER_PRICE, PaperType.GLOSSY, VALID_PAPER_PRICE, PaperType.NEWSPAPER, VALID_PAPER_PRICE);
    public static  final double VALID_PRICE_INCREASE = 1;
    public static  final BigDecimal VALID_MANAGER_BONUS_THRESHOLD = BigDecimal.ONE;
    public static  final int VALID_PAPER_DISCOUNT_AMOUNT = 10;
    public static  final double VALID_PAPER_DISCOUNT_PERCENT = 10;

    public static  final String  VALID_PAPER_PRICE_PARAMETER = "1";
    public static  final String VALID_PRICE_INCREASE_PARAMETER = "1";
    public static  final String VALID_MANAGER_BONUS_THRESHOLD_PARAMETER = "1";
    public static  final String VALID_PAPER_DISCOUNT_AMOUNT_PARAMETER = "1";
    public static  final String VALID_PAPER_DISCOUNT_PERCENT_PARAMETER = "1";

    public static final List<String> VALID_OFFICE_CREATION_PARAMETERS = List.of(VALID_OFFICE_NAME,  VALID_PAPER_PRICE_PARAMETER, VALID_PAPER_PRICE_PARAMETER, VALID_PAPER_PRICE_PARAMETER, VALID_PRICE_INCREASE_PARAMETER, VALID_MANAGER_BONUS_THRESHOLD_PARAMETER, VALID_PAPER_DISCOUNT_AMOUNT_PARAMETER, VALID_PAPER_DISCOUNT_PERCENT_PARAMETER);

    public static final String VALID_EMPLOYEE_NAME = "Test Employee Name";
    public static final BigDecimal VALID_EMPLOYEE_SALARY = BigDecimal.TEN;
    public static final String VALID_EMPLOYEE_SALARY_PARAMETER = "10";
    public static final List<String> VALID_EMPLOYEE_CREATION_PARAMETERS = List.of("Test", "Employee", "Name", VALID_EMPLOYEE_SALARY_PARAMETER);


}
