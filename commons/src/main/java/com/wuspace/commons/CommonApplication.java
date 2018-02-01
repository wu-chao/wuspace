package com.wuspace.commons;

import com.wuspace.commons.util.QrcodeGenerator;

public class CommonApplication {
    public static void main(String[] args) {
        QrcodeGenerator.createQrcodeFile("http://111.231.106.242:8080/products/49/qrcode?nologin=ff8081816150f53b016150f53b250000", "/home/wu/图片/qrcode.png");
    }
}
