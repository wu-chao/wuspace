package com.wuspace.domain;

import com.wuspace.util.JdbcUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Jdbc extends SchedulerJob {

    public String url;

    public String username;

    public String password;

    public String driverClassName;

    public String sql;

    @Override
    public void log() {
        JdbcUtils jdbcUtils = new JdbcUtils(url, username, password, driverClassName);
        Map<String, Object> results = jdbcUtils.getJdbcTemplate().queryForMap(sql);
        for (Map.Entry e : results.entrySet()) {
            System.out.println(e.getKey() + " -------------- : " + e.getValue());
        }
        jdbcUtils.close();
    }
}
