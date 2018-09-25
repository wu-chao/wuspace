package com.github.wuchao.webproject.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public abstract class DateTimeUtils {

    public static ZonedDateTime string2ZonedDateTime(String source, String pattern) {
        return LocalDate.parse(source, DateTimeFormatter.ofPattern(pattern)).atStartOfDay(ZoneId.systemDefault());
    }

    public static Period period(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate);
    }

    public static long yearPeriod(LocalDate startDate, LocalDate endDate) {
        return period(startDate, endDate).getYears();
    }

    public static Duration duration(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return Duration.between(startDateTime, endDateTime);
    }

    public static long dayDuration(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return duration(startDateTime, endDateTime).toDays();
    }

    public static long dayDuration(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public static long dayDuration(Date startDate, Date endDate) {
        long time1 = startDate.getTime();
        long time2 = endDate.getTime();
        long diff;

        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }

        return diff / (1000 * 60 * 60 * 24);
    }

    public static long hourDuration(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return duration(startDateTime, endDateTime).toHours();
    }

    public static long minuteDuration(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return duration(startDateTime, endDateTime).toMinutes();
    }

    public static long milliDuration(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return duration(startDateTime, endDateTime).toMillis();
    }

    /**
     * 数字转中文数字
     *
     * @param num
     * @return
     */
    private static String num2Chinese(int num) {
        // 映射个位数
        Character[] onesPlace = {'零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十'};

        // 映射十位数
        String[] tensPlace = {"", "十", "二十", "三十", "四十", "五十"};
        // 映射十位数的个位数
        Character[] onesPlaceForTensDigit = {' ', '一', '二', '三', '四', '五', '六', '七', '八', '九'};

        // 数字转化为中文函数
        return num <= 10 ? String.valueOf(onesPlace[num]) : tensPlace[(int) Math.floor(num / 10)] + onesPlaceForTensDigit[(int) Math.floor(num % 10)];
    }

    /**
     * LocalDate 转成中文日期格式
     *
     * @param localDate
     * @return
     */
    public static String localDateWithChinese(LocalDate localDate) {
        String localDataFormatStr = localDate.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));

        String[] dateNum = localDataFormatStr.split("\\D");

        // 年
        StringBuilder year = new StringBuilder();
        char[] yearNum = Integer.valueOf(dateNum[0]).toString().toCharArray();
        for (int i = 0; i < yearNum.length; i++) {
            year.append(num2Chinese(Integer.valueOf(String.valueOf(yearNum[i]))));
        }

        localDataFormatStr = localDataFormatStr.replace(dateNum[0], year.toString());

        // 月
        localDataFormatStr = localDataFormatStr.replace(dateNum[1], num2Chinese(Integer.valueOf(dateNum[1])));

        // 日
        localDataFormatStr = localDataFormatStr.replace(dateNum[2], num2Chinese(Integer.valueOf(dateNum[2])));

        return localDataFormatStr;
    }

}
