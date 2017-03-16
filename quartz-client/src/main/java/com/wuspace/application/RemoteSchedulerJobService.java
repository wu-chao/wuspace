package com.wuspace.application;

import com.wuspace.domain.SchedulerJob;

public interface RemoteSchedulerJobService {

    void addJob(SchedulerJob job);
}
