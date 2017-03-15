package com.wuspace.server.validation;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

/**
 *
 * Convert string value of <code>yyyy-[m]m-[d]d hh:mm:ss[.f...]</code> format to {@code java.sql.Timestamp} value.
 *
 * @author snowblink on 15/11/12.
 */
public class StringToTimeStampConverter implements Converter<String, Timestamp> {

    @Override public Timestamp convert(String source) {
        return Timestamp.valueOf(source);
    }
}
