package com.wuspace.application.job;

import org.quartz.Job;

import java.util.Map;

public abstract class SchedulerJob implements Job {

    String identify;

    String group;

    String description;

    String cron;

    Map<String, String> jobDataMap;

}
