package com.wuspace.client.application;

import com.wuspace.client.domain.SchedulerJob;

public interface RemoteSchedulerJobService {

    void addJob(SchedulerJob job);
}
