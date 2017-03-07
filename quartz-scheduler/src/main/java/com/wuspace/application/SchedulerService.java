package com.wuspace.application;

import com.wuspace.application.job.SchedulerJob;

public interface SchedulerService {

    void addJob(SchedulerJob job);
}
