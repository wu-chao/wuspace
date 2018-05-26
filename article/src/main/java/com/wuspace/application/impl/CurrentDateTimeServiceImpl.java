package com.wuspace.application.impl;

import com.wuspace.application.DateTimeService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class CurrentDateTimeServiceImpl implements DateTimeService {
    @Override
    public ZonedDateTime getCurrentDateTime() {
        return ZonedDateTime.now();
    }
}
