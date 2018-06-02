package com.wuspace.service.article;

import com.wuspace.domain.MediaContent;
import com.wuspace.domain.MediaInfo;
import com.wuspace.domain.enumeration.MediaType;
import com.wuspace.repository.MediaContentRepository;
import com.wuspace.repository.MediaInfoRepository;
import com.wuspace.security.SecurityUtils;
import com.wuspace.service.article.dto.AdminArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class AdminArticlePublishService {

    @Autowired
    private MediaContentRepository mediaContentRepository;

    @Autowired
    private MediaInfoRepository mediaInfoRepository;

    @Transactional(rollbackFor = Exception.class)
    public void publishArticle(AdminArticleDTO articleDTO) {
        MediaContent articleContent = new MediaContent();
        articleContent.setContent(articleDTO.getContent());
        mediaContentRepository.save(articleContent);

        MediaInfo article = new MediaInfo();
        article.setTitle(articleDTO.getTitle());
        article.setMediaContent(articleContent);
        article.setSummary(articleContent.getContent().length() <= 60 ? articleContent.getContent() : articleContent.getContent().substring(0, 60));
        article.setCategoryId(articleDTO.getCategoryId());
        article.setMediaType(MediaType.ARTICLE);
        article.setPublishDate(ZonedDateTime.now());
        article.setAuthorId(SecurityUtils.getCurrentUser().getUserId());
        mediaInfoRepository.save(article);
    }

}
