package com.wuspace.commons;

import com.wuspace.commons.util.QrcodeGenerator;

public class CommonApplication {
    public static void main(String[] args) {
        QrcodeGenerator.create("百度首页", "/var/qrcode.png");
    }
}
