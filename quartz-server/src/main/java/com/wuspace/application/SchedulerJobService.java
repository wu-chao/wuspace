package com.wuspace.application;

import com.wuspace.domain.SchedulerJob;
import org.quartz.JobKey;

import java.util.Set;

public interface SchedulerJobService {

    void addJob(SchedulerJob job);

    Set<JobKey> allJobs(String matcher);
}
