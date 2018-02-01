package com.wuspace.commons.util;

import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 使用 QRCode 生成二维码
 * jar包下载： http://blog.csdn.net/u012453843/article/details/71484300
 */
public abstract class QrcodeGenerator {

    public static void create(String content, String imgPath) {
        try {
            Qrcode qrcodeHandler = new Qrcode();

            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
            qrcodeHandler.setQrcodeErrorCorrect('H');
            qrcodeHandler.setQrcodeEncodeMode('B');
            qrcodeHandler.setQrcodeVersion(5);

//            System.out.println(content);

//	           int imgSize = 67 + 12 * (size - 1);
            byte[] contentBytes = content.getBytes("UTF-8");
            BufferedImage bufImg = new BufferedImage(115, 115,
                    BufferedImage.TYPE_INT_RGB);

            Graphics2D gs = bufImg.createGraphics();
            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, 115, 115);
            // 设定图像颜色> BLACK
            gs.setColor(Color.BLACK);
            // 设置偏移量 不设置可能导致解析出错
            int pixoff = 2;

            // 输出内容> 二维码
            if (contentBytes.length > 0 && contentBytes.length < 800) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            } else {
                System.err.println("QRCode content bytes length = "
                        + contentBytes.length + " not in [ 0,120 ]. ");
            }

            gs.dispose();
            bufImg.flush();
            File imgFile = new File(imgPath);

            // 生成二维码QRCode图片
            ImageIO.write(bufImg, "png", imgFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
