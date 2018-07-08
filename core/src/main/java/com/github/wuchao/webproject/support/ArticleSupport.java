package com.github.wuchao.webproject.support;

import com.github.wuchao.webproject.domain.MediaInfo;
import com.github.wuchao.webproject.util.HtmlUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class ArticleSupport {

    public static void buildThumbnailUrl(MediaInfo mediaInfo, int thumbnailNum) {
        if (mediaInfo != null && mediaInfo.getMediaContent() != null) {
            Set<String> thumbnailUrls = HtmlUtils.parseHtmlImgSrc(mediaInfo.getMediaContent().getContent());
            if (CollectionUtils.isNotEmpty(thumbnailUrls)) {
                int size = thumbnailUrls.size();
                String thumbnailUrlStr = "";
                mediaInfo.setThumbnailNum(size);
                if (size > 0 && size < thumbnailNum) {
                    thumbnailUrlStr = com.github.wuchao.webproject.util.StringUtils.convertArrayToString(thumbnailUrls.toArray(new String[0]), 1);
                }
                if (size >= thumbnailNum) {
                    thumbnailUrlStr = com.github.wuchao.webproject.util.StringUtils.convertArrayToString(thumbnailUrls.toArray(new String[0]), 4);
                }
                mediaInfo.setThumbnailUrls(thumbnailUrlStr);
            }
        }
    }

    public static void buildSummary(MediaInfo mediaInfo, int summaryLength) {
        if (mediaInfo != null && mediaInfo.getMediaContent() != null && StringUtils.isNotBlank(mediaInfo.getMediaContent().getContent())) {
            String content = mediaInfo.getMediaContent().getContent();
            mediaInfo.setSummary(content.length() <= summaryLength ?
                    HtmlUtils.html2text(content) : HtmlUtils.html2text(content).substring(0, summaryLength));
        }
    }

}
