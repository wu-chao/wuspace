package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Map;

@Getter
@Setter
public abstract class SchedulerJob extends QuartzJobBean implements java.io.Serializable {

    String identify;

    String group;

    String description;

    String cron;

    Map jobDataMap;

    public abstract void log();

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(this.identify + "--------" + this.group);
        log();
    }
}
