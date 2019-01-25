package com.github.wuchao.webproject;

import com.github.wuchao.webproject.common.Constants;
import com.github.wuchao.webproject.config.DefaultProfileUtil;
import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.TimeZone;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class NewsApplication {

    private final Environment env;

    public NewsApplication(Environment env) {
        this.env = env;
    }

    /**
     * Initializes investment.
     * <p>
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     * <p>
     * You can find more information on how profiles work with JHipster on <a href="http://jhipster.github.io/profiles/">http://jhipster.github.io/profiles/</a>.
     */
    @PostConstruct
    public void initApplication() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
            log.error("You have misconfigured your application! It should not run " +
                    "with both the 'dev' and 'prod' profiles at the same time.");
        }
        if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_CLOUD)) {
            log.error("You have misconfigured your application! It should not" +
                    "run with both the 'dev' and 'cloud' profiles at the same time.");
        }

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    public static void main(String args[]) throws UnknownHostException {
//        SpringApplication app = new SpringApplication(NewsApplication.class);
//        DefaultProfileUtil.addDefaultProfile(app);
//        Environment env = app.run(args).getEnvironment();
//        String protocol = "http";
//        if (env.getProperty("server.ssl.key-store") != null) {
//            protocol = "https";
//        }
//        log.info("\n----------------------------------------------------------\n\t" +
//                        "Application '{}' is running! Access URLs:\n\t" +
//                        "Local: \t\t{}://localhost:{}\n\t" +
//                        "External: \t{}://{}:{}\n\t" +
//                        "Profile(s): \t{}\n----------------------------------------------------------",
//                env.getProperty("spring.application.name"),
//                protocol,
//                env.getProperty("server.port"),
//                protocol,
//                InetAddress.getLocalHost().getHostAddress(),
//                env.getProperty("server.port"),
//                env.getActiveProfiles());


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

        UserRepository userRepository = (UserRepository) context.getBean("userRepository");
        User user = userRepository.findByUsername("admin");
        System.out.println(user.getEmail());
        user.setEmail("admin1@qq.com");

    }

}
