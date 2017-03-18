package com.wuspace.web;

import com.wuspace.domain.SchedulerJob;
import com.wuspace.application.SchedulerJobService;
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
public class SchedulerCreateController {

    @Autowired
    private SchedulerJobService schedulerJobService;

    @RequestMapping(value = "/jdbc", method = RequestMethod.POST)
    public ResponseEntity jdbc(@RequestBody SchedulerJob job) {
        schedulerJobService.addJob(job);
        return ResponseEntity.ok().build();
    }
}
