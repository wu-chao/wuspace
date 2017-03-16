package com.wuspace.server.web;

import com.wuspace.server.application.SchedulerJobService;
import com.wuspace.server.domain.job.SchedulerJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by WUCHAO on 2017/3/8.
 */
@Controller
public class JdbcSchedulerController {

    @Autowired
    private SchedulerJobService schedulerJobService;

    @RequestMapping(value = "/jdbc", method = RequestMethod.POST)
    public ResponseEntity jdbc(@RequestBody SchedulerJob job) {
        schedulerJobService.addJob(job);
        return ResponseEntity.ok().build();
    }
}
