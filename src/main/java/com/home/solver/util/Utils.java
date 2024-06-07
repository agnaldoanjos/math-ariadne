package com.home.solver.util;

import com.home.solver.expression.math.DateTimeExpression;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Utils {

    // Define o padrão regex para corresponder ao formato "yyyy-MM-dd'T'HH:mm:ss.SSS"
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile(
            "^\\[\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\]$"
    );

    public static boolean isValidDateTimeFormat(String dateTimeString) {
        // Verifica se a string corresponde ao padrão regex
        if (DATE_TIME_PATTERN.matcher(dateTimeString).matches()) {
            try {
                // Remove os colchetes antes de tentar fazer o parsing para um LocalDateTime
                String strippedDateTime = dateTimeString.substring(1, dateTimeString.length() - 1);
                // Tenta fazer o parsing da string para um LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
                LocalDateTime.parse(strippedDateTime, formatter);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }
        return false;
    }

    public static LocalDateTime parseDateTime(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return LocalDateTime.parse(value, formatter);
    }
}
