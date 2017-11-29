package com.wuspace.controller;

import com.wuspace.application.impl.PdfServiceImpl;
import com.wuspace.util.ImageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.image.BufferedImage;

@Controller
public class PdfSignController {

    private final static String FILE_TYPE_PNG = "png";
    private final static String PNG_FILE_EXT = "." + FILE_TYPE_PNG;

    @Value("${web.tmp.path}")
    private String imageUploadPath;

    @GetMapping("/signature/{fileName:.+}")
    public String signature(@PathVariable("fileName") String fileName, Model model) {
        // /home/wu-chao/下载/springboot.pdf

        String pdfToImageName = PdfServiceImpl.convertOnePage2Image(fileName);

        model.addAttribute("pdfToImageName", pdfToImageName);
        model.addAttribute("fileName", fileName);

        return "signature";
    }

    @GetMapping("/jsignature/{fileName:.+}")
    public String jsignature(@PathVariable("fileName") String fileName, Model model) {
        // /home/wu-chao/下载/springboot.pdf

        String pdfToImageName = PdfServiceImpl.convertOnePage2Image(fileName);

        model.addAttribute("pdfToImageName", pdfToImageName);
        model.addAttribute("fileName", fileName);

        return "jsignature";
    }

    @PostMapping("/signature/{fileName:.+}")
    public String sign(@PathVariable("fileName") String fileName, @RequestParam("imageData") String imageData, @RequestParam("height") float height, @RequestParam("width") float width) {
        String absolutePathWithoutExt = imageUploadPath + fileName.substring(0, fileName.lastIndexOf('.'));
        ImageUtil.base64ToImage(imageData, absolutePathWithoutExt + PNG_FILE_EXT);
        PdfServiceImpl.image2Pdf(absolutePathWithoutExt + PNG_FILE_EXT, absolutePathWithoutExt + ".pdf", width, height);
        return "redirect:/signature/" + fileName;
    }
}
