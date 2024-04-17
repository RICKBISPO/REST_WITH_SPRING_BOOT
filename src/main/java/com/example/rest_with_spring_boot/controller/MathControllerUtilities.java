package com.example.rest_with_spring_boot.controller;

public class MathControllerUtilities {

    public static Double convertToDouble(String strNumber) {
        try {
            strNumber = strNumber.replaceAll(",", ".");
            return Double.parseDouble(strNumber);
        } catch (NumberFormatException e) {
            return 0D;
        }
    }

    public static Boolean isNumeric(String strNumber) {
        if (strNumber == null) {
            return false;
        }
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
