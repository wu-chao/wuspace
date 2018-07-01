package com.github.wuchao.webproject.spider.qqnews;

import com.github.wuchao.webproject.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.jdbc.core.JdbcTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

@Slf4j
public class QQNewsPageProcessor implements PageProcessor {

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    private int count = 0;

    private JdbcTemplate jdbcTemplate;

    @Override
    public void process(Page page) {
//        List<String> lis = page.getHtml().xpath("//div[@class='Q-tpList']/div[@class='Q-tpWrap']").all();
//        if (CollectionUtils.isNotEmpty(lis)) {
//            lis.forEach(li -> {
//                HtmlCleaner htmlCleaner = new HtmlCleaner();
//                TagNode node = htmlCleaner.clean(li);
//                try {
//                    Object[] pics = node.evaluateXPath("//img[@class='picto']/@src");
//                    Object[] titles = node.evaluateXPath("//div[@class='text']/em/a/text()");
//                    Object[] detailUrls = node.evaluateXPath("//div[@class='text']/em/a/@href");
//                    log.info(pics != null && pics.length > 0 ? String.valueOf(pics[0]) : "");
//                    log.info(titles != null && titles.length > 0 ? String.valueOf(titles[0]) : "");
//                    log.info(detailUrls != null && titles.length > 0 ? String.valueOf(detailUrls[0]) : "");
//                } catch (XPatherException e) {
//                    e.printStackTrace();
//                }
//            });
//        }

        List<String> detailUrls = page.getHtml().xpath("//div[@class='Q-tpWrap']/a").links().all();
        if (CollectionUtils.isNotEmpty(detailUrls)) {
            page.addTargetRequests(detailUrls);
        } else {
            String title = page.getHtml().xpath("//div[@class='LEFT']/h1/text()").get();
            List<String> contents = page.getHtml().xpath("//div[@class='LEFT']/div[@class='content']/div[@class='content-article']/").all();
            String year = page.getHtml().xpath("//div[@class='LeftTool']/div[@class='left-stick-wp']/div[@class='year']/span/text()").get();
            String md = page.getHtml().xpath("//div[@class='LeftTool']/div[@class='left-stick-wp']/div[@class='md']/span/text()").get();
            String time = page.getHtml().xpath("//div[@class='LeftTool']/div[@class='left-stick-wp']/div[@class='time']/span/text()").get();

            StringBuilder contentSb = new StringBuilder();
            if (CollectionUtils.isNotEmpty(contents)) {
                contents.forEach(text -> {
                    contentSb.append(text);
                });
            }



        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public QQNewsPageProcessor() {
        super();
        this.jdbcTemplate = (JdbcTemplate) SpringContextUtil.getBean("jdbcTemplate");
    }

    ///home/wu/IdeaProjects/web-project/web-crawler/src/log4j.properties
    //log4j.properties
    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        Spider.create(new QQNewsPageProcessor())
                // 从 ”http://news.qq.com/“ 页面开始爬取
                .addUrl("http://news.qq.com/")
                .thread(5)
                .run();
    }

}
