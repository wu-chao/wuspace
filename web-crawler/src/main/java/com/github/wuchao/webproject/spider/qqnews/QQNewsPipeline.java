package com.github.wuchao.webproject.spider.qqnews;

import com.github.wuchao.webproject.domain.MediaContent;
import com.github.wuchao.webproject.domain.MediaInfo;
import com.github.wuchao.webproject.domain.enumeration.MediaType;
import com.github.wuchao.webproject.repository.MediaContentRepository;
import com.github.wuchao.webproject.repository.MediaInfoRepository;
import com.github.wuchao.webproject.support.ArticleSupport;
import com.github.wuchao.webproject.util.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class QQNewsPipeline implements Pipeline {

    private MediaContentRepository mediaContentRepository;

    private MediaInfoRepository mediaInfoRepository;

    public QQNewsPipeline() {
        mediaContentRepository = (MediaContentRepository) SpringContextUtil.getBean("mediaContentRepository");
        mediaInfoRepository = (MediaInfoRepository) SpringContextUtil.getBean("mediaInfoRepository");
    }


    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title");
        String content = resultItems.get("content");
        if (StringUtils.isNotBlank(title) && StringUtils.isNotBlank(content)) {
            MediaContent mediaContent = new MediaContent();
            mediaContent.setContent(content);
            mediaContentRepository.save(mediaContent);

            MediaInfo mediaInfo = new MediaInfo();
            mediaInfo.setTitle(title);
            mediaInfo.setMediaType(MediaType.NEWS);
            mediaInfo.setMediaContent(mediaContent);
            ArticleSupport.buildSummary(mediaInfo, 60);
            ArticleSupport.buildThumbnailUrl(mediaInfo, 4);

            if (resultItems.get("publishedDate") != null) {
                mediaInfo.setPublishedDate(resultItems.get("publishedDate"));
            }

            mediaInfoRepository.save(mediaInfo);
        }
    }
}
