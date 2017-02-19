package com.wuspace.service.scheduler;

import com.wuspace.service.scheduler.job.MainJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by WUCHAO on 2017/2/19.
 */
@Component
public class MainScheduler {
    public void execute() {
        try {
            JobDetail jobDetail = JobBuilder.newJob(MainJob.class)
                    .withIdentity("jfowef", "jfowejfo")
                    .build();
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");
            Trigger jobTrigger = TriggerBuilder.newTrigger()
                    .withIdentity("fjowejf", "fjwoefj")
                    .startAt(new Date())
                    .withSchedule(cronScheduleBuilder)
                    .forJob(jobDetail)
                    .build();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(jobDetail, jobTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
