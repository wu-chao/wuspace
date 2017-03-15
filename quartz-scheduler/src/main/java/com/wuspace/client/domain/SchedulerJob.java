package com.wuspace.client.domain;

import lombok.Getter;
import lombok.Setter;
import org.quartz.Job;
import org.quartz.JobDataMap;

@Getter
@Setter
public abstract class SchedulerJob implements Job {

    String identify;

    String group;

    String description;

    String cron;

    JobDataMap jobDataMap;

}
