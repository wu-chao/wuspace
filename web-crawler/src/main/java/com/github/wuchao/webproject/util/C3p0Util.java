package com.github.wuchao.webproject.util;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

public class C3p0Util {

    //本地线程管理对象，用于实现: 同一个线程获取的连接是同一个
    private static ThreadLocal<Connection> t = new ThreadLocal<Connection>();
    //  private static ComboPooledDataSource pool = null;
    private static DataSource pool = null;

    static {
        try {
//          pool=new ComboPooledDataSource();//使用默认配置项
            pool = new ComboPooledDataSource("hncu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return pool;
    }

    public static Connection getConn() {
        //先从t中拿，如果有就拿出去，如果没有再到池中拿且把该对象放到t中
        Connection con = t.get();
        if (con == null) {
            try {
                con = pool.getConnection();
                t.set(con); //放到t中
            } catch (Exception e) {

                throw new RuntimeException(e.getMessage(), e);
            }
        }
        System.out.println("获取一个连接:" + con);
        return con;
    }

}
