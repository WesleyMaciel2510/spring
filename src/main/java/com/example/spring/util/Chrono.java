package com.example.spring.util;

import com.example.spring.exception.BadRequestException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public abstract class Chrono {
    public static final String FORMAT_DATE = "dd/MM/yyyy";
    public static final String FORMAT_DATETIME = FORMAT_DATE + " HH:mm:ss";
    public static final String FORMAT_SYSDATE = "yyyy-MM-dd'T'HH:mm:ss";

    public static LocalDateTime now() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toLocalDateTime();
    }

    public static LocalDateTime clone(LocalDateTime date) {
        return date != null
                ? LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(),
                date.getMinute(), date.getSecond(), date.getNano())
                : null;
    }

    public static LocalDateTime getStartOfDay(LocalDateTime date) {
        return (date == null ? now() : date).truncatedTo(ChronoUnit.DAYS);
    }

    public static LocalDateTime getEndOfDay(LocalDateTime date) {
        return (date == null ? now() : date).truncatedTo(ChronoUnit.DAYS).plusDays(1).plusSeconds(-1);
    }

    public static Long getTime(LocalDateTime dataHora) {
        return Timestamp.valueOf(dataHora).getTime();
    }

    public static Boolean isBetween(LocalDateTime date, LocalDateTime start, LocalDateTime end) {
        return ((date.isEqual(start) || date.isAfter(start)) && (date.equals(end) || date.isBefore(end)));
    }

    public static LocalDateTime fromString(String string, String mask) {
        final DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(mask).toFormatter();
        try {
            return LocalDateTime.parse(string, formatter);
        } catch (DateTimeParseException e) {
            throw new BadRequestException("Data no formato incorreto. Correto -> " + mask);
        }
    }

    public static String toString(LocalDateTime localDateTime, String mask) {
        return localDateTime == null ? null : localDateTime.format(DateTimeFormatter.ofPattern(mask));
    }

    public static String getGreetings(LocalDateTime localDateTime) {
        final Integer hour = localDateTime.getHour();

        if (hour < 12) {
            return "Bom dia";
        }

        if (hour < 18) {
            return "Boa tarde";
        }

        return "Boa noite";
    }

    public static String getDayName(LocalDateTime date) {
        switch (date.getDayOfWeek()) {
            case SUNDAY:
                return "Domingo";
            case MONDAY:
                return "Segunda-feira";
            case TUESDAY:
                return "Terça-feira";
            case WEDNESDAY:
                return "Quarta-feira";
            case THURSDAY:
                return "Quinta-feira";
            case FRIDAY:
                return "Sexta-feira";
            case SATURDAY:
                return "Sábado";
            default:
                return "";
        }
    }

    public static String getMonthName(LocalDateTime date) {
        switch (date.getMonth()) {
            case JANUARY:
                return "Janeiro";
            case FEBRUARY:
                return "Fevereiro";
            case MARCH:
                return "Março";
            case APRIL:
                return "Abril";
            case MAY:
                return "Maio";
            case JUNE:
                return "Junho";
            case JULY:
                return "Julho";
            case AUGUST:
                return "Agosto";
            case SEPTEMBER:
                return "Setembro";
            case OCTOBER:
                return "Outubro";
            case NOVEMBER:
                return "Novembro";
            case DECEMBER:
                return "Dezembro";
            default:
                return "";
        }
    }

    public static String getAge(Integer years, Integer months, Integer days) {
        String result = "";

        if (years == 1) {
            result = "1 ano, ";
        } else if (years > 1) {
            result = years + " anos, ";
        }

        if (months == 1) {
            result += months + " mês e ";
        } else if (months > 1) {
            result += months + " meses e ";
        } else if (result.length() > 0) {
            result = result.substring(0, result.length() - 2) + " e ";
        }

        if (days == 1) {
            result += days + " dia";
        } else if (days > 1) {
            result += days + " dias";
        } else if (months == 0 && result.length() > 0) {
            result = result.substring(0, result.length() - 3);
        } else if (result.length() > 0) {
            result = result.replace(", ", " e ").substring(0, result.length() - 2);
        }

        if (result.trim().equals("")) {
            result = "0 dias";
        }

        return result;
    }

    public static String getInfo(LocalDateTime localDateTime) {
        return localDateTime == null ? null
                : getDayName(localDateTime) + ", " + localDateTime.getDayOfMonth() + " de "
                + getMonthName(localDateTime) + " de " + localDateTime.getYear();
    }
}
