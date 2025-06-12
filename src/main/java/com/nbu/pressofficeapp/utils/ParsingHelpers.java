package com.nbu.pressofficeapp.utils;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.List;

public class ParsingHelpers {
    private static final String INVALID_VALUE_TYPE = "%s is an incompatible value type!";
    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type, String errorMessage) {
        try {
            return Enum.valueOf(type, valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(errorMessage, valueToParse));
        }
    }

    public static Integer tryParseInteger(String valueToParse){
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException( String.format(INVALID_VALUE_TYPE, valueToParse));
        }
    }

    public static Long tryParseLong(String valueToParse){
        try {
            return Long.parseLong(valueToParse);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException( String.format(INVALID_VALUE_TYPE, valueToParse));
        }
    }

    public static Double tryParseDouble(String valueToParse){
        try{
            return Double.parseDouble(valueToParse);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException( String.format(INVALID_VALUE_TYPE, valueToParse));
        }
    }

    public static BigDecimal tryParseBigDecimal(String valueToParse){
            return BigDecimal.valueOf(tryParseDouble(valueToParse));
    }
    public static <E> void checkIfCollectionEmpty(List<E> collection, String errorMessage){
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(String.format(errorMessage));
        }
    }
    public static YearMonth tryParseYearMonth(String unformattedYearMonth){
        try{
            String[] separatedYearMonth = unformattedYearMonth.split("\\.");
            return YearMonth.of(Integer.parseInt(separatedYearMonth[0]), Integer.parseInt(separatedYearMonth[1]));
        } catch (DateTimeException e){
            throw new IllegalArgumentException(String.format(INVALID_VALUE_TYPE, unformattedYearMonth));
        }

    }
}
