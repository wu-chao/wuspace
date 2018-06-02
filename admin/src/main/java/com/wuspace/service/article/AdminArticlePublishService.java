package com.wuspace.service.article;

import com.wuspace.domain.Article;
import com.wuspace.domain.WorksContent;
import com.wuspace.domain.enumeration.MediaType;
import com.wuspace.repository.ArticleContentRepository;
import com.wuspace.repository.ArticleRepository;
import com.wuspace.security.SecurityUtils;
import com.wuspace.service.article.dto.AdminArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class AdminArticlePublishService {

    @Autowired
    private ArticleContentRepository articleContentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional(rollbackFor = Exception.class)
    public void publishArticle(AdminArticleDTO articleDTO) {
        WorksContent articleContent = new WorksContent();
        articleContent.setContent(articleDTO.getContent());
        articleContentRepository.save(articleContent);

        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setArticleContent(articleContent);
        article.setSummary(articleContent.getContent().length() <= 60 ? articleContent.getContent() : articleContent.getContent().substring(0, 60));
        article.setCategoryId(articleDTO.getCategoryId());
        article.setMediaType(MediaType.ARTICLE);
        article.setPublishDate(ZonedDateTime.now());
        article.setAuthorId(SecurityUtils.getCurrentUser().getUserId());
        articleRepository.save(article);
    }

}
