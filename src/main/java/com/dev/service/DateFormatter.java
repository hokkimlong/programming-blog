package com.dev.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    private static String defaultFormatPattern = "dd/MM/yyyy";

    public static String format(Date date, String formatPattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatPattern);
        return simpleDateFormat.format(date);
    }

    public static String format(Date date) {
        return DateFormatter.format(date, DateFormatter.defaultFormatPattern);
    }
}
