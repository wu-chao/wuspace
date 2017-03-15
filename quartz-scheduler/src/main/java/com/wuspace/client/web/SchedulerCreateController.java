package com.wuspace.client.web;

import com.wuspace.client.domain.Jdbc;
import com.wuspace.client.domain.SchedulerJob;
import org.quartz.JobDataMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WUCHAO on 2017/3/15.
 */
@Controller
public class SchedulerCreateController {

    @RequestMapping(value = "/addJob")
    public ResponseEntity addJob() {
        SchedulerJob jdbcJob = new Jdbc();
        jdbcJob.setIdentify("jdbc01");
        jdbcJob.setGroup("jdbcs");
        jdbcJob.setDescription("jdbc01");
        jdbcJob.setCron("0 0/1 * * * ?");
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("url", "");
        jobDataMap.put("username", "root");
        jobDataMap.put("password", "");
        jobDataMap.put("driverClassName", "com.mysql.jdbc.Driver");
        jobDataMap.put("sql", "select u from user");
        jdbcJob.setJobDataMap(jobDataMap);
        return ResponseEntity.ok().build();
    }
}