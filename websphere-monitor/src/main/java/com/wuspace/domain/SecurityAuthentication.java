package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by WUCHAO on 2017/3/10.
 */
@Getter
@Setter
public class SecurityAuthentication {

    private int webAuthenticationCount;

    private int identityAssertionCount;

    private int basicAuthenticationCount;

    private int tokenAuthenticationCount;

    private int jAASIdentityAssertionCount;

    private int jAASBasicAuthenticationCount;

    private int jAASTokenAuthenticationCount;

    private int rMIAuthenticationCount;

    private long webAuthenticationTime;

    private long identityAssertionTime;

    private long basicAuthenticationTime;

    private long tokenAuthenticationTime;

    private long jAASIdentityAssertionTime;

    private long jAASBasicAuthenticationTime;

    private long jAASTokenAuthenticationTime;

    private long rMIAuthenticationTime;

}
