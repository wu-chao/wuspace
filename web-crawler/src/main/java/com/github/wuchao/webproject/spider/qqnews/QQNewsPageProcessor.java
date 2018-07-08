package com.github.wuchao.webproject.spider.qqnews;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            StringBuilder contentSb = new StringBuilder();
            if (CollectionUtils.isNotEmpty(contents)) {
                contents.forEach(text -> {
                    contentSb.append(text);
                });
            }
            String publishedDateStr = page.getHtml().xpath("//div[@class='a_Info']/span[@class='a_time']/text()").get();
            if (StringUtils.isNotBlank(publishedDateStr)) {
                LocalDateTime publishedDate = LocalDateTime.parse(publishedDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                page.putField("publishedDate", publishedDate);
            }

            page.putField("title", title);
            page.putField("content", contentSb.toString());

        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public QQNewsPageProcessor() {
        super();
//        this.jdbcTemplate = (JdbcTemplate) SpringContextUtil.getBean("jdbcTemplate");
    }

}
