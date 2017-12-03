package com.wuspace.commons.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileUtil {

    /**
     * load image
     */
    public static BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
