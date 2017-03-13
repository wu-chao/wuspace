package com.wuspace.server.application.impl;

import com.wuspace.server.application.SchedulerJobService;
import com.wuspace.server.domain.job.SchedulerJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

public class SchedulerJobServiceImpl implements SchedulerJobService {

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
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                .forJob(jobDetail)
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}