package com.github.wuchao.webproject.spider.xinhuanet;

import com.github.wuchao.webproject.spider.qqnews.QQNewsPageProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class XinHuaNetSpider implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        String[] urls = new String[23];
        //
        Spider.create(new QQNewsPageProcessor())
                .addUrl("http://news.qq.com/")
                .thread(5)
                .run();
    }
}
