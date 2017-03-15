package com.wuspace.server.web;

import com.wuspace.server.application.SchedulerJobService;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * Created by WUCHAO on 2017/3/15.
 */
@Controller
public class SchedulerIndexController {

    @Autowired
    private SchedulerJobService schedulerJobService;

    @RequestMapping(value = "/show")
    public ResponseEntity index(@RequestParam(defaultValue = "") String matcher, Model model) {
        Set<JobKey> jobKeys = schedulerJobService.allJobs(matcher);
        model.addAttribute("jobs", jobKeys);

        //打印所有Job的信息
        for (JobKey jobKey : jobKeys) {
            System.out.println("======" + jobKey.getName() + jobKey.getGroup());
        }

        return ResponseEntity.ok().build();
    }
}
