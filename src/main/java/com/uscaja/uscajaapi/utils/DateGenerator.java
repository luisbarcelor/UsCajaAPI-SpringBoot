package com.uscaja.uscajaapi.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateGenerator {
    public static String currentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }
}
