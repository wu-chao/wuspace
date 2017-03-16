package com.wuspace.server.domain.job;

import lombok.Getter;
import lombok.Setter;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Getter
@Setter
public class SchedulerJob implements Job {

    String identify;

    String group;

    String description;

    String cron;

    JobDataMap jobDataMap;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}
