package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by WUCHAO on 2017/3/10.
 */
@Getter
@Setter
public class SecurityAuthorization {

    private long webAuthorizationTime;

    private long eJBAuthorizationTime;

    private long adminAuthorizationTime;

}
