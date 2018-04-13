package com.wuspace;

import java.net.URL;

public class Test {
    public void test() {
        URL url1 = this.getClass().getResource("test.txt");
        URL url2 = this.getClass().getResource("/test.txt");
        URL url3 = this.getClass().getClassLoader().getResource("test.txt");

        System.out.print(url1 + "-----" + url2 + "------" + url3);
    }
}
