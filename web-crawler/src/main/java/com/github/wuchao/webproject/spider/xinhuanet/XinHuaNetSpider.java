package com.github.wuchao.webproject.spider.xinhuanet;

import com.github.wuchao.webproject.spider.qqnews.QQNewsPageProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class XinHuaNetSpider implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        new ScheduledThreadPoolExecutor(2).scheduleWithFixedDelay(() -> {
            String[] urls = {
                    // 时政
                    "http://www.xinhuanet.com/politics/",
                    // 国际
                    "http://www.xinhuanet.com/world/",
                    // 财经
                    "http://www.xinhuanet.com/fortune/",
                    "http://www.xinhuanet.com/finance/",
                    "http://www.xinhuanet.com/money/index.htm",
                    // 法治
                    "http://www.xinhuanet.com/legal/index.htm",
                    // 社会
                    "http://www.xinhuanet.com/local/",
                    "http://www.xinhuanet.com/city/index.htm",
                    // 科技
                    "http://www.xinhuanet.com/tech/index.htm",
                    // 娱乐
                    "http://www.xinhuanet.com/ent/",
                    // 游戏
                    "http://www.xinhuanet.com/ent/game.htm",
                    // 体育
                    "http://sports.xinhuanet.com/",
                    // 旅游
                    "http://www.xinhuanet.com/travel/",
                    // 健康
                    "http://www.xinhuanet.com/health/",
                    // 文化
                    "http://www.xinhuanet.com/culture/",
                    // 食品
                    "http://www.xinhuanet.com/food/index.htm",
                    // 环保
                    "http://www.xinhuanet.com/energy/index.htm",
                    // 汽车
                    "http://www.xinhuanet.com/auto/index.htm",
                    // 房产
                    "http://www.xinhuanet.com/house/index.htm"

            };

            Spider.create(new QQNewsPageProcessor())
                    .addUrl(urls)
                    .thread(5)
                    .run();
        }, 0, 1, TimeUnit.DAYS);
    }

}
