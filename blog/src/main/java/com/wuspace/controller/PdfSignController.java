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

@Controller
public class PdfSignController {

    private final static String FILE_TYPE_PNG = "png";
    private final static String PNG_FILE_EXT = "." + FILE_TYPE_PNG;

    @Value("${web.tmp.path}")
    private String imageUploadPath;

    @GetMapping("/signature/{pdfPath:.+}")
    public String signature(@PathVariable("pdfPath") String pdfPath, Model model) {
        // /home/wu-chao/下载/springboot.pdf
        String pdfToImageName = PdfServiceImpl.convertOnePage2Image(imageUploadPath + pdfPath);

        model.addAttribute("pdfToImageName", pdfToImageName);
        model.addAttribute("pdfPath", pdfPath);

        return "signature";
    }

    @PostMapping("/signature/{pdfPath:.+}")
    public String sign(@PathVariable("pdfPath") String pdfPath, @RequestParam("imageData") String imageData) {

        ImageUtil.base64ToImage(imageData, imageUploadPath + pdfPath.substring(0, pdfPath.lastIndexOf('.')) + PNG_FILE_EXT);
        PdfServiceImpl.image2Pdf(pdfPath + PNG_FILE_EXT, imageUploadPath + pdfPath);
        return "redirect:/signature/" + pdfPath;
    }
}
