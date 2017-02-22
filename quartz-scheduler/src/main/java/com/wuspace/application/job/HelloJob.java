package com.wuspace.application.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Random;

/**
 * Created by WUCHAO on 2017/2/20.
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("===================" + new Random(100).nextInt());
    }
}
