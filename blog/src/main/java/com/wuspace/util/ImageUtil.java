package com.wuspace.util;

import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;

public abstract class ImageUtil {
    /**
     * 将base64编码字符串转换为图片
     *
     * @param imgStr base64编码字符串
     * @param path   图片路径-具体到文件
     * @return
     */
    public static boolean base64ToImage(String imgStr, String path) {

        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out;

        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);

            // 处理数据
            for (int i = 0; i < b.length; i++) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
