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

    }

}
