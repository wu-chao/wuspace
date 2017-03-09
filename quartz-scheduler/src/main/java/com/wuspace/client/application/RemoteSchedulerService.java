package com.wuspace.client.application;

import com.wuspace.client.domain.SchedulerJob;

public interface RemoteSchedulerService {

    void addJob(SchedulerJob job);
}
