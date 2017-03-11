package com.wuspace.client.domain;

import com.wuspace.util.JdbcUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Map;

public class Jdbc extends SchedulerJob {

    public String url;

    public String username;

    public String password;

    public String driverClassName;

    public String sql;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JdbcUtils jdbcUtils = new JdbcUtils(url, username, password, driverClassName);
        Map<String, Object> results = jdbcUtils.getJdbcTemplate().queryForMap(sql);
        for (Map.Entry e : results.entrySet()) {
            System.out.println("--------------" + e.getKey() + " : " + e.getValue() + "--------------");
        }
        jdbcUtils.close();
    }
}
