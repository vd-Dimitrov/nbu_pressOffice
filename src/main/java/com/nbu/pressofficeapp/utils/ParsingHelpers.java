package com.nbu.pressofficeapp.utils;

import java.util.List;

public class ParsingHelpers {
    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type, String errorMessage) {
        try {
            return Enum.valueOf(type, valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(errorMessage, valueToParse));
        }
    }

    public static <E> void checkIfCollectionEmpty(List<E> collection, String errorMessage){
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(String.format(errorMessage));
        }
    }
}
