package com.github.wuchao.webproject.java.time;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

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
    public void testDuration() {
        LocalDateTime startTime = LocalDateTime.of(2017, 8, 8, 10, 10, 10);
        LocalDateTime endTime = LocalDateTime.of(2018, 9, 9, 10, 10, 10);

        Duration duration = Duration.between(startTime, endTime);

        System.out.println("duration days: " + duration.toDays()); // 天数
        System.out.println("duration hours: " + duration.toHours()); // 小时
        System.out.println("duration minutes: " + duration.toMinutes()); // 分钟数
        System.out.println("duration millis: " + duration.toMillis()); // 毫秒
        System.out.println("duration nanos: " + duration.toNanos()); // 纳秒

        /**
         * duration days: 397
         * duration hours: 9528
         * duration minutes: 571680
         * duration millis: 34300800000
         * duration nanos: 34300800000000000
         */

    }

}
