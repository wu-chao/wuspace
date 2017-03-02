package com.wuspace.application.job;

import org.quartz.*;

import java.util.Map;
import java.util.Random;

/**
 * Created by WUCHAO on 2017/2/20.
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("==========" + new Random(100).nextInt() + "=========");

        //获取JobDetail和其所有参数
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        Map<String, Object> jobDataMap = jobDetail.getJobDataMap();
        JobKey jobKey = jobDetail.getKey();

        Map<String, Object> mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
    }
}
