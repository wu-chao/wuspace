package com.wuspace.application.impl;

import com.wuspace.application.SchedulerService;
import com.wuspace.application.job.SchedulerJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(SchedulerJob job) {
        JobDetail jobDetail = JobBuilder.newJob(job.getClass())
                .withIdentity(job.getIdentify(), job.getGroup())
                .usingJobData(job.getJobDataMap())
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(job.getIdentify(), job.getGroup())
                .withDescription(job.getDescription())
                .forJob(jobDetail)
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
