package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by WUCHAO on 2017/3/11.
 */
@Getter
@Setter
public class Server {

    private SecurityAuthentication securityAuthentication;

    private SecurityAuthorization securityAuthorization;

    private JDBCConnectionPools jDBCConnectionPools;
}
