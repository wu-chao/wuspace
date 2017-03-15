package com.wuspace.server.config;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.simpl.PropertySettingJobFactory;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class QuartzConfig {

    @Bean
    public Scheduler scheduler() {
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.setJobFactory(jobFactory());
            scheduler.start();
            return scheduler;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public JobFactory jobFactory() {
        return new QuartzJobFactory();
    }

    protected class QuartzJobFactory extends PropertySettingJobFactory {

        @Autowired
        private AutowireCapableBeanFactory autowireCapableBeanFactory;

        @Override
        public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
            Job job = super.newJob(bundle, scheduler);
            autowireCapableBeanFactory.autowireBean(job);
            return job;
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}