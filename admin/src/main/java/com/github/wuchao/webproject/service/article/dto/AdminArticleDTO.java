package com.github.wuchao.webproject.service.article.dto;

import com.github.wuchao.webproject.domain.MediaContent;
import com.github.wuchao.webproject.domain.MediaInfo;
import com.github.wuchao.webproject.domain.enumeration.MediaType;
import com.github.wuchao.webproject.security.SecurityUtils;
import com.github.wuchao.webproject.util.HtmlUtils;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;

import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminArticleDTO {

    private Long id;

    private String title;

    private String content;

    private MediaType mediaType;

    private Long categoryId;

    public static AdminArticleDTO build(MediaInfo mediaInfo) {
        AdminArticleDTO adminArticleDTO = AdminArticleDTO.builder()
                .id(mediaInfo.getId())
                .title(mediaInfo.getTitle())
                .content(mediaInfo.getMediaContent() != null ? mediaInfo.getMediaContent().getContent() : "")
                .mediaType(mediaInfo.getMediaType())
                .categoryId(mediaInfo.getCategoryId())
                .build();
        return adminArticleDTO;
    }

    public MediaInfo initMediaInfo(MediaInfo mediaInfo) {
        MediaContent mediaContent = mediaInfo.getMediaContent();
        if (mediaContent == null) {
            mediaContent = new MediaContent();
            mediaContent.setContent(this.getContent());
        }

        mediaInfo.setMediaContent(mediaContent);
        mediaInfo.setTitle(this.getTitle());
        mediaInfo.setSummary(this.getContent().length() <= 60 ? HtmlUtils.html2text(this.getContent()) : HtmlUtils.html2text(this.getContent()).substring(0, 60));
        mediaInfo.setMediaType(MediaType.ARTICLE);
        mediaInfo.setPublishedDate(ZonedDateTime.now());
        mediaInfo.setAuthorId(SecurityUtils.getCurrentUser().getUserId());

        // 缩略图
        Set<String> thumbnailUrls = HtmlUtils.parseHtmlImgSrc(this.getContent());
        if (CollectionUtils.isNotEmpty(thumbnailUrls)) {
            int size = thumbnailUrls.size();
            String thumbnailUrlStr = "";
            mediaInfo.setThumbnailNum(size);
            if (size > 0 && size < 4) {
                thumbnailUrlStr = com.github.wuchao.webproject.util.StringUtils.convertArrayToString(thumbnailUrls.toArray(new String[0]), 1);
            }
            if (size >= 4) {
                thumbnailUrlStr = com.github.wuchao.webproject.util.StringUtils.convertArrayToString(thumbnailUrls.toArray(new String[0]), 4);
            }
            mediaInfo.setThumbnailUrls(thumbnailUrlStr);
        }

        return mediaInfo;
    }

}
