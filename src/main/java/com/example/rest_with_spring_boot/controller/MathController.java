package com.example.rest_with_spring_boot.controller;

import com.example.rest_with_spring_boot.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {

    @RequestMapping(value = "/{operation}/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double calculate(@PathVariable(value = "operation") String operation,
                            @PathVariable(value = "numberOne") String numberOne,
                            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        else {
            Double numberOneConverted = convertToDouble(numberOne);
            Double numberTwoConverted = convertToDouble(numberTwo);

            return switch (operation) {
                case "sum" -> numberOneConverted + numberTwoConverted;
                case "sub" -> numberOneConverted - numberTwoConverted;
                case "mult" -> numberOneConverted * numberTwoConverted;
                case "div" -> numberOneConverted / numberTwoConverted;
                case "avg" -> (numberOneConverted + numberTwoConverted) / 2;
                default -> throw new UnsupportedMathOperationException("Operation not supported!");
            };
        }
    }

    private Double convertToDouble(String strNumber) {
        try {
            strNumber = strNumber.replaceAll(",", ".");
            return Double.parseDouble(strNumber);
        } catch (NumberFormatException e) {
            return 0D;
        }
    }

    private Boolean isNumeric(String strNumber) {
        if (strNumber == null) {
            return false;
        }
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
