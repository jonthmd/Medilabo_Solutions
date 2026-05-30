package com.medilabo.risk.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

/**
 * Util used to calculate age of persons from their birthdate in String format.
 */
@Component
public class CalculateAge {

    public int calculate(String birthDate){
        LocalDate localDate = LocalDate.parse(birthDate);
        return Period.between(localDate, LocalDate.now()).getYears();
    }
}
