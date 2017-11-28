package com.wuspace.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.text.ParseException;

@Component
public class StringToDoubleConverter implements Converter<String, Double> {
    @Override
    public Double convert(String source) {
        if (!StringUtils.isEmpty(source)) {
            if (source.contains(",")) {
                DecimalFormat format = new DecimalFormat(",#.#");
                try {
                    return format.parse(source).doubleValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return Double.valueOf(source);
            }
        } else {
            return null;
        }
    }
}
