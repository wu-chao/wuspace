package com.wuspace.time;

import com.wuspace.JavaUtilApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by wu_chao on 17-7-3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JavaUtilApplication.class)
@WebAppConfiguration
public class TimeUtilsTest {


    @Test
    public void testSimpleDateFormat() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

//        Date 与 String 之间的转换，字符串解析成Date，Date格式化成固定格式的字符串

        Date date = new Date(1359641834000L); // 2013-1-31 22:17:14
        String dateStr = "2013-1-31 22:17:14";

        try {
            Date parseDateStr = sdf.parse(dateStr);
            System.out.println(parseDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String formatDate = sdf.format(date);
        System.out.println(formatDate);
    }
}
