package com.github.wuchao.webproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
//@EnableDiscoveryClient
public class NewsApplication {
    public static void main(String args[]) {
        ConfigurableApplicationContext context = SpringApplication.run(NewsApplication.class, args);

//        String src = "/home/wu-chao/下载/spring boot实战.pdf";
//        String dest = "/home/wu-chao/下载/2222.pdf";
//        String img = "/home/wu-chao/下载/timg.jpeg";
//        OfficeServiceImpl.addWatermarkImageToPDF(src, dest, img, 50, 50, 400, 50);


//        OfficeServiceImpl.addWatermarkImageToWord("/home/wu-chao/下载/qingshi-fields2.docx", "/home/wu-chao/下载/qingshi-fields3.docx", "/home/wu-chao/下载/16093404_waifu2x_photo_noise3_scale_tta_1.png");

//        PdfServiceImpl.pdf2Images("/home/wu-chao/下载/pdftoimages/", "/home/wu-chao/下载/springboot.pdf");

//        PdfServiceImpl.image2Pdf("/tmp/pdf_to_images_.png", "/tmp/333.pdf");


//        String filePath = "/home/wu-chao/下载/springboot.pdf";
//        String imageDirection = "/home/wu-chao/下载/pdftoimages/";
//        PdfServiceImpl.executorServicePdf2Images(imageDirection, filePath);

    }
}
