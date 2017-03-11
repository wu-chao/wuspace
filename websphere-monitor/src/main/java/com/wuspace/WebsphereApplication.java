package com.wuspace;

import com.wuspace.domain.PerformanceMonitor;
import com.wuspace.util.XmlUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by WUCHAO on 2017/3/10.
 */
@SpringBootApplication
public class WebsphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsphereApplication.class);
        PerformanceMonitor monitor = new XmlUtils().parsePerformanceMonitor();
    }
}
