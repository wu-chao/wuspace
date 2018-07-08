package com.github.wuchao.webproject.spider.qqnews;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class SpiderRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Spider.create(new QQNewsPageProcessor())
                // 从 ”http://news.qq.com/“ 页面开始爬取
                .addUrl("http://news.qq.com/")
                .addPipeline(new QQNewsPipeline())
                .thread(5)
                .run();
    }
}
