package com.wuspace.client.domain;

import com.wuspace.util.JdbcUtils;
import lombok.Getter;
import lombok.Setter;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Map;
import java.util.Random;

@Getter
@Setter
public class Jdbc extends SchedulerJob {

    public String url;

    public String username;

    public String password;

    public String driverClassName;

    public String sql;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JdbcUtils jdbcUtils = new JdbcUtils(username, password, driverClassName, sql);
        Map<String, Object> map = jdbcUtils.getJdbcTemplate().queryForMap(sql);
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(new Random().nextInt(100) + "====" + entry.getKey() + "====" + entry.getValue());
        }
        jdbcUtils.close();
    }
}
