package com.wuspace.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by WUCHAO on 2017/2/20.
 */
@Configuration
public class QuartzConfig {

    /**
     * 创建Scheduler对象并启动
     *
     * @return
     */
    @Bean
    public Scheduler scheduler() {
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            return scheduler;
        } catch (SchedulerException e) {
            e.printStackTrace();
            //关闭scheduler
            try {
                if (!scheduler.isShutdown()) {
                    scheduler.shutdown();
                }
            } catch (SchedulerException e1) {
                e1.printStackTrace();
                return null;
            }
            return null;
        }
    }
}
