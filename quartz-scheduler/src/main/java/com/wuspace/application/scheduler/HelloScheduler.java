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

    public void execHelloJob() {
        try {
            Scheduler scheduler = new QuartzConfig().scheduler();
            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("hello", "helloJobGroup")
                    .build();
            ScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule()

                    .withIntervalInSeconds(5)
                    .withRepeatCount(10);
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("hello", "helloTriggerGroup")
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
