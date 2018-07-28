package com.github.wuchao.webproject.spider.xinhuanet;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class XinHuaNetPageProcessor implements PageProcessor {

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public Site getSite() {
        return this.site;
    }

    @Override
    public void process(Page page) {
//        // 时政
//        "http://www.xinhuanet.com/politics/",
//                // 国际
//                "http://www.xinhuanet.com/world/",
//                // 财经
//                "http://www.xinhuanet.com/fortune/",
//                "http://www.xinhuanet.com/finance/",
//                "http://www.xinhuanet.com/money/index.htm",
//                // 法治
//                "http://www.xinhuanet.com/legal/index.htm",
//                // 社会
//                "http://www.xinhuanet.com/local/",
//                "http://www.xinhuanet.com/city/index.htm",
//                // 科技
//                "http://www.xinhuanet.com/tech/index.htm",
//                // 娱乐
//                "http://www.xinhuanet.com/ent/",
//                // 游戏
//                "http://www.xinhuanet.com/ent/game.htm",
//                // 体育
//                "http://sports.xinhuanet.com/",
//                // 旅游
//                "http://www.xinhuanet.com/travel/",
//                // 健康
//                "http://www.xinhuanet.com/health/",
//                // 文化
//                "http://www.xinhuanet.com/culture/",
//                // 食品
//                "http://www.xinhuanet.com/food/index.htm",
//                // 环保
//                "http://www.xinhuanet.com/energy/index.htm",
//                // 汽车
//                "http://www.xinhuanet.com/auto/index.htm",
//                // 房产
//                "http://www.xinhuanet.com/house/index.htm"

        String url = page.getUrl().get();
        if (url.contains("politics")) {

        }

    }

}
