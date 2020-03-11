package com.github.wuchao.webproject.java.time;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantTests {

    @Test
    public void testToEpochMilli1() {
        /**
         * toEpochMilli：自 1970-01-01 00:00:00 以来的毫秒数
         */
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Instant instant1 = LocalDateTime.parse("2019-07-25 15:15:15", dateTimeFormatter)
                .atZone(ZoneId.systemDefault()).toInstant();
        Instant instant2 = LocalDateTime.parse("2019-07-25 15:15:20", dateTimeFormatter)
                .atZone(ZoneId.systemDefault()).toInstant();
        System.out.println("毫秒数的差：" + (instant2.toEpochMilli() - instant1.toEpochMilli()));
        System.out.println("毫秒数的差：" + (instant1.toEpochMilli() - instant2.toEpochMilli()));
    }

    @Test
    public void testToEpochMilli2() {
        /**
         * toEpochMilli：自 1970-01-01 00:00:00 以来的毫秒数
         */
        Instant instant1 = Instant.parse("2019-07-25T15:15:15.00Z");
        Instant instant2 = Instant.parse("2019-07-25T15:15:20.00Z");
        System.out.println("毫秒数的差：" + (instant2.toEpochMilli() - instant1.toEpochMilli()));
        System.out.println("毫秒数的差：" + (instant1.toEpochMilli() - instant2.toEpochMilli()));
    }

}
