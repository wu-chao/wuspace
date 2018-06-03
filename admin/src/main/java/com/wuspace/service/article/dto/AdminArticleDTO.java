package com.wuspace.service.article.dto;

import com.wuspace.domain.MediaContent;
import com.wuspace.domain.MediaInfo;
import com.wuspace.domain.enumeration.MediaType;
import com.wuspace.security.SecurityUtils;
import lombok.*;

import java.time.ZonedDateTime;

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
            mediaInfo.setPublishDate(ZonedDateTime.now());
            mediaInfo.setAuthorId(SecurityUtils.getCurrentUser().getUserId());
        }
        mediaContent.setContent(this.getContent());
        mediaInfo.setMediaContent(mediaContent);
        mediaInfo.setTitle(this.getTitle());
        mediaInfo.setSummary(this.getContent().length() <= 60 ? this.getContent() : this.getContent().substring(0, 60));
//        mediaInfo.setCategoryId(this.getCategoryId());
        mediaInfo.setMediaType(MediaType.ARTICLE);

        return mediaInfo;
    }

}
