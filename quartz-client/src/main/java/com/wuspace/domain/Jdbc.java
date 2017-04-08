package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;
import org.quartz.Scheduler;

@Getter
@Setter
public class Jdbc implements Jobable {

    public String url;

    public String username;

    public String password;

    public String driverClassName;

    public String sql;

    @Override
    public Scheduler convertToScheduler() {
        return null;
    }
}
