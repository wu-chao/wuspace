package com.wuspace.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;


public class JdbcUtils {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public JdbcUtils() {
        this.dataSource = new SingleConnectionDataSource();

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
