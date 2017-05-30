package com.wuspace.web;

import com.wuspace.application.RemoteSchedulerJobService;
import com.wuspace.domain.Jdbc;
import com.wuspace.domain.SchedulerJob;
import org.apache.commons.collections.map.HashedMap;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WUCHAO on 2017/3/15.
 */
@Controller
public class JdbcJobCreateController {

    @Autowired
    private RemoteSchedulerJobService remoteSchedulerJobService;

    @RequestMapping(value = "/addJdbc")
    public ResponseEntity addJob() {
        SchedulerJob jdbcJob = new Jdbc().convertToScheduler();
        Map<String, String> jobDataMap = new HashMap<>();
        jobDataMap.put("url", "jdbc:mysql://localhost:3306/quartz_scheduler");
        jobDataMap.put("username", "root");
        jobDataMap.put("password", "");
        jobDataMap.put("driverClassName", "com.mysql.jdbc.Driver");
        jobDataMap.put("sql", "select job_name, job_group, description from qrtz_job_details limit 1");
        jdbcJob.setJobDataMap(jobDataMap);
        remoteSchedulerJobService.addJob(jdbcJob);
        return ResponseEntity.ok().build();
    }
}