package com.zhang.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {
    public static LocalDateTime toStartOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.atStartOfDay();
    }

    public static LocalDateTime toEndOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.atTime(23, 59, 59, 999_999_999);
    }
}