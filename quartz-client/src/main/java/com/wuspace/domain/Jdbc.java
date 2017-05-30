package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Jdbc implements Jobable {

    public String url;

    public String username;

    public String password;

    public String driverClassName;

    public String sql;

    @Override
    public SchedulerJob convertToScheduler() {
        SchedulerJob job = new SchedulerJob();
        job.setIdentify("jdbc" + new Random().nextInt());
        job.setGroup("jdbcs");
        job.setDescription("j...d...b...c");
        job.setCron("0 */1 * * * ? *");
        return job;
    }
}
