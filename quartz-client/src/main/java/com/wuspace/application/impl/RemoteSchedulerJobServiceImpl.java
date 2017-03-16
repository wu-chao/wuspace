package com.wuspace.application.impl;

import com.wuspace.application.RemoteSchedulerJobService;
import com.wuspace.domain.SchedulerJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteSchedulerJobServiceImpl implements RemoteSchedulerJobService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.quartz.server}")
    private String remoteSchedulerServer;

    @Override
    public void addJob(SchedulerJob job) {
        restTemplate.postForLocation(remoteSchedulerServer + "/jdbc", job);
    }
}