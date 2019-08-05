package com.github.wuchao.webproject.util;

import org.junit.Test;

public class NetUtilsTests {

    @Test
    public void testHttpRequest() {
        String url = "";
        Object response = NetUtils.httpRequest(url, "get");
        System.out.println(response);
    }

}
