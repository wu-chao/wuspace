package com.wuspace.client.domain;

import lombok.Getter;
import lombok.Setter;
import org.quartz.JobDataMap;

@Getter
@Setter
public class SchedulerJob {

    String identify;

    String group;

    String description;

    String cron;

    JobDataMap jobDataMap;

}
