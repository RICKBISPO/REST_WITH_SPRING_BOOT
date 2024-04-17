package com.example.rest_with_spring_boot.controller;

import com.example.rest_with_spring_boot.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double sum(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!MathControllerUtilities.isNumeric(numberOne) || !MathControllerUtilities.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        else {
            return MathControllerUtilities.convertToDouble(numberOne) + MathControllerUtilities.convertToDouble(numberTwo);
        }
    }

    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double subtraction(@PathVariable(value = "numberOne") String numberOne,
                              @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!MathControllerUtilities.isNumeric(numberOne) || !MathControllerUtilities.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        else {
            return MathControllerUtilities.convertToDouble(numberOne) - MathControllerUtilities.convertToDouble(numberTwo);
        }
    }

    @RequestMapping(value = "/multi/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double multiplication(@PathVariable(value = "numberOne") String numberOne,
                                 @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!MathControllerUtilities.isNumeric(numberOne) || !MathControllerUtilities.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        else {
            return MathControllerUtilities.convertToDouble(numberOne) * MathControllerUtilities.convertToDouble(numberTwo);
        }
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double division(@PathVariable(value = "numberOne") String numberOne,
                           @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!MathControllerUtilities.isNumeric(numberOne) || !MathControllerUtilities.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        else {
            return MathControllerUtilities.convertToDouble(numberOne) / MathControllerUtilities.convertToDouble(numberTwo);
        }
    }

    @RequestMapping(value = "/avg/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public double average(@PathVariable(value = "numberOne") String numberOne,
                           @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!MathControllerUtilities.isNumeric(numberOne) || !MathControllerUtilities.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        else {
            return (MathControllerUtilities.convertToDouble(numberOne) + MathControllerUtilities.convertToDouble(numberTwo)) / 2;
        }
    }

}
