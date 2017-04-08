package com.wuspace.domain;

import org.quartz.Scheduler;

/**
 * Created by WUCHAO on 2017/4/8.
 */
public interface Jobable {

    Scheduler convertToScheduler();
}
