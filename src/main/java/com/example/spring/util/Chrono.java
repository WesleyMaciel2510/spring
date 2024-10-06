package com.example.spring.util;

import com.example.spring.exception.BadRequestException;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public abstract class Chrono {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATETIME_FORMAT = DATE_FORMAT + " HH:mm:ss";
    public static final String SYSDATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static LocalDateTime getCurrentTime() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toLocalDateTime();
    }

    public static LocalDateTime now() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toLocalDateTime();
    }

    public static LocalDateTime parseStringToLocalDateTime(String dateString, String pattern) {
        final DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(pattern).toFormatter();
        try {
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Incorrect Date Format. Expected format: " + pattern);
        }
    }

    public static String getGreeting(LocalDateTime localDateTime, Locale locale) {
        int hour = localDateTime.getHour();

        if (hour < 12) {
            return "Good morning";
        } else if (hour < 18) {
            return "Good afternoon";
        } else {
            return "Good evening";
        }
    }

    public static String getDayName(LocalDateTime dateTime, Locale locale) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.FULL, locale);
    }

    public static String getMonthName(LocalDateTime dateTime, Locale locale) {
        Month month = dateTime.getMonth();
        return month.getDisplayName(TextStyle.FULL, locale);
    }

    public static String calculateAge(int years, int months, int days) {
        StringBuilder ageStringBuilder = new StringBuilder();
        if (years > 0) ageStringBuilder.append(years).append(" years ");
        if (months > 0) ageStringBuilder.append(months).append(" months ");
        if (days > 0) ageStringBuilder.append(days).append(" days");

        return ageStringBuilder.toString().trim();
    }
}
