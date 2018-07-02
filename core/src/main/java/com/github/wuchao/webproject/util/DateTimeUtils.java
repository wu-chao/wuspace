package com.github.wuchao.webproject.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public abstract class DateTimeUtils {

    public static ZonedDateTime string2ZonedDateTime(String source, String pattern) {
        return LocalDate.parse(source, DateTimeFormatter.ofPattern(pattern)).atStartOfDay(ZoneId.systemDefault());
    }

    /**
     * 两个时间之间相差距离多少天
     *
     * @return 相差天数
     */
    public static long getDistanceDays(String str1, String str2) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one = df.parse(str1);
        Date two = df.parse(str2);

        long time1 = one.getTime();
        long time2 = two.getTime();
        long diff;

        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }

        return diff / (1000 * 60 * 60 * 24);
    }

    public static long getDistanceDays(ZonedDateTime start, ZonedDateTime end) {
        return ChronoUnit.DAYS.between(start.toLocalDate(), end.toLocalDate());
    }

    public static long getYearPeriod(LocalDate start, LocalDate end) {
        return Period.between(start, end).getYears();
    }
}
