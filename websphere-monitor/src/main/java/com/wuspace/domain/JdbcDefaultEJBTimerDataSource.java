package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by WUCHAO on 2017/3/11.
 */
@Getter
@Setter
public class JdbcDefaultEJBTimerDataSource {

    private int createCount;

    private int closeCount;

    private int poolSize;

    private int freePoolSize;

    private int waitingThreadCount;

    private int percentUsed;

    private long useTime;

    private long waitTime;
}
