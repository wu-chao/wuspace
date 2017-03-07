package com.wuspace.application.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Jdbc extends SchedulerJob {

    public String url;

    public String username;

    public String password;

    public String driverName;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}
