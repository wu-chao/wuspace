package com.github.wuchao.webproject.service.article.dto;

import com.github.wuchao.webproject.domain.MediaContent;
import com.github.wuchao.webproject.domain.MediaInfo;
import com.github.wuchao.webproject.domain.enumeration.MediaType;
import com.github.wuchao.webproject.security.SecurityUtils;
import com.github.wuchao.webproject.support.ArticleSupport;
import lombok.*;

import java.time.LocalDateTime;

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
        mediaInfo.setMediaType(MediaType.ARTICLE);
        mediaInfo.setPublishedDate(LocalDateTime.now());
        mediaInfo.setAuthorId(SecurityUtils.getCurrentUser().getUserId());
        // 概要
        ArticleSupport.buildSummary(mediaInfo, 60);
        // 缩略图
        ArticleSupport.buildThumbnailUrl(mediaInfo, 4);

        return mediaInfo;
    }

}
