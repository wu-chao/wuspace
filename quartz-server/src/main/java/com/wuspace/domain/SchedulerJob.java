package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Getter
@Setter
public class SchedulerJob extends QuartzJobBean {

    String identify;

    String group;

    String description;

    String cron;

    JobDataMap jobDataMap;

    public void log() {

    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(this.identify + "--------" + this.group);
        log();
    }
}
