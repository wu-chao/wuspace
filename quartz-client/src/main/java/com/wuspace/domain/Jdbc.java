package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Jdbc extends SchedulerJob {

    public String url;

    public String username;

    public String password;

    public String driverClassName;

    public String sql;

}
