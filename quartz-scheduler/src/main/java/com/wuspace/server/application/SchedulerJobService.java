package com.wuspace.server.application;

import com.wuspace.server.domain.job.SchedulerJob;

public interface SchedulerJobService {

    void addJob(SchedulerJob job);
}
