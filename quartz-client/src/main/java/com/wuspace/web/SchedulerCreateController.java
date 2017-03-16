package com.wuspace.web;

import com.wuspace.application.RemoteSchedulerJobService;
import com.wuspace.domain.Jdbc;
import com.wuspace.domain.SchedulerJob;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WUCHAO on 2017/3/15.
 */
@Controller
public class SchedulerCreateController {

    @Autowired
    private RemoteSchedulerJobService remoteSchedulerJobService;

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
        remoteSchedulerJobService.addJob(jdbcJob);
        return ResponseEntity.ok().build();
    }
}