package com.wuspace.service.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Random;

/**
 * Created by WUCHAO on 2017/2/19.
 */
public class MainJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("----------- wuchao "+ new Random().nextInt(10) +" -----------");
    }
}
