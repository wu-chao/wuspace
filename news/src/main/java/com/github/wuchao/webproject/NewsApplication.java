package com.github.wuchao.webproject;

import com.github.wuchao.webproject.config.DefaultProfileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootConfiguration
@EnableScheduling
@Slf4j
//@EnableDiscoveryClient
public class NewsApplication {

    private final Environment env;

    public NewsApplication(Environment env) {
        this.env = env;
    }

    public static void main(String args[]) throws UnknownHostException {
        SpringApplication app = new SpringApplication(NewsApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());


//        ConfigurableApplicationContext context = SpringApplication.run(NewsApplication.class, args);

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


    /**
     * Unable to start EmbeddedWebApplicationContext due to missing EmbeddedServletContainerFactory bean.
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        return factory;
    }


}
