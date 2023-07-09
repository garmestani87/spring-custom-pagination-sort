package com.garm.pagination.utils;

public class RegexUtils {

    public static boolean isValidDate(String str) {
        return str.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    public static boolean isValidDateTime(String str) {
        return str.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");
    }

    public static boolean isValidTime(String str) {
        return str.matches("^\\d{2}:\\d{2}:\\d{2}$");
    }

}