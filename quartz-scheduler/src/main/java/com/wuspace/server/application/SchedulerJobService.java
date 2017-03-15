package com.wuspace.server.application;

import com.wuspace.server.domain.job.SchedulerJob;
import org.quartz.JobKey;

import java.util.Set;

public interface SchedulerJobService {

    void addJob(SchedulerJob job);

    Set<JobKey> allJobs(String matcher);
}
