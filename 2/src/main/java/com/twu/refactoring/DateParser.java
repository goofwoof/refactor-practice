package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<>();
    private static final String NOT_INTEGET_ERROR = "%s is not an integer";
    private static final String OUT_OF_RANGE_ERROR = "%s cannot be less than %d or more than %d";
    private static final String LENGTH_LESS_ERROR = "%s string is less than %d characters";
    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(parseYear(), parseMonth() - 1, parseDate(), parseHour(), parseMinute(), 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private int parseMinute() {
        int minute;
        if (dateAndTimeString.startsWith("Z", 11)) {
            minute = 0;
        } else {
            try {
                String minuteString = dateAndTimeString.substring(14, 16);
                minute = Integer.parseInt(minuteString);
            } catch (StringIndexOutOfBoundsException e) {
                throw new IllegalArgumentException(String.format(LENGTH_LESS_ERROR, "Minute",2));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format(NOT_INTEGET_ERROR, "Minute"));
            }
            if (minute < 0 || minute > 59)
                throw new IllegalArgumentException(String.format(OUT_OF_RANGE_ERROR, "Minute", 0, 59));

        }
        return minute;
    }

    private int parseHour() {
        int hour;
        if (dateAndTimeString.startsWith("Z", 11)) {
            hour = 0;
        } else {
            try {
                String hourString = dateAndTimeString.substring(11, 13);
                hour = Integer.parseInt(hourString);
            } catch (StringIndexOutOfBoundsException e) {
                throw new IllegalArgumentException(String.format(LENGTH_LESS_ERROR, "Hour", 2));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format(NOT_INTEGET_ERROR, "Hour"));
            }
            if (hour < 0 || hour > 23)
                throw new IllegalArgumentException(String.format(OUT_OF_RANGE_ERROR, "Hour", 0, 23));
        }
        return hour;
    }

    private int parseDate() {
        int date;
        try {
            String dateString = dateAndTimeString.substring(8, 10);
            date = Integer.parseInt(dateString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format(LENGTH_LESS_ERROR, "Date", 2));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(NOT_INTEGET_ERROR, "Date"));
        }
        if (date < 1 || date > 31)
            throw new IllegalArgumentException(String.format(OUT_OF_RANGE_ERROR, "Date", 1, 31));
        return date;
    }

    private int parseMonth() {
        int month;
        try {
            String monthString = dateAndTimeString.substring(5, 7);
            month = Integer.parseInt(monthString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format(LENGTH_LESS_ERROR, "Month", 2));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(NOT_INTEGET_ERROR, "Month"));
        }
        if (month < 1 || month > 12)
            throw new IllegalArgumentException(String.format(OUT_OF_RANGE_ERROR, "Month", 1, 12));
        return month;
    }

    private int parseYear() {
        int year;
        try {
            String yearString = dateAndTimeString.substring(0, 4);
            year = Integer.parseInt(yearString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format(LENGTH_LESS_ERROR, "Year", 4));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(NOT_INTEGET_ERROR, "Year"));
        }
        if (year < 2000 || year > 2012)
            throw new IllegalArgumentException(String.format(OUT_OF_RANGE_ERROR, "Year", 2000,2012));
        return year;
    }
}
