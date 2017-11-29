package com.wuspace.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public abstract class ImageUtil {
    /**
     * 将base64编码字符串转换为图片
     *
     * @param imageData base64编码字符串
     * @param path      图片路径-具体到文件
     * @return
     */
    public static boolean base64ToImage(String imageData, String path) {
        imageData = imageData.substring(imageData.indexOf(',') + 1);

        try {
            imageData.replace("", "+");

            OutputStream out = new FileOutputStream(path);
            out.write(Base64.decodeBase64(imageData));
            out.flush();
            out.close();

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    //byte数组到图片到硬盘上
    public static void byte2image(byte[] data, String path) {
        if (data.length < 3 || path.equals("")) return;//判断输入的byte是否为空
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));//打开输入流
            imageOutput.write(data, 0, data.length);//将byte写入硬盘
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }

    /**
     * String 转 byte[]
     *
     * @param str
     * @param charset
     * @return
     */
    public static byte[] str2Byte(String str, String charset) {
        try {
            if (StringUtils.isEmpty(charset)) {
                return str.getBytes();
            } else {
                return str.getBytes(charset);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    /**
     * byte[] 转 String
     *
     * @param bytes
     * @param charset
     * @return
     */
    public static String byte2Str(byte[] bytes, String charset) {
        try {
            if (StringUtils.isEmpty(charset)) {
                return new String(bytes);
            } else {
                return new String(bytes, charset);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
