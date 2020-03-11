package com.github.wuchao.webproject.java.time;

import com.github.wuchao.webproject.util.DateTimeUtils;
import com.github.wuchao.webproject.util.NumberUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTimeTests {


    @Test
    public void testPeriod() {
        LocalDateTime startTime = LocalDateTime.of(2017, 8, 8, 10, 10, 10);
        LocalDateTime endTime = LocalDateTime.of(2018, 9, 9, 10, 10, 10);

        Period period = Period.between(startTime.toLocalDate(), endTime.toLocalDate());

        System.out.println("period years: " + period.getYears());
        System.out.println("period months: " + period.getMonths());
        System.out.println("period days: " + period.getDays());

        /**
         * period years: 1
         * period months: 1
         * period days: 1
         */
    }

    @Test
    public void testDuration() throws ParseException {
        LocalDateTime startTime = LocalDateTime.of(2017, 8, 8, 10, 10, 10);
        LocalDateTime endTime = LocalDateTime.of(2018, 9, 9, 10, 10, 10);

        Duration duration = Duration.between(startTime, endTime);

        System.out.println("--------------------------------------");
        System.out.println("duration days: " + duration.toDays() + " == "
                + DateTimeUtils.dayDuration(startTime, endTime) + " == "
                + DateTimeUtils.dayDuration(startTime.toLocalDate(), endTime.toLocalDate())); // 天数
        System.out.println("duration hours: " + duration.toHours() + " == " + DateTimeUtils.hourDuration(startTime, endTime)); // 小时
        System.out.println("duration minutes: " + duration.toMinutes() + " == " + DateTimeUtils.minuteDuration(startTime, endTime)); // 分钟数
        System.out.println("duration millis: " + duration.toMillis() + " == " + DateTimeUtils.milliDuration(startTime, endTime)); // 毫秒
        System.out.println("duration nanos: " + duration.toNanos()); // 纳秒

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("duration days: " + DateTimeUtils.dayDuration(simpleDateFormat.parse("2017-08-08"),
                simpleDateFormat.parse("2018-09-09")));

        /**
         * duration days: 397
         * duration hours: 9528
         * duration minutes: 571680
         * duration millis: 34300800000
         * duration nanos: 34300800000000000
         * duration days: 397
         */

    }

    /**
     * https://my.oschina.net/githubhty/blog/1610049
     */
    @Test
    public void testLocalDateTime2() {

        LocalDate today = LocalDate.of(2017, 12, 1);

        // 星期几
        int dayOfWeek = today.getDayOfWeek().getValue();
        System.out.println("今天是：星期" + NumberUtils.trans2ChineseNumber(dayOfWeek));

        // 取本月第1天：
        LocalDate firstDayOfThisMonth1 = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate firstDayOfThisMonth2 = today.withDayOfMonth(1);
        System.out.println("取本月第1天:" + firstDayOfThisMonth1);
        System.out.println("取本月第1天:" + firstDayOfThisMonth2);

        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2);
        System.out.println("取本月第2天:" + secondDayOfThisMonth);

        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("取本月最后一天:" + lastDayOfThisMonth);

        // 取下一天：
        LocalDate firstDayOf2019 = lastDayOfThisMonth.plusDays(1);
        System.out.println("取下一天:" + firstDayOf2019);

        // 取今年1月第一个周一
        LocalDate firstMondayOf2017 = LocalDate.parse("2017-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println("取今年1月第一个周一:" + firstMondayOf2017);

        // Temporal


        // TemporalAccessor

        // TemporalAdjusters

    }


    @Test
    public void testDateTimeToChinese() {
        LocalDate localDate = LocalDate.of(2018, 9, 10);
        System.out.println("日期是：" + DateTimeUtils.localDateWithChinese(localDate));
    }


    @Test
    public void testCompareTo() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime t1 = LocalDateTime.parse("2019-06-22 13:40:00", dateTimeFormatter);
        LocalDateTime t2 = LocalDateTime.parse("2019-06-22 13:40:01", dateTimeFormatter);
        LocalDateTime t3 = LocalDateTime.parse("2019-06-22 13:40:01", dateTimeFormatter);
        LocalDateTime t4 = LocalDateTime.parse("2023-03-31 00:00:00", dateTimeFormatter);
        LocalDateTime t5 = LocalDateTime.parse("2043-04-01 00:00:00", dateTimeFormatter);
        System.out.println(t1.compareTo(t2));
        System.out.println(t2.compareTo(t3));
        System.out.println(t3.compareTo(t1));
        System.out.println(t4.compareTo(LocalDateTime.now()));
        System.out.println(t5.compareTo(t4));

        /**
         * 执行结果为：
         * -1
         * 0
         * 1
         * 4
         * 20
         */
    }

    @Test
    public void testParseLocalDate() {
        String dateTimeStr = "2019-11-14 20:22:58";
        String dateStr = "2019-11-15";
        System.out.println(LocalDate.parse(dateTimeStr.substring(0, 10)));
        System.out.println(LocalDate.parse(dateStr.substring(0, 10)));
    }

}
