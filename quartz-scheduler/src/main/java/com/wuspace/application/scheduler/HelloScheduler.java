package com.wuspace.application.scheduler;

import com.wuspace.application.job.HelloJob;
import com.wuspace.config.QuartzConfig;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by WUCHAO on 2017/2/20.
 */
@Component
public class HelloScheduler {

    @Autowired
    Scheduler scheduler2;

    public void execSimpleHelloJob() {
        try {
            Scheduler scheduler = new QuartzConfig().scheduler();
            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("hello", "helloJobGroup")
                    .usingJobData("jobName", "chao")
                    .build();
            ScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5)
                    .withRepeatCount(10);
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("hello", "helloTriggerGroup")
                    .usingJobData("triggerName", "fewfewfe")
                    .withSchedule(builder)
                    .startNow()
                    .forJob(jobDetail)
                    .build();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void execCronHelloJob() {
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("jobNameKey", "11111111111111");
        dataMap.put("JobGroupNameKey", "22222222222222");
        try {
            Scheduler scheduler = new QuartzConfig().scheduler();
            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("hello2", "helloJobGroup")
                    .usingJobData("jobName", "helloJob2222")
                    .usingJobData("jobGroupName", "helloJobGroup2222")
                    .usingJobData(dataMap)
                    .build();
            ScheduleBuilder builder = CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("hello2", "helloTriggerGroup")
                    .usingJobData("triggerName", "helloTrigger2222")
                    .usingJobData("trggerGroupName", "helloTriggerGroup2222")
                    .withSchedule(builder)
                    .startNow()
                    .forJob(jobDetail)
                    .build();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
