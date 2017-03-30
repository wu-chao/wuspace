package com.wuspace.application.impl;

import com.wuspace.application.SchedulerJobService;
import com.wuspace.domain.SchedulerJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
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
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<JobKey> allJobs(String matcher) {
        Set<JobKey> jobKeys = new HashSet<>();
        try {
            jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupContains(matcher));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobKeys;
    }
}
